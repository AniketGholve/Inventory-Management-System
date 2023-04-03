package com.patient.ServiceImpl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import com.patient.Entity.DispenseToPatient;
import com.patient.Entity.LastInjectionScreen;
import com.patient.Entity.Patient;
import com.patient.Entity.Product;
import com.patient.Entity.Serial;
import com.patient.Repo.DispenseRepo;
import com.patient.Repo.PatientRepo;
import com.patient.Repo.ProductRepo;
import com.patient.Repo.SerialRepo;
import com.patient.Service.DispenceToPatientService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Service
public class DispenceToPatientServiceImpl implements DispenceToPatientService{
	
	@Autowired
	private SerialRepo serialRepo;
	
	@Autowired
	private PatientRepo patientRepo;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private DispenseRepo dispenseRepo;

	@Override
	public Serial getProductBySerialNo(Integer serialNo) {
		// TODO Auto-generated method stub
		if(serialNo==null)return null;
		Query q=entityManager.createQuery("select s from Serial s where s.serialNumber=:u and s.serialStatus IN (:v,:y) ");
		q.setParameter("u", serialNo);
		q.setParameter("v", "Available");
		q.setParameter("y", "Received");
		Serial s;
		try {
			 s=(Serial) q.getSingleResult();
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return s;
	}	
	

	@Override
	public DispenseToPatient createDispence(DispenseToPatient dispenceToPatient) {
		
		Date date = new Date(System.currentTimeMillis());
		Query q = entityManager.createNativeQuery("select p.minimum_days from product p where p.product_id=?");
		q.setParameter(1, dispenceToPatient.getProductId());
		int min = (int) q.getSingleResult();
		System.out.println(min);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		Calendar c = Calendar.getInstance(); 
		c.setTime(new Date(System.currentTimeMillis())); // Using today's date 
		c.add(Calendar.DATE, min); // Adding 5 days 
		String output = sdf.format(c.getTime()); 
		System.out.println(output);
		
		dispenceToPatient.setNextInjection(output);
		dispenceToPatient.setCreatedOn(date);
		DispenseToPatient d = dispenseRepo.save(dispenceToPatient);	
		return d;
	}
	
	@Override
	public List<Patient> getPatientsByName(String paientName) {
		// TODO Auto-generated method stub
		Query q=entityManager.createQuery("select p from Patient p where p.patientFirstName=:u");
		q.setParameter("u", paientName);
		List<Patient> result=q.getResultList();
		if(result.size()==0) return null;
		return result;
	}


	@Override
	public DispenseToPatient addDispense(Integer Id, Integer nurseId, Integer physicianId, Integer productId,
			Integer serialId,Integer locationId,String injectionSite) {
		// TODO Auto-generated method stub
		DispenseToPatient dispenseToPatient=new DispenseToPatient();
		Patient p=patientRepo.findById(Id).orElseThrow();
		long m=System.currentTimeMillis();
		Date d=new Date(m);
		dispenseToPatient.setCreatedOn(d);
		dispenseToPatient.setDispenseDate(d);
		dispenseToPatient.setEnterprise_id(1);
		dispenseToPatient.setExpMessage(null);
		dispenseToPatient.setExpType(null);
		dispenseToPatient.setPatientId(Id);
//		dispenseToPatient.setId(p);

		dispenseToPatient.setInitial(null);
		dispenseToPatient.setInjectionSite(injectionSite);
		dispenseToPatient.setLocationId(locationId);
		dispenseToPatient.setModifiedOn(null);
		dispenseToPatient.setNotes(null);
		dispenseToPatient.setNurseId(nurseId);
		dispenseToPatient.setOrderNum(1);
		dispenseToPatient.setPaymentStatus(null);
		dispenseToPatient.setPhysicianId(physicianId);
		dispenseToPatient.setProductId(productId);
		dispenseToPatient.setReaderId(1);
		dispenseToPatient.setRevesionEmail(null);
		dispenseToPatient.setRevisionInitial(null);
		dispenseToPatient.setRevisionNotes(null);
		dispenseToPatient.setSerialEventId(1);
		dispenseToPatient.setSerialId(serialId);
		dispenseToPatient.setSrcId(1);
		dispenseToPatient.setUserId(1);
		DispenseToPatient dtp=dispenseRepo.save(dispenseToPatient);
		return dtp;
	}


	@Override
	public Product getProductBySerialId(Integer serialId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<LastInjectionScreen> getAllDispense() {
		// TODO Auto-generated method stub
//		List<DispenseToPatient> list = dispenseRepo.findAll();
		Query q = entityManager.createNativeQuery("select pa.patient_first_name,pa.patient_last_name,pa.patient_date_of_birth,dp.created_On,dp.next_Injection,p.product_Name from dispense_to_patient dp inner join Product p on dp.product_Id = p.product_Id inner join Patient pa on pa.id = dp.patient_Id where STR_TO_DATE(dp.next_Injection,'%Y-%m-%d') >= date_sub(now(), INTERVAL 3 DAY) order by dp.next_injection");
		//Query q = entityManager.createQuery("select pa.patientFirstName,pa.patientLastName,pa.patientDob,dp.patientCreatedOn,dp.nextInjection,p.productName from dispenseToPatient dp inner join Product p on dp.productId = p.productId inner join Patient pa on pa.id = dp.patientId where STR_TO_DATE(dp.nextInjection,'%Y-%m-%d') >= date_sub(now(), INTERVAL 3 DAY) order by dp.nextInjection");

		List<Object[]> list = q.getResultList();
//		Object[][] objArray = list.toArray(new Object[0][]);
//		System.out.println(Arrays.deepToString(objArray));
		List<LastInjectionScreen> result = new ArrayList<>();
		for(Object[] o:list) {
			
			LastInjectionScreen l = new LastInjectionScreen();
			
			String encrypted = (String)o[0];
			byte[] decodedBytes = Base64Utils.decodeFromString(encrypted);
			String name = new String(decodedBytes);
			l.setPatientName(name);
			System.out.println(name);
			
			String encrypted1 = (String)o[1];
			byte[] decodedBytes1 = Base64Utils.decodeFromString(encrypted1);
			String lastName = new String(decodedBytes1);
			l.setPatientLastName(lastName);
			
			String encrypted2 = (String)o[2];
			byte[] decodedBytes2 = Base64Utils.decodeFromString(encrypted2);
			String DOB = new String(decodedBytes2);
			l.setPatientDOB(DOB);
			
			l.setCreatedOn((Date)o[3]);
			l.setLastInjection((String)o[4]);
			l.setProductname((String)o[5]);
//			System.out.println(Arrays.toString(l));
			result.add(l);
		}
		return result;
	}


}
