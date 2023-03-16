package com.patient.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		List<Serial> serialList=q.getResultList();
		return serialList;
	}

	@Override
	public Serial getSerialBySerialId(Integer serialId, Integer locationId) {
		// TODO Auto-generated method stub
		Query q=entityManager.createQuery("select s from Serial s where s.locationId=:u and s.serialStatus=:v and s.serialId=:w");
		q.setParameter("u", locationId);
		q.setParameter("v", "Shipped");
		q.setParameter("w", locationId);
		Serial s;
		try {
			 s=(Serial) q.getSingleResult();
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return s;
	}

}
