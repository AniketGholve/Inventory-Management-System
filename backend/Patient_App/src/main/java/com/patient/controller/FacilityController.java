package com.patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.patient.Entity.Facility;
import com.patient.ServiceImpl.FacilityServiceImpl;

@RestController
@CrossOrigin
public class FacilityController {
	
	@Autowired
	private FacilityServiceImpl facilityServiceImpl;
	
	@GetMapping("/getAllFacilityByEnterpriseId/{enterpriseId}")
	public List<Facility> getAllFacility(@PathVariable int enterpriseId)
	{
		List<Facility> list = facilityServiceImpl.getAllFacilityByEnterpriseId(enterpriseId);
		return list;
	}
	
	@PostMapping("/addFacility")
	public Facility addFacility(@RequestBody Facility facility)
	{
		Facility fac = facilityServiceImpl.addFacility(facility);
		return fac;
	}
	
	@PutMapping("/editFacility")
	public Facility editFacility(@RequestBody Facility facility) 
	{
		Facility f = facilityServiceImpl.editFacility(facility);
		return f;
	}
	
	@PostMapping("/deleteFacility/{facilityId}")
	public String deleteFacility(@PathVariable int facilityId) 
	{
		facilityServiceImpl.deleteById(facilityId);
		return "Facility Deleted Succesfully";
	}
}
