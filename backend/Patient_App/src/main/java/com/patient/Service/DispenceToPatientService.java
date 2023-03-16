package com.patient.Service;

import com.patient.Entity.DispenceToPatient;
import com.patient.Entity.Product;

public interface DispenceToPatientService {
	
	
	public Product getProductBySerialId(Integer serialId);
	
	public DispenceToPatient createDispence(DispenceToPatient dispenceToPatient);

}
