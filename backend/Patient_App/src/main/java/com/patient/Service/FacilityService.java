package com.patient.Service;

import java.util.List;

import com.patient.Entity.Facility;

public interface FacilityService {
	
	public List<Facility> getAllFacilityByEnterpriseId(int enterpriseId);
	
	public Facility editFacility(Facility facility);
	
	public void deleteById(int id);
	
	public Facility addFacility(Facility facility);
	
	public Facility getFacilityById(int id);
}
