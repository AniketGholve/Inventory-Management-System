package com.patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patient.Entity.ClinicOrder;
import com.patient.ServiceImpl.ClinicOrderServiceImpl;

@RestController
@CrossOrigin
public class ClinicOrderController {
	
	@Autowired
	private ClinicOrderServiceImpl clinicOrderServiceImpl;
	
	@PostMapping("/createOrder/{locationId}")
	public ResponseEntity<ClinicOrder> createOrder(@PathVariable Integer locationId) 
	{
		ClinicOrder result=clinicOrderServiceImpl.createOrder(locationId);
		return new ResponseEntity<ClinicOrder>(result,HttpStatus.OK);
	}
	

	@GetMapping("/getAllOrders/{locId}")
	public List<ClinicOrder> getAllOrders(@PathVariable("locId") Integer locId)
	{
		return clinicOrderServiceImpl.getAllOrdersById(locId);
	}

}
