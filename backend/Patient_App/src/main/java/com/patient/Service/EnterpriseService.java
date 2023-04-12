package com.patient.Service;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.patient.Entity.Enterprises;
import com.patient.Entity.UserEntity;

public interface EnterpriseService {
	
	
	public Enterprises createEnterprises(Enterprises enterprises);
	
	public List<Enterprises> getAllEnterprises();
	
	public Enterprises updateEnterprise(Enterprises enterprises);
	
	public String deleteEnterprise(Integer enterpriseId);
	
//	public List<UserEntity> getAllUsersByEnterpriseId(int id);
 
}
