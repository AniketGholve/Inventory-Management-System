package com.patient.Service;

import java.util.List;

import com.patient.Entity.Physician;


public interface PhysicianService {
	
	public Physician createPhysician(Physician physician);
	
	public List<Physician> getAllPhysicians();
	
}
