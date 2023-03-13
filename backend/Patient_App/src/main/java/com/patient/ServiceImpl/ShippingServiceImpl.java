package com.patient.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.Entity.Clinic;
import com.patient.Entity.OrderEvents;
import com.patient.Entity.Serial;
import com.patient.Repo.ClinicRepo;
import com.patient.Repo.OrderEventsRepo;
import com.patient.Repo.SerialRepo;
import com.patient.Service.ShippingService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
@Service
public class ShippingServiceImpl implements ShippingService {
	
	
	@Autowired
	private SerialRepo serialRepo;
	
	@Autowired
	private ClinicRepo clinicRepo;
	
	@Autowired
	private OrderEventsRepo orderEventsRepo;
	
	@Autowired
	private EntityManager entityManager;
	
	
	
	
	
	@Override
	public List<Clinic> getAllShipToId() {
		// TODO Auto-generated method stub
		List<Clinic> clinicList=clinicRepo.findAll();
		return clinicList;
	}





	@Override
	public Clinic getShippingDataByShippingId(String shippingToId) {
		// TODO Auto-generated method stub
		Query q=entityManager.createQuery("select c from Clinic c where c.shipTo=:u");
		q.setParameter("u",shippingToId );
		Clinic clinic= (Clinic) q.getSingleResult();
		return clinic;
	}





	@Override
	public List<OrderEvents> getprocessedorderEvents() {
		// TODO Auto-generated method stub
		List<OrderEvents> orderEventList=orderEventsRepo.findAll();
		return orderEventList;
	}
	
	
	


	
	 

}
