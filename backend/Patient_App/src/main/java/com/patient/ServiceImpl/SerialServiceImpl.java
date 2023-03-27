package com.patient.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.Entity.OrderEvents;
import com.patient.Entity.Product;
import com.patient.Entity.Serial;
import com.patient.Repo.SerialRepo;
import com.patient.Service.SerialService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Service
public class SerialServiceImpl implements SerialService{
	
	@Autowired
	private SerialRepo serialRepo;
	
	@Autowired
	private EntityManager entityManager;

	@Transactional
	public Serial createSerial(Serial serial) {
		// TODO Auto-generated method stub
		 Serial s=serialRepo.save(serial);
		 Query q=entityManager.createNativeQuery("update inventory i set on_hand=(select Count(*) from serial s group by product_id having s.product_id=?) where i.product_id=?");
		 q.setParameter(1, serial.getProductId());
		 q.setParameter(2, serial.getProductId());
		 q.executeUpdate();
		 Query q2=entityManager.createNativeQuery("update inventory i set expired=(select count(*) from serial s where expiry_date<sysdate() and s.product_id=?) where i.product_id=?");
		 q2.setParameter(1, serial.getProductId());
		 q2.setParameter(2, serial.getProductId());
		 q2.executeUpdate();
		 return s;
	}

	@Override
	public List<Serial> getSerialByLocationId(Integer locationId) {
		// TODO Auto-generated method stub
		
		Query q=entityManager.createQuery("select s from Serial s where s.locationId=:u and s.serialStatus=:v");
		q.setParameter("u", locationId);
		q.setParameter("v", "Comissioned");
		List<Serial> serialList= q.getResultList();
		return serialList;
	}

	@Override
	public Serial getSerialBySerialId(Integer serialId, Integer locationId) {
		// TODO Auto-generated method stub
		if(serialId==null || locationId==null) return null;
		Query q=entityManager.createQuery("select s from Serial s where s.locationId=:u and s.serialStatus=:v and s.serialId=:w");
		q.setParameter("u", locationId);
		q.setParameter("v", "Recieved");
		q.setParameter("w", serialId);
		Serial s;
		try {
			 s=(Serial) q.getSingleResult();
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return s;
	}
	
	
	@Transactional
	@Override
	public String changeSerialStatus(Integer serialId, Integer locationId) {
		// TODO Auto-generated method stub
		System.out.println(serialId);
		System.out.println(locationId);
		Query q = entityManager.createQuery("update Serial set serialStatus=:a where serialStatus=:b AND serialId=:c");
		q.setParameter("a", "Received");
		q.setParameter("b", "Available");
		q.setParameter("c", serialId);
		q.executeUpdate();
		
		Query q1 = entityManager.createNativeQuery("update order_events set event_desc=? where event_desc=? and location_id=?");
		q1.setParameter(1, "Received");
		q1.setParameter(2, "Shipped");
		q1.setParameter(3, locationId);
		q1.executeUpdate();
		return "Status Changed to Recieved";
	}

	@Override
	public Product getDoseName(Integer productId) {
		// TODO Auto-generated method stub
		Query q1=entityManager.createQuery("select p from product p where p.productId=:a");
		q1.setParameter("a",productId);
		Product p = (Product)q1.getResultList();
		return p;
	}

	@Override
	public OrderEvents getQuantity(Integer productId, Integer locationId) {
		// TODO Auto-generated method stub
		Query q2=entityManager.createQuery("");
		return null;
	}

	@Override
	public List<Serial> getSerialShipped(Integer locationId,Integer serialNo) {
		// TODO Auto-generated method stub
		Query q = entityManager.createNativeQuery("select s from serial where serail_no=? and location_id=?");
		q.setParameter(1, serialNo);
		q.setParameter(2, locationId);
		List<Serial> list = q.getResultList();
		return list;
	}
	
	

}
