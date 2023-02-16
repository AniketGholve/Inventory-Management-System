package com.patient.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patient.Entity.PatientFile;

public interface PatientFileRepo extends JpaRepository<PatientFile, String> {
	
	

}
