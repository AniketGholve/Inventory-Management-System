package com.patient.ServiceImpl;

import java.util.List;
import java.util.Optional;

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

	@Override
	public String deletePhysician(int id) {
		// TODO Auto-generated method stub
		physicianRepo.deleteById(id);
		return "Deleted Successfully";
	}

	@Override
	public Physician EditPhysician(Physician physician) {
		// TODO Auto-	generated method stub
		Physician p = physicianRepo.findById(physician.getId()).orElseThrow();
		p.setFirstName(physician.getFirstName());
		p.setLastName(physician.getLastName());
		p.setMiddleName(physician.getMiddleName());
		p.setActive(true);
		p.setCreatedOn(physician.getCreatedOn());
		p.setEnterpriseId(physician.getEnterpriseId());
		p.setLocationId(physician.getLocationId());
		p.setModifiedOn(physician.getModifiedOn());
		p.setOccupationType(physician.getOccupationType());
		p.setSrcId(physician.getSrcId());
		return p;
	}

	@Override
	public Physician getPhysician(int id) {
		Physician p = physicianRepo.findById(id).orElseThrow();
		return p;
	}

}
