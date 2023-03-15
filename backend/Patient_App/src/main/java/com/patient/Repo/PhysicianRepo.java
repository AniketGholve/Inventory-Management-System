package com.patient.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patient.Entity.Physician;

public interface PhysicianRepo extends JpaRepository<Physician, Integer> {

}
