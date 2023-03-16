package com.patient.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patient.Entity.Nurse;

public interface NurseRepo extends JpaRepository<Nurse, Integer> {

	
}
