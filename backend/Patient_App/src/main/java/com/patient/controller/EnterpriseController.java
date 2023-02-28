package com.patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.patient.Entity.Enterprises;
import com.patient.ServiceImpl.EnterpriseServiceImpl;

@RestController
public class EnterpriseController {
	
	
	@Autowired
	private EnterpriseServiceImpl enterpriseserviceImpl;
	
	
	
	@PostMapping("/createEnterprises")
	public ResponseEntity<Enterprises> createEnterprise(@RequestBody Enterprises enterprise)
	{
		Enterprises e=enterpriseserviceImpl.createEnterprises(enterprise);
		
		return new ResponseEntity<Enterprises>(e,HttpStatus.CREATED);
	}

	
	@PutMapping("/updateEnterprise")
	public ResponseEntity<Enterprises> updateEnterprises(@RequestBody Enterprises enterprises)
	{
		Enterprises e=enterpriseserviceImpl.updateEnterprise(enterprises);
		return new ResponseEntity<Enterprises>(e,HttpStatus.OK);
	}
	
	
	
	@GetMapping("/getAllEnterprise")
	public ResponseEntity<List<Enterprises>> getAllEnterprises()
	{
		List<Enterprises> listEnterprises=enterpriseserviceImpl.getAllEnterprises();
		return new ResponseEntity<List<Enterprises>>(listEnterprises,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/deleteEnterprise/{enterpriseId}")
	public ResponseEntity<String> deleteEnterpriseById(@PathVariable Integer enterpriseId)
	{
		String message=enterpriseserviceImpl.deleteEnterprise(enterpriseId);
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
}