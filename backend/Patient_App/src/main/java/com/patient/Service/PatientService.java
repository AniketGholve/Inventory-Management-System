package com.patient.Service;

import java.util.List;

import com.patient.Entity.Patient;

public interface PatientService {
	
	public Patient createPatient(Patient patient);
	
	public List<Patient> getAllPatient();
	
	public Object updatePatient(Patient p);

	
	public Object getPatientById(String pid);
	
	public String deletePatient(int pid);
	
	
	public List<Patient> getPatientByLocationId(Integer clinicLocationId);
	
	public List<Patient> getPatientsByName(String paientName);
	
	//public String getPatientByIdJPQL(int pid);
	
	//public List<Patient> getAllPatientByQuery();
	
	//public Patient updatePatientByQuery();
	
	//public Patient insertPatientByQuery(Patient patient);

}
