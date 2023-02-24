package com.patient.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.patient.Repo.EnterpriseRepo;
import com.patient.Service.Enterprise;
import com.patient.Service.EnterpriseService;
import com.patient.Service.List;

public class EnterpriseServiceImpl implements EnterpriseService {
	
	
	
	@Autowired
	private EnterpriseRepo enterpriseRepo;
	
private Enterprise createEnterprise(Enterprise enterprise){
	Enterprise e=enterpriseRepo.save(enterprise)
	return e;
	
}
	
	private ResponseEntity<List<Enterprise>> getAllEnterprise();
	
	private ResponseEntity<Enterprise> updateEnterprise(Enterprise enterprise);
	
	private ResponseEntity<String> deleteenterprise(Integer enterpriseId);
	
	
	
	
	
	

}
