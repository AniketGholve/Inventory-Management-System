package com.patient.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patient.Entity.Clinic;

public interface ClinicRepo extends JpaRepository<Clinic, Integer> {

}
