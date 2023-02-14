package com.patient.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patient.Entity.Inventory;


public interface InventoryRepo extends JpaRepository<Inventory, Integer> { 

}
