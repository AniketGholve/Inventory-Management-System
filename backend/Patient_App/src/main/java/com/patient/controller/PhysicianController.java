package com.patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.patient.Entity.Physician;
import com.patient.ServiceImpl.PhysicianServiceImpl;

@RestController
@CrossOrigin
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
	
	@PostMapping("/deletePhysician/{id}")
	public String deletePhysician(@PathVariable int id) {
		physicianServiceImpl.deletePhysician(id);
		return "Deleted Succesfully";
	}
	
	@PutMapping("/EditPhysician")
	public ResponseEntity<Physician> EditPhysician(@RequestBody Physician physician){
		Physician ph = physicianServiceImpl.EditPhysician(physician);
		return new ResponseEntity<Physician>(ph,HttpStatus.OK);
		
	}
	
	@GetMapping("/getPhysician")
	public ResponseEntity<Physician> getPhysician(@PathVariable int id){
		Physician p = physicianServiceImpl.getPhysician(id);
		return new ResponseEntity<Physician>(p,HttpStatus.OK);
		
	}
	
	@GetMapping("/getAllPhysiciansByLocId/{locationId}")
	public ResponseEntity<List<Physician>> getAllPhysiciansByLocId(@PathVariable int locationId){
		List<Physician> list = physicianServiceImpl.getPhysicianByLocationId(locationId);
		return new ResponseEntity<List<Physician>>(list,HttpStatus.OK);
		
	}
}
