package com.patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patient.Entity.ClinicOrder;
import com.patient.ServiceImpl.ClinicOrderServiceImpl;

@RestController
public class ClinicOrderController {
	
	@Autowired
	private ClinicOrderServiceImpl clinicOrderServiceImpl;
	
	@PostMapping("/createOrder")
	public ClinicOrder createOrder(ClinicOrder clinicOrder) 
	{
		return clinicOrderServiceImpl.createOrder(clinicOrder);
	}
	
	
//	@GetMapping("/getAllOrders/{locId}")
//	public List<ClinicOrder> getAllOrders(@PathVariable("locId") Integer locId)
//	{
//		return clinicOrderServiceImpl.getAllOrdersById(locId);
//	}
		
	
}
