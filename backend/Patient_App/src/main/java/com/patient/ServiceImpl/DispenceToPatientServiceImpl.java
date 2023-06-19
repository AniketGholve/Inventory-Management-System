package com.patient.ServiceImpl;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.Entity.Clinic;
import com.patient.Entity.DispenseToPatient;
import com.patient.Entity.LastInjectionScreen;
import com.patient.Entity.OverdueMail;
import com.patient.Entity.Patient;
import com.patient.Entity.Product;
import com.patient.Entity.Serial;
import com.patient.Entity.SerialEventDesc;
import com.patient.Entity.UsageOverLastMonths;
import com.patient.Repo.ClinicRepo;
import com.patient.Repo.DispenseRepo;
import com.patient.Repo.PatientRepo;
import com.patient.Repo.ProductRepo;
import com.patient.Repo.SerialRepo;
import com.patient.Repo.UserEntityRepo;
import com.patient.Service.DispenceToPatientService;
import com.patient.Service.EmailSenderService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

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
	
	@Autowired
	private EmailSenderService emailsenderservice;
	
	@Autowired
	private ClinicRepo clinicRepo;
	
	@Autowired
	private UserEntityRepo userEntityRepo;

	@Override
	public Serial getProductBySerialNo(Integer serialNo) {
		// TODO Auto-generated method stub
		if(serialNo==null)return null;
		Query q=entityManager.createQuery("select s from Serial s where s.serialNumber=:u and s.serialStatus IN (:v,:y)");
		q.setParameter("u", serialNo);
		q.setParameter("v", "Available");
		q.setParameter("y", "Received");
		Query q1 = entityManager.createQuery("select se from SerialEventDesc se where se.serialNumber=:u");
		q1.setParameter("u", serialNo);
		Serial s = new Serial();
//		SerialEventDesc se = (SerialEventDesc) q1.getResultList().get(0);
//		if(q1.getResultList()==null) {
//			se=null;
//		}
//		else {
			try {
				 s=(Serial) q.getSingleResult();
//				 s.setSerialEventDesc(se);	 	
			}
			catch (Exception e) {
				// TODO: handle exception
				return null;
			}
//	}	 
		return s;
	}	
	
	@Transactional
	@Override
	public DispenseToPatient createDispence(DispenseToPatient dispenceToPatient,String UserName) {
		
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
		//MailDispense
				Query q1 = entityManager.createNativeQuery("select p.product_name from product p where p.product_id=?");
				q1.setParameter(1, d.getProductId());
				String productName = (String)q1.getSingleResult();
				
				Query q2 = entityManager.createNativeQuery("select name from clinic where location_id=?");
				q2.setParameter(1, d.getLocationId());
				String clinicName = (String)q2.getSingleResult();
				
//				Query q3 = entityManager.createNativeQuery("select username from user_entity where id=?");
//				q3.setParameter(1, UserName);
//				String UserMail = (String)q3.getSingleResult();
				
				Patient pa = patientRepo.findById(d.getPatientId()).orElseThrow();
				String patientName = pa.getPatientFirstName();
				
				Serial s = serialRepo.findById(d.getSerialId()).orElseThrow();
				s.setSerialStatus("Dispensed");
				serialRepo.save(s);
				
				System.out.println("Mail");
				emailsenderservice.sendDespenseMail(UserName, clinicName, patientName, productName);
				
				long m= System.currentTimeMillis();
				Date dat = new Date(m);
				Timestamp time = new Timestamp(m);
				System.out.println("Time");
				System.out.println(time);
				Query q3 = entityManager.createNativeQuery("INSERT INTO serial_event_desc (`serial_id`, `serial_number`, `event_date`, `status`, `product_id`) VALUES (?, ?, ?, ?, ?)");
				q3.setParameter(1, d.getSerialId());
				q3.setParameter(2, s.getSerialNumber());
				q3.setParameter(3, time);
				q3.setParameter(4, "Dispensed");
				q3.setParameter(5, d.getProductId());
				q3.executeUpdate();
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

	@Transactional
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
		
		Query q = entityManager.createNativeQuery("select on_hand from inventory where location_id=? and product_id=?");
		int onHand = (int)q.getSingleResult();
		Query q2 = entityManager.createNativeQuery("update inventory set on_hand =? where location_id=? and product_id=?");
		q2.setParameter(1, onHand-1);
		
		Serial s = serialRepo.findById(serialId).orElseThrow();
		int serialNo = s.getSerialNumber();
		
		
		return dtp;
		
	}


	@Override
	public Product getProductBySerialId(Integer serialId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<LastInjectionScreen> getAllDispense(int locationId,String UserMail) {
		// TODO Auto-generated method stub
		Query q = entityManager.createQuery("select pa.patientFirstName,pa.patientLastName,pa.patientDob,dp.createdOn,dp.nextInjection,p.productName from DispenseToPatient dp inner join Product p on dp.productId = p.productId inner join Patient pa on pa.id = dp.patientId where STR_TO_DATE(dp.nextInjection,'%Y-%m-%d') < CURRENT_DATE and dp.locationId =:u order by dp.nextInjection");//CURRENT_DATE <=3
		q.setParameter("u", locationId);
 		List<Object[]> list = q.getResultList();
		List<LastInjectionScreen> result = new ArrayList<>();
		for(Object[] o:list) {
			LastInjectionScreen l = new LastInjectionScreen();
			
			String name = (String)o[0];
			l.setPatientName(name);
			
			String lastName = (String)o[1];
			l.setPatientLastName(lastName);
			
			Date DOB = (Date)o[2];
			l.setPatientDOB(DOB);
			
 			l.setCreatedOn((Date)o[3]);
			System.out.println(o[4].getClass());
			l.setLastInjection((String)o[4]);
			l.setProductname((String)o[5]);
			l.setFlag(false);
			
			result.add(l);
		}
		Query q2 = entityManager.createQuery("select pa.patientFirstName,pa.patientLastName,pa.patientDob,dp.createdOn,dp.nextInjection,p.productName from DispenseToPatient dp inner join Product p on dp.productId = p.productId inner join Patient pa on pa.id = dp.patientId where STR_TO_DATE(dp.nextInjection,'%Y-%m-%d') >= CURRENT_DATE and STR_TO_DATE(dp.nextInjection,'%Y-%m-%d') <= CURRENT_DATE+7 and dp.locationId =:u order by dp.nextInjection");//CURRENT_DATE <=3
		q2.setParameter("u", locationId);
		List<Object[]> list2 = q2.getResultList();
		for(Object[] o : list2) {
			LastInjectionScreen l = new LastInjectionScreen();
			
			String name = (String)o[0];
			l.setPatientName(name);
			
			String lastName = (String)o[1];
			l.setPatientLastName(lastName);
			
			Date DOB = (Date)o[2];
			l.setPatientDOB(DOB);
			
 			l.setCreatedOn((Date)o[3]);
			System.out.println(o[4].getClass());
			l.setLastInjection((String)o[4]);
			l.setProductname((String)o[5]);
			l.setFlag(true);
 
			result.add(l);
		}
		
		
		return result;
	}


	@Override
	public List<UsageOverLastMonths> getAllUsedDoses() {
		// TODO Auto-generated method stub
		Query q = entityManager.createNativeQuery("SELECT\r\n"
				+ "CAST(SUM(CASE WHEN MONTH(created_on) = 10 THEN 1 ELSE 0 END) AS UNSIGNED) as oct_count,\r\n"
				+ "CAST(SUM(CASE WHEN MONTH(created_on) = 11 THEN 1 ELSE 0 END) AS Unsigned) as Nov_count,\r\n"
				+ "CAST(SUM(CASE WHEN MONTH(created_on) = 12 THEN 1 ELSE 0 END) AS Unsigned) AS Dec_count,\r\n"
				+ "CAST(SUM(CASE WHEN MONTH(created_on) = 1 THEN 1 ELSE 0 END) AS Unsigned) AS Jan_count,\r\n"
				+ "CAST(SUM(CASE WHEN MONTH(created_on) = 2 THEN 1 ELSE 0 END) AS Unsigned) AS Feb_count,\r\n"
				+ "CAST(SUM(CASE WHEN MONTH(created_on) = 3 THEN 1 ELSE 0 END) AS Unsigned) AS Mar_count\r\n"
				+ "FROM\r\n"
				+ "dispense_to_patient\r\n"
				+ "WHERE\r\n"
				+ "created_on >= DATE_SUB(CURDATE(), INTERVAL 6 MONTH)\r\n"
				+ "GROUP BY\r\n"
				+ "product_id");
		List<Object[]> list = q.getResultList();
		List<UsageOverLastMonths> result = new ArrayList<>();
		for(Object[] o:list) {
			UsageOverLastMonths u = new UsageOverLastMonths();
			System.out.println(o[0]);
			u.setUsgaeInOctober((long)o[0]);
			u.setUsageInNovember((long)o[1]);
			u.setUsageInDecember((long)o[2]);
			u.setUsageInJanuary((long)o[3]);
			u.setUsageInFebruary((long)o[4]);
			u.setUsageInMarch((long)o[5]);
			result.add(u);
		}
		
		return result;
	}


	@Override
	public Patient getPatientByPatientId(int PatientSpecific) {
		// TODO Auto-generated method stub
		Patient p = patientRepo.findById(PatientSpecific).orElseThrow();
		return p;
	}


	@Override
	public List<LastInjectionScreen> getAllDispenseNext30Days(int locationId) {
		Query q2 = entityManager.createQuery("select pa.patientFirstName,pa.patientLastName,pa.patientDob,dp.createdOn,dp.nextInjection,p.productName,pa.id from DispenseToPatient dp inner join Product p on dp.productId = p.productId inner join Patient pa on pa.id = dp.patientId where STR_TO_DATE(dp.nextInjection,'%Y-%m-%d') >= CURRENT_DATE+7 and STR_TO_DATE(dp.nextInjection,'%Y-%m-%d') <= CURRENT_DATE+30 and dp.locationId =:u order by dp.nextInjection");//CURRENT_DATE <=3
		q2.setParameter("u", locationId);
		List<LastInjectionScreen> result = new ArrayList<>();
		List<Object[]> list2 = q2.getResultList();
		for(Object[] o : list2) {
			LastInjectionScreen l = new LastInjectionScreen();
			
			String name = (String)o[0];
			l.setPatientName(name);
			
			String lastName = (String)o[1];
			l.setPatientLastName(lastName);
			
			Date DOB = (Date)o[2];
			l.setPatientDOB(DOB);
			
 			l.setCreatedOn((Date)o[3]);
			System.out.println(o[4].getClass());
			l.setLastInjection((String)o[4]);
			l.setProductname((String)o[5]);
			l.setId((Integer)o[6]);
 
			result.add(l);
		}
		
		
		return result;
	}


	@Override
//	@Scheduled(cron = "0 0 12 * * MON-FRI")
	public OverdueMail OverdueMail() {
		// TODO Auto-generated method stub
		Query q = entityManager.createQuery("select pa.patientFirstName,pa.patientLastName,pa.patientDob,dp.createdOn,dp.nextInjection,p.productName,pa.locationId from DispenseToPatient dp inner join Product p on dp.productId = p.productId inner join Patient pa on pa.id = dp.patientId where STR_TO_DATE(dp.nextInjection,'%Y-%m-%d') < CURRENT_DATE");//CURRENT_DATE <=3
		List<Object[]> list = q.getResultList();
		Query q1 = entityManager.createNativeQuery("select u.Username from User_entity u where u.role =?");
		q1.setParameter(1, "CLP");
		List<Object[]> list1 = new ArrayList<>();
		String[] mails = new String[list1.size()];
		for(int i=0;i<list1.size();i++) {
			mails
			[i] = list1.get(i).toString();
		}
		for(Object[] o:list) {			
			String patientname = (String)o[0];						
			String NextInjection = (String)o[4];
			String Dose = (String)o[5];
			Clinic c = clinicRepo.findById((Integer)o[6]).orElseThrow();
			String clinicName = c.getName();
			
			emailsenderservice.sendOverdueMail(mails, clinicName, patientname, Dose, NextInjection);
			
		}
		return null;
	}


}
