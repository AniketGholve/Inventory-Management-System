package com.patient.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.Entity.Physician;
import com.patient.Repo.PhysicianRepo;
import com.patient.Service.PhysicianService;
@Service
public class PhysicianServiceImpl implements PhysicianService{
	
	@Autowired
	PhysicianRepo physicianRepo;

	@Override
	public Physician createPhysician(Physician physician) {
		Physician p = physicianRepo.save(physician);
		return p;
	}

	@Override
	public List<Physician> getAllPhysicians() {
		List<Physician> l = physicianRepo.findAll();
		return l;
	}

}
