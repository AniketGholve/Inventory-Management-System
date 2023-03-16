package com.patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.patient.Entity.Nurse;
import com.patient.Entity.Physician;
import com.patient.ServiceImpl.NurseServiceImpl;

@RestController
@CrossOrigin
public class NurseController {
	
	@Autowired
	NurseServiceImpl nurseServiceImpl;
	
	@PostMapping("/createNurse")
	public ResponseEntity<Nurse> createNurse(@RequestBody Nurse nurse){
		Nurse n = nurseServiceImpl.createNurse(nurse);
		return new ResponseEntity<Nurse>(n,HttpStatus.CREATED);
		
	}
	
	@GetMapping("/getAllNurse")
	public ResponseEntity<List<Nurse>> getAllNurse(){
		List<Nurse> list = nurseServiceImpl.getAllNurse();
		return new ResponseEntity<List<Nurse>>(list,HttpStatus.ACCEPTED);
		
	}
	
	@PostMapping("/deleteNurse/{id}")
	public String deletePhysician(@PathVariable int id) {
		nurseServiceImpl.deleteNurse(id);
		return "Deleted Succesfully";
	}
}
