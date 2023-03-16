package com.patient.Repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.patient.Entity.DispenceToPatient;
public interface DispenceRepo extends JpaRepository<DispenceToPatient, Integer> {

}
