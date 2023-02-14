package com.patient.ServiceImpl;

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
	private EntityManager em;

	@Transactional
	public Serial createSerial(Serial serial) {
		// TODO Auto-generated method stub
		 Serial s=serialRepo.save(serial);
		 Query q=em.createNativeQuery("update inventory i set on_hand=(select Count(*) from serial s group by product_id having s.product_id=?) where i.product_id=?");
		 q.setParameter(1, serial.getProductId());
		 q.setParameter(2, serial.getProductId());
		 q.executeUpdate();
		 Query q2=em.createNativeQuery("update inventory i set expired=(select count(*) from serial s where expiry_date<sysdate() and s.product_id=?) where i.product_id=?");
		 q2.setParameter(1, serial.getProductId());
		 q2.setParameter(2, serial.getProductId());
		 q2.executeUpdate();
		 return s;
	}

}
