package com.patient.ServiceImpl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.Entity.DispenseToPatient;
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
	public Product getProductBySerialNo(Integer serialNo) {
		// TODO Auto-generated method stub
		Serial s=serialRepo.findBySerialNumber(serialNo);
		Product p=productRepo.findById(s.getProductId()).orElseThrow();
		return p;
	}
	

	@Override
	public DispenseToPatient createDispence(DispenseToPatient dispenceToPatient) {
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
		dispenseToPatient.setId(p);
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


}
