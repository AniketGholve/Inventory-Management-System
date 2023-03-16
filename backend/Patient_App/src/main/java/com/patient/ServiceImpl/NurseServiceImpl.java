package com.patient.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.Entity.Nurse;
import com.patient.Repo.NurseRepo;
import com.patient.Service.NurseService;
@Service
public class NurseServiceImpl implements NurseService {
	
	@Autowired
	NurseRepo nurseRepo;
	
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

}
