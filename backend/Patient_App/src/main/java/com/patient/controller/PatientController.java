package com.patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.patient.Entity.Patient;
import com.patient.ServiceImpl.PatientServiceImpl;

import lombok.Delegate;

@RestController
@CrossOrigin
public class PatientController {
	
	@Autowired
	PatientServiceImpl patientServiceImpl;
	
	@PreAuthorize("hasAuthority('CLP')")
	@PostMapping("/createPatient")
	public ResponseEntity<Patient> createData(@RequestBody Patient patient)
	{
		
		Patient p=patientServiceImpl.createPatient(patient);
		return new ResponseEntity<Patient>(p,HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAuthority('CLP')")
	@GetMapping("/getAllData")
	public ResponseEntity<?> getData()
	{
		
		List<Patient> l=patientServiceImpl.getAllPatient();
		if(l.size()==0) return new ResponseEntity<String>("Data not exists in the database",HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Patient>>(l,HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAuthority('CLP')")
	@PutMapping("/updatePatient")
	public ResponseEntity<?> updatePatient(@RequestBody Patient p){
		
		Patient updatedPatient=(Patient) patientServiceImpl.updatePatient(p);
		return new ResponseEntity<Patient>(updatedPatient,HttpStatus.OK);
		
	}
	@PreAuthorize("hasAuthority('CLP')")
	@GetMapping("/getPatientById/{pid}")
	public ResponseEntity<?> getPatientById(@PathVariable("pid") String pid){
		Object p=(Patient) patientServiceImpl.getPatientById(pid);
		if(p==null) return new ResponseEntity<Object>("given id not exists",HttpStatus.OK);
		return new ResponseEntity<Object>(p,HttpStatus.OK);
	}
	
	
	
	@DeleteMapping("deletePatient/{id}")
	public ResponseEntity<String> deletePatient(@PathVariable("id") Integer id)
	{
		String s=patientServiceImpl.deletePatient(id);
		return new ResponseEntity<String>(s,HttpStatus.OK);
	}
	

	

}
