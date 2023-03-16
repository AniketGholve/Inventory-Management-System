package com.patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.patient.Entity.Physician;
import com.patient.ServiceImpl.PhysicianServiceImpl;

@RestController
public class PhysicianController {
	
	@Autowired
	PhysicianServiceImpl physicianServiceImpl;
	
	@PostMapping("/createPhysician")
	public ResponseEntity<Physician> createPhysician(@RequestBody Physician physician){
		Physician ph = physicianServiceImpl.createPhysician(physician);
		return new ResponseEntity<Physician>(ph,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/getAllPhysicians")
	public ResponseEntity<List<Physician>> getAllPhysicians(){
		List<Physician> list = physicianServiceImpl.getAllPhysicians();
		return new ResponseEntity<List<Physician>>(list,HttpStatus.ACCEPTED);
		
	}
}
