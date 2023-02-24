package com.patient.Service;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;

public interface EnterpriseService {
	
	
	private ResponseEntity<Enterprise> createEnterprise(Enterprise enterprise);
	
	private ResponseEntity<List<Enterprise>> getAllEnterprise();
	
	private ResponseEntity<Enterprise> updateEnterprise(Enterprise enterprise);
	
	private ResponseEntity<String> deleteenterprise(Integer enterpriseId);

}
