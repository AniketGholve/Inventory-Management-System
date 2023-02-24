package com.patient.Service;

import java.util.List;

 
import com.patient.Entity.Enterprises;

public interface EnterpriseService {
	
	
	public Enterprises createEnterprises(Enterprises enterprises);
	
	public List<Enterprises> getAllEnterprises();
	
	public Enterprises updateEnterprise(Enterprises enterprises);
	
	public String deleteEnterprise(Integer enterpriseId);

}
