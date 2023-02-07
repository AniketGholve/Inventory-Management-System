package com.example.patient.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.patient.DAO.PatientDAO;
import com.example.patient.model.Patient;

@Service
public class PatientService {
	@Autowired
	PatientDAO patientDAO;
	
	public List<Patient> getAllPatient()
	{
		List<Patient> allPatientList=new ArrayList<>();
		try {
			allPatientList=patientDAO.findAll();
			return allPatientList;
		}catch(Exception e)
		{
			System.out.println(e);
			return allPatientList;
		}
	}
}
