package com.patient.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.Entity.Nurse;
import com.patient.Repo.NurseRepo;
import com.patient.Service.NurseService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
@Service
public class NurseServiceImpl implements NurseService {
	
	@Autowired
	NurseRepo nurseRepo;
	
	@Autowired
	EntityManager entityManager;
	
	@Override
	public Nurse createNurse(Nurse nurse) {
		// TODO Auto-generated method stub
		Nurse n = nurseRepo.save(nurse);
		return n;
	}

	@Override
	public List<Nurse> getAllNurse() {
		// TODO Auto-generated method stub
		List<Nurse> list = nurseRepo.findAll();
		return list;
	}

	@Override
	public String deleteNurse(int id) {
		// TODO Auto-generated method stub
		nurseRepo.deleteById(id);
		return "Deleted Succesfully";
	}

	@Override
	public Nurse editNurse(Nurse nurse) {
		// TODO Auto-generated method stub
		Nurse n = nurseRepo.findById(nurse.getId()).orElseThrow();
		return n;
	}

	@Override
	public Nurse getNurse(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Nurse> getAllNurseByLocId(int locationId) {
		// TODO Auto-generated method stub
		Query q= entityManager.createNativeQuery("select n from Nurse n where location_id=?");
		q.setParameter(1, locationId);
		List<Nurse> list = q.getResultList();
		return list;
	}

}
