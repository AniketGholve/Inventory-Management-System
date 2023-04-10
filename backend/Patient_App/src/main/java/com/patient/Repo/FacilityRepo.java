package com.patient.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patient.Entity.Facility;

public interface FacilityRepo extends JpaRepository<Facility, Integer> {
	
	public List<Facility> findByEnterpriseId(int enterpriseId);
}
