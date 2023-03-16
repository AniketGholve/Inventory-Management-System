package com.patient.Service;

import java.util.List;
import java.util.Optional;

import com.patient.Entity.Physician;


public interface PhysicianService {
	
	public Physician createPhysician(Physician physician);
	
	public List<Physician> getAllPhysicians();
	
	public String deletePhysician(int id);
	
	public Physician EditPhysician(Physician physician);
	
	public Physician getPhysician(int id);
}
