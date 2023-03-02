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
	public List<Patient> getPatientById(Integer clinicLocationId) {
		// TODO Auto-generated method stub
		Query q=entityManager.createNativeQuery("select p from Patient p where p.location_id=:u" );
		q.setParameter("u", clinicLocationId);
		List<Object[]> l=q.getResultList();
		List<Patient> resultList=new ArrayList<>();
		for(Object [] o:l)
		{
			Patient p=new Patient();
			p.setId((Integer)o[0]);
			p.setIndependentInventory((Boolean) o[1]);
			p.setPatientCreatedOn((Date) o[2]);
			p.setPatientDob((Date) o[3]);
			p.setPatientEmail((String) o[4]);
			p.setPatientEnterpriseId((Integer) o[5]);
			p.setPatientFirstName((String) o[6]);
			p.setPatientId((String) o[7]);
			p.setPatientLastDispenseId((Integer)o[8]);
			p.setPatientLastName((String)o[9]);
			p.setPatientLocationId((Integer)o[10]);
			p.setPatientMiddleName((String)o[11]);
			p.setPatientModifiedOn((Date)o[12]);
			p.setPatientPaNeeded((Boolean)o[13]);
			p.setPatientPayerType((String)o[14]);
			p.setPatientSrcId((Integer)o[15]);
			p.setPatientStatus((String)o[16]);
			resultList.add(p);
		}
		return resultList;
	}



}
