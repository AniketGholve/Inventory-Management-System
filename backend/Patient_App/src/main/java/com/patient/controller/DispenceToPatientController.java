package com.patient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.patient.Entity.Product;
import com.patient.ServiceImpl.DispenceToPatientServiceImpl;
@RestController
@CrossOrigin
public class DispenceToPatientController {
	
	@Autowired
	private DispenceToPatientServiceImpl dispenceToPatientServiceImpl;
	
	
	
	@GetMapping("/getProductBySerialId/{serialId}")
	public ResponseEntity<Product> getProductBySerialId(@PathVariable Integer serialId) {
		
		Product p=dispenceToPatientServiceImpl.getProductBySerialId(serialId);
		return new ResponseEntity<Product>(p,HttpStatus.OK);
		
	}

}