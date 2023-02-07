package com.example.patient.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.patient.model.Patient;

@Component
public interface PatientDAO extends JpaRepository<Patient, Integer>  {

}
