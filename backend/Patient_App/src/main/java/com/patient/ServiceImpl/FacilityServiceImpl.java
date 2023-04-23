package com.patient.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.Entity.Facility;
import com.patient.Repo.FacilityRepo;
import com.patient.Service.FacilityService;

import jakarta.persistence.EntityManager;

@Service
public class FacilityServiceImpl implements FacilityService {
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private FacilityRepo facilityRepo;

	@Override
	public List<Facility> getAllFacilityByEnterpriseId(int enterpriseId) 
	{
		List<Facility> list = facilityRepo.findByEnterpriseId(enterpriseId);
		return list;
	}

	@Override
	public Facility editFacility(Facility facility) {
		// TODO Auto-generated method stub
		Facility f = facilityRepo.findById(facility.getFacilityId()).orElseThrow();
		f.setAddress(facility.getAddress());
		f.setCity(facility.getCity());
//		f.setEnterpriseId(facility.getEnterpriseId());
		f.setEnterpriseId(f.getEnterpriseId());
		f.setFacilityName(facility.getFacilityName());
		f.setLocationId(facility.getLocationId());
		f.setState(facility.getState());
		f.setZipcode(facility.getZipcode());
		facilityRepo.save(f);
		return f;
	}

	@Override
	public void deleteById(int facilityId) {
		// TODO Auto-generated method stub
		facilityRepo.deleteById(facilityId);
	}

	@Override
	public Facility addFacility(Facility facility,int enterpriseId) {
		// TODO Auto-generated method stub
		Facility f = new Facility();
//		f.setEnterpriseId(enterpriseId);
		f = facilityRepo.save(facility);
		
		return f;
	}

	@Override
	public Facility getFacilityById(int id) {
		// TODO Auto-generated method stub
		Facility f = facilityRepo.findById(id).orElseThrow();
		return f;
	}

}
