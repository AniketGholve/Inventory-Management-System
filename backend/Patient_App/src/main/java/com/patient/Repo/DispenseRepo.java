package com.patient.Repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.patient.Entity.DispenseToPatient;
public interface DispenseRepo extends JpaRepository<DispenseToPatient, Integer> {

}
