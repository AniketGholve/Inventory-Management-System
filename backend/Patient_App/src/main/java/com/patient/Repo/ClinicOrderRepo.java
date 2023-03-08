package com.patient.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patient.Entity.ClinicOrder;

public interface ClinicOrderRepo extends JpaRepository<ClinicOrder, Integer> {
	
	
}
