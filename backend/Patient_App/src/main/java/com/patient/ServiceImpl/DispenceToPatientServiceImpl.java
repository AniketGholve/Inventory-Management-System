package com.patient.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.Entity.DispenceToPatient;
import com.patient.Entity.Product;
import com.patient.Entity.Serial;
import com.patient.Repo.DispenceRepo;
import com.patient.Repo.ProductRepo;
import com.patient.Repo.SerialRepo;
import com.patient.Service.DispenceToPatientService;

@Service
public class DispenceToPatientServiceImpl implements DispenceToPatientService{
	
	@Autowired
	private SerialRepo serialRepo;
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private DispenceRepo dispenceRepo;

	@Override
	public Product getProductBySerialId(Integer serialId) {
		// TODO Auto-generated method stub
		Serial s=serialRepo.findById(serialId).orElseThrow();
		Product p=productRepo.findById(s.getProductId()).orElseThrow();
		return p;
	}

	@Override
	public DispenceToPatient createDispence(DispenceToPatient dispenceToPatient) {
		DispenceToPatient d = dispenceRepo.save(dispenceToPatient);
		return d;
	}

}
