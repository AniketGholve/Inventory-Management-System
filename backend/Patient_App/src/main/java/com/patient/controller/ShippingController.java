package com.patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.patient.Entity.Clinic;
import com.patient.ServiceImpl.ShippingServiceImpl;

@RestController
@CrossOrigin
public class ShippingController {
	
	@Autowired
	private ShippingServiceImpl shippingServiceImpl;
	
	
	@GetMapping("getAllShipToId")
	private ResponseEntity<List<Clinic>> getAllShipToId(){
		
		List<Clinic> result=shippingServiceImpl.getAllShipToId();
		return new ResponseEntity<List<Clinic>>(result,HttpStatus.OK);
		
		
		
	}
	
	@GetMapping("getShippingDataByShippingId/{shippingId}")
	private ResponseEntity<Clinic> getShippingDataByShippingId(@PathVariable String shippingId){
		Clinic result=shippingServiceImpl.getShippingDataByShippingId(shippingId);
		return new ResponseEntity<Clinic>(result,HttpStatus.OK);
	}
	 
}
