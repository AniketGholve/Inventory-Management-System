package com.patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.patient.Entity.Clinic;
import com.patient.ServiceImpl.ClinicServiceImpl;


@RestController
@CrossOrigin
public class ClinicController {
	
	@Autowired
	private ClinicServiceImpl clinicServiceImpl;
	
	
	
	@PostMapping("/createClinic")
	public ResponseEntity<Clinic> createClinic(@RequestBody Clinic clinic)
	{
		Clinic c=clinicServiceImpl.createClinic(clinic);
		
		return new ResponseEntity<Clinic>(c,HttpStatus.CREATED);
	}

	
	@PutMapping("/updateClinic")
	public ResponseEntity<Clinic> updateClinic(@RequestBody Clinic clinic)
	{
		Clinic c=clinicServiceImpl.updateClinic(clinic);
		return new ResponseEntity<Clinic>(c,HttpStatus.OK);
	}
	
	
	@GetMapping("/getByClinicId/{id}")
	public ResponseEntity<Clinic> getByClinicId(@PathVariable("id") int id)
	{
		Clinic c=clinicServiceImpl.getClinicById(id);
		return new ResponseEntity<Clinic>(c,HttpStatus.OK);
	}
	
	
	
	@GetMapping("/getAllClinic")
	public ResponseEntity<List<Clinic>> getAllClinic()
	{
		List<Clinic> listClinic=clinicServiceImpl.getAllClinic();
		return new ResponseEntity<List<Clinic>>(listClinic,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/deleteClinic/{locationId}")
	public ResponseEntity<String> deleteClinicById(@PathVariable Integer locationId)
	{
		String message=clinicServiceImpl.deleteClinic(locationId);
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
	
	
	
	
	@GetMapping("/getClinicNames")
	public ResponseEntity<List<Clinic>> getClinicNamesAndId()
	{
		List<Clinic> c=clinicServiceImpl.getClinicNamesAndId();
		return new ResponseEntity<List<Clinic>>(c,HttpStatus.OK);
		
	}
	
	
	 

}
