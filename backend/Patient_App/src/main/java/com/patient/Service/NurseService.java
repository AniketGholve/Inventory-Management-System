package com.patient.Service;

import java.util.List;

import com.patient.Entity.Nurse;

public interface NurseService {
	
	public Nurse createNurse(Nurse nurse);
	
	public List<Nurse> getAllNurse();
	
	public String deleteNurse(int id);
	
	public Nurse editNurse(Nurse nurse);
	
	public Nurse getNurse(int id);
}
