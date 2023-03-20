package com.patient.ServiceImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.Entity.Clinic;
import com.patient.Entity.Patient;
import com.patient.Repo.PatientRepo;
import com.patient.Service.PatientService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Service
public class PatientServiceImpl implements PatientService {
	
	@Autowired
	private  PatientRepo patientRepo;
	
	@PersistenceContext
	private EntityManager entityManager;

	public Patient createPatient(Patient patient) {
		// TODO Auto-generated method stub
		patient.setPatientLocationId(10);
		patient.setPatientEnterpriseId(20);
		patient.setPatientLastDispenseId(30);
		patient.setPatientModifiedOn(null);
		patient.setPatientSrcId(40);
		long m=System.currentTimeMillis();
		Date d=new Date(m);
		patient.setPatientCreatedOn(d);
		Patient p=patientRepo.save(patient);
		//Patient p=patientRepo.insertData(patient);
		return p;
	}

	
	public List<Patient> getAllPatient() {
		// TODO Auto-generated method stub
		List<Patient> l=patientRepo.findAll();
		return l;
	}
	
	
	public Object updatePatient(Patient p)
	{
		String pid=p.getPatientId();
		Query q=entityManager.createQuery("select p from Patient p where p.patientId=:u");
		q.setParameter("u", pid);
		Patient patient=(Patient) q.getSingleResult();
		if(patient==null) return "patient with given data not exists";
		long m=System.currentTimeMillis();
		Date d=new Date(m);
		patient.setPatientFirstName(p.getPatientFirstName());
		patient.setPatientLastName(p.getPatientLastName());
		patient.setPatientMiddleName(p.getPatientMiddleName());
		patient.setPatientDob(p.getPatientDob());
		patient.setPatientStatus(p.getPatientStatus());
		patient.setPatientEmail(p.getPatientEmail());
		patient.setPatientModifiedOn(d);
		patient.setPatientPayerType(p.getPatientPayerType());
		patient.setPatientPaNeeded(p.getPatientPaNeeded());
		patient.setIndependentInventory(p.getIndependentInventory());
		Patient updatedPatient=patientRepo.save(patient);
		return updatedPatient;

	}
	
	public String deletePatient(int pid) {
		
		patientRepo.deleteById(pid);
		return "patient deleted successfully";
		
	}
	
	
	public Object getPatientById(String pid)
	{
		
		Query q=entityManager.createQuery("select p from Patient p where p.patientId=:u");
		q.setParameter("u", pid);
		//Patient patient=(Patient) q.getSingleResult();
		List<?> l=q.getResultList();
		if(l.size()==0) return null;
		Patient p=(Patient) l.get(0);
		return p;
	}


	@Override
	public List<Patient> getPatientByLocationId(Integer clinicLocationId) {
		// TODO Auto-generated method stub
		Query q=entityManager.createQuery("select p.patientId,p.patientFirstName,p.patientLastName,p.patientMiddleName,p.patientDob,p.patientStatus,c.name,p.patientEnterpriseId,p.id from Patient p inner join Clinic c on p.patientLocationId=c.locationId where p.patientLocationId=:u" );
		q.setParameter("u", clinicLocationId);
		List<Object[]> l=q.getResultList();
		List<Patient> resultList=new ArrayList<>();
		for(Object[] o:l) {
			Patient p=new Patient();
			p.setPatientId((String)o[0]);
			p.setPatientFirstName((String)o[1]);
			p.setPatientLastName((String)o[2]);
			p.setPatientMiddleName((String)o[3]);
			p.setPatientDob((Date)o[4]);
			p.setPatientStatus((String)o[5]);
			p.setClinicName((String)o[6]);
			p.setPatientEnterpriseId((Integer)o[7]);
			p.setId((Integer)o[8]);
			resultList.add(p);
		}
		return resultList;
	}


	



}
