package com.patient.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.Entity.ClinicOrder;
import com.patient.Repo.ClinicOrderRepo;
import com.patient.Service.ClinicOrderService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
@Service
public class ClinicOrderServiceImpl implements ClinicOrderService {
	
	@Autowired
	private ClinicOrderRepo clinicOrderRepo;
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public ClinicOrder createOrder(ClinicOrder clinicOrder) {
		// TODO Auto-generated method stub
		ClinicOrder c = clinicOrderRepo.save(clinicOrder);
		return c;
	}

	@Override
	public List<ClinicOrder> getAllOrdersById(Integer locId) {
		
		Query q = entityManager.createQuery("select co from clinic_order co where co.location_id=:u");
		q.setParameter("u", locId);
		List<ClinicOrder> l = q.getResultList();
		return l;
	}

	

	

	

	

}
