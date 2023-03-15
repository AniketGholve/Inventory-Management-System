package com.patient.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.Entity.Clinic;
import com.patient.Entity.Inventory;
import com.patient.Entity.OrderEvents;
import com.patient.Entity.ScannedShipmentDetails;
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
		Query q=entityManager.createQuery("select c from Clinic c where c.deleted=:u");
		q.setParameter("u", false);
		List<Clinic> clinicList=q.getResultList();
		return clinicList;
	}





	@Override
	public Clinic getShippingDataByShippingId(String shippingToId) {
		// TODO Auto-generated method stub
		Query q=entityManager.createQuery("select c from Clinic c where c.shipToName=:u");
		System.out.println(shippingToId);
		q.setParameter("u",shippingToId);
		Clinic clinic;
		try {
			 clinic= (Clinic) q.getSingleResult();
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
		return clinic;
	}





	@Override
	public List<OrderEvents> getprocessedorderEvents(Integer locationId) {
		// TODO Auto-generated method stub
		Query q=entityManager.createQuery("select oe from OrderEvents oe where oe.eventDesc=:u and locationId=:v");
		q.setParameter("u", "Processed");
		q.setParameter("v", locationId);
		List<OrderEvents> orderEventList=q.getResultList();
		return orderEventList;
	}





	@Override
	public List<Serial> getSerialByProductId(Integer productId) {
		// TODO Auto-generated method stub
		Query q=entityManager.createQuery("select s from Serial s where s.productId=:u");
		q.setParameter("u", productId);
		List<Serial> result=q.getResultList();
		return result;
	}





	@Override
	public List<ScannedShipmentDetails> getScannedShipmentDetails(Integer serialId, Integer productId,Integer orderEventId) {
		// TODO Auto-generated method stub
		Query q=entityManager.createNativeQuery("select p.product_name,oe.order_event_id,oe.event_desc from product p inner join order_events oe where p.product_id=oe.product_id and order_event_id=?");
		OrderEvents orderEvent=orderEventsRepo.findById(orderEventId).orElseThrow();
		orderEvent.setEventDesc("Shipped");
		orderEventsRepo.save(orderEvent);
		q.setParameter(1, orderEventId); 	 	
		List<Object []> l=q.getResultList();
		Integer k=1;
		List<ScannedShipmentDetails> list=new ArrayList<>();
		for (Object[] o:l) {
			ScannedShipmentDetails scannedShipmentDetails=new ScannedShipmentDetails();
			scannedShipmentDetails.setDose((String)o[0]);
			scannedShipmentDetails.setQuantity(k++);
			scannedShipmentDetails.setStatus((String)o[2]);
			list.add(scannedShipmentDetails);
		}
		return list;
	}





	public List<OrderEvents> shippedInventoryDetails(Integer locationId) {
		List<OrderEvents> findByLocationIdAndEventDesc = orderEventsRepo.findByLocationIdAndEventDesc(locationId, "Shipped");
		return findByLocationIdAndEventDesc;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	
	 

}
