package com.patient.Service;

import java.util.List;

import com.patient.Entity.DispenseToPatient;
import com.patient.Entity.LastInjectionScreen;
import com.patient.Entity.Patient;
import com.patient.Entity.OverdueMail;
import com.patient.Entity.Product;
import com.patient.Entity.Serial;
import com.patient.Entity.UsageOverLastMonths;

public interface DispenceToPatientService {
	
	
	public Product getProductBySerialId(Integer serialId);
	
	public DispenseToPatient createDispence(DispenseToPatient dispenceToPatient,String UserName);
	
	public List<Patient> getPatientsByName(String paientName);
	
	
	public DispenseToPatient addDispense(Integer Id,Integer nurseId,Integer physicianId,Integer productId,Integer serialId,Integer locationId,String injectionSite);

	public Serial getProductBySerialNo(Integer serialNo);
	
	public List<LastInjectionScreen> getAllDispense(int locationId,String UserMail);
	
	public List<UsageOverLastMonths> getAllUsedDoses();
	
	public Patient getPatientByPatientId(int PatientSpecific);

	public List<LastInjectionScreen> getAllDispenseNext30Days(int locationId);
	
	public OverdueMail OverdueMail();

}
