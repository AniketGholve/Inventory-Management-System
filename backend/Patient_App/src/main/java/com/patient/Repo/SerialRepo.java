package com.patient.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patient.Entity.Serial;

public interface SerialRepo  extends JpaRepository<Serial, Integer>{

}
