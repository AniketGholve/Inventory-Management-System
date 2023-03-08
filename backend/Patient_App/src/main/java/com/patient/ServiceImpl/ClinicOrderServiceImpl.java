package com.patient.ServiceImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.Entity.Clinic;
import com.patient.Entity.ClinicOrder;
import com.patient.Repo.ClinicOrderRepo;
import com.patient.Repo.ClinicRepo;
import com.patient.Service.ClinicOrderService;
import com.patient.controller.ClinicController;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Service
public class ClinicOrderServiceImpl implements ClinicOrderService {
	
	@Autowired
	private ClinicOrderRepo clinicOrderRepo;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private ClinicRepo clinicRepo;
	
	
	@Override
	public ClinicOrder createOrder(Integer locationId) {
		// TODO Auto-generated method stub
		long m=System.currentTimeMillis();
		Date d=new Date(m);
		Clinic clinic=clinicRepo.findById(locationId).orElseThrow();
		ClinicOrder clinicOrder=new ClinicOrder();
		clinicOrder.setActivityDate(d);
		clinicOrder.setBilltoId(123);
		clinicOrder.setBilltoName(clinic.getBillTo());
		clinicOrder.setEnterpriseId(1);
		clinicOrder.setLocationId(locationId);
		clinicOrder.setMeu(null);
		clinicOrder.setOrderDatetime(d);
		clinicOrder.setOrderId(12345);
		clinicOrder.setOrderNote(null);
		clinicOrder.setOrderStatusId(123);
		clinicOrder.setOrderType(null);
		clinicOrder.setPersonInitial(null);
		clinicOrder.setPoNumber(clinic.getOrderPoNumber());
		clinicOrder.setShipfromId(0);
		clinicOrder.setShiptoId(0);
		clinicOrder.setShiptoName(null);
		clinicOrder.setSrcId(123);
		clinicOrder.setUserId(0);
		ClinicOrder c=clinicOrderRepo.save(clinicOrder);
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
