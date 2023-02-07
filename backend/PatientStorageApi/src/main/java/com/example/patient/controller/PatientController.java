package com.example.patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.patient.model.Patient;
import com.example.patient.service.PatientService;

@RestController
@CrossOrigin
public class PatientController {
	@Autowired
	PatientService patientService;
	
	@GetMapping("/getAllPatientList")
	public List<Patient> getPatientList()
	{
		List<Patient> listOfAllPatient=patientService.getAllPatient();
		return listOfAllPatient;
	}
}
