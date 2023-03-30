package com.patient.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patient.Entity.Inventory;


public interface InventoryRepo extends JpaRepository<Inventory, Integer> { 
	
	public List<Inventory> findByOnHandLessThan(int onHand);
}
