package com.patient.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patient.Entity.Enterprises;

public interface EnterpriseRepo extends JpaRepository<Enterprises, Integer> {

}
