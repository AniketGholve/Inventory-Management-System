package com.patient.Service;

import java.util.List;

import com.patient.Entity.Clinic;

 
public interface ClinicService {
	
		public Clinic createClinic(Clinic clinic);
	
		public List<Clinic> getAllClinic();
	
		public Clinic updateClinic(Clinic clinic);
	
		public String deleteClinic(Integer locationId);
		
		public Clinic getClinicById(Integer locationId);

}
