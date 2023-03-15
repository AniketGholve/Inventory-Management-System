package com.patient.ServiceImpl;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.Entity.Clinic;
import com.patient.Entity.ClinicOrder;
import com.patient.Entity.OrderEvents;
import com.patient.Entity.ScannedShipmentDetails;
import com.patient.Entity.Serial;
import com.patient.Repo.ClinicOrderRepo;
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
	private ClinicOrderRepo clinicOrderRepo;
	
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
		if(shippingToId==null) return null;
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
	public List<ClinicOrder> getprocessedorderEvents(Integer locationId) {
		Query q=entityManager.createNativeQuery("select * from order_events oe inner join clinic_order co on co.order_id=oe.order_id where oe.event_desc=? and oe.location_id=? group by co.order_id;");
		q.setParameter(1, "Processed");
		q.setParameter(2, locationId);
		
		List<Object []> l=q.getResultList();
		List<ClinicOrder> clinicOrderList=new ArrayList<>();
		for(Object [] o:l) {
			ClinicOrder clinicOrder=new ClinicOrder();
			clinicOrder.setOrderId((Integer)o[13]);
			clinicOrder.setLocationId((Integer)o[5]);
			clinicOrderList.add(clinicOrder);
		}
		return clinicOrderList;
	}





	@Override
	public List<Serial> getSerialByProductId(Integer orderId) {
		// TODO Auto-generated method stub
		System.out.println(orderId);
		List<Serial> result=new ArrayList<>();
		Query q1=entityManager.createNativeQuery("select product_id from order_events where order_id=?");
		q1.setParameter(1, orderId);
		List<Integer> productIdList=q1.getResultList();
 		for(Integer i:productIdList) {
			Query q2=entityManager.createQuery("select s from Serial s where s.productId=:u");
			q2.setParameter("u", i);
			List<Serial> r=q2.getResultList();
			for(Serial s:r) {
				result.add(s);
			}
		}
		return result;
	}





	@Override
	public List<ScannedShipmentDetails> getScannedShipmentDetails(Integer serialId, Integer productId,Integer orderId) {
		// TODO Auto-generated method stub
		System.out.println("llll");
		Query q1=entityManager.createNativeQuery("update order_events set event_desc=? where order_id=?");
		q1.setParameter(1, "Comissioned");
		q1.setParameter(2, orderId);
		q1.executeUpdate();
		Query q2=entityManager.createNativeQuery("select p.product_name,p.active from product p where p.product_id=?");
		q2.setParameter(1, productId); 	 	
		List<Object []> l=q2.getResultList();
		Integer k=1;
		List<ScannedShipmentDetails> list=new ArrayList<>();
		for (Object[] o:l) {
			ScannedShipmentDetails scannedShipmentDetails=new ScannedShipmentDetails();
			scannedShipmentDetails.setDose((String)o[0]);
			scannedShipmentDetails.setQuantity(k++);
			scannedShipmentDetails.setStatus("Comissioned");
			list.add(scannedShipmentDetails);	
		}
		return list;
	}





	public List<OrderEvents> shippedInventoryDetails(Integer locationId) {
		List<OrderEvents> findByLocationIdAndEventDesc = orderEventsRepo.findByLocationIdAndEventDesc(locationId, "Shipped");
		return findByLocationIdAndEventDesc;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	
	 

}
