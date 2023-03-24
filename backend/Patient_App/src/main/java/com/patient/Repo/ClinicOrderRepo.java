package com.patient.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patient.Entity.ClinicOrder;

public interface ClinicOrderRepo extends JpaRepository<ClinicOrder, Integer> {

	List<ClinicOrder> findByOrderDatetime(String orderDate);
	
	
}
