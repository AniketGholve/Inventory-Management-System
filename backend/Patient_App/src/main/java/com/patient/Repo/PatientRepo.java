package com.patient.Repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.patient.Entity.Patient;

import jakarta.persistence.EntityManager;

public interface PatientRepo extends JpaRepository<Patient, Integer>{
	
	
//	@Query("select p.patient_first_name from Patient p where p.id=?1")
//	public String getPatientByIdJPQL(int pid);
	
	
	

}
