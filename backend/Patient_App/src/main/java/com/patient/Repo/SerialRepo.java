package com.patient.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.patient.Entity.Serial;

@Repository
public interface SerialRepo  extends JpaRepository<Serial, Integer>{
	
	public Serial findBySerialNumber(int serialNo);
}
