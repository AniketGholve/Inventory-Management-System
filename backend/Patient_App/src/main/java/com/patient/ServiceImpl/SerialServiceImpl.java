package com.patient.ServiceImpl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.Entity.Clinic;
import com.patient.Entity.OrderEvents;
import com.patient.Entity.Product;
import com.patient.Entity.Serial;
import com.patient.Entity.SerialEventDesc;
import com.patient.Repo.ClinicRepo;
import com.patient.Repo.ProductRepo;
import com.patient.Repo.SerialRepo;
import com.patient.Service.EmailSenderService;
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
	
	@Autowired
	private ClinicRepo clinicRepo;
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private EmailSenderService mailSender;

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
	public Serial getSerialBySerialId(Integer serialNo, Integer locationId) {
		// TODO Auto-generated method stub
		if(serialNo==null || locationId==null) return null;
		Query q=entityManager.createQuery("select s from Serial s where s.locationId=:u and s.serialStatus=:v and s.serialNumber=:w");
		q.setParameter("u", locationId);
		q.setParameter("v", "Shipped");
		q.setParameter("w", serialNo);
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
	public String changeSerialStatus(Integer serialId, Integer locationId,Integer patientSpecific,String UserMail) {
		// TODO Auto-generated method stub
		System.out.println(serialId);
		System.out.println(locationId);
		Query q = entityManager.createQuery("update Serial set serialStatus=:a where serialStatus=:b AND serialId=:c");
		q.setParameter("a", "Received");
		q.setParameter("b", "Shipped");
		q.setParameter("c", serialId);
		q.executeUpdate();
		
		Query q1 = entityManager.createNativeQuery("update order_events set event_desc=? where event_desc=? and location_id=?");
		q1.setParameter(1, "Received");
		q1.setParameter(2, "Shipped");
		q1.setParameter(3, locationId);
		q1.executeUpdate();
		
		Query q2 = entityManager.createNativeQuery("update Serial set patient_specific=? where patient_specific is null and serial_Status=? and serial_Id=? and location_id=?");
		q2.setParameter(1, patientSpecific);
		q2.setParameter(2, "Received");
		q2.setParameter(3, serialId);
		q2.setParameter(4, locationId);
		System.out.println("patientSpecufic:"+patientSpecific);
		q2.executeUpdate();
		
		Serial s = serialRepo.findById(serialId).orElseThrow();
		Integer serialNo = s.getSerialNumber();
		Integer productId = s.getProductId();
		Integer lot = s.getLot();
		Date expiryDate = s.getExpiryDate();
		
		Clinic c = clinicRepo.findById(locationId).orElseThrow();
		String clinicName = c.getName();
		
		Product p = productRepo.findById(productId).orElseThrow();
		String productName = p.getProductName();
		String gtin = p.getGtin();
		
		mailSender.sendAddtoInventoryMail(UserMail, serialNo, clinicName, productName,expiryDate,lot,gtin);
		
		long m= System.currentTimeMillis();
		Date dat = new Date(m);
		Timestamp time = new Timestamp(m);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		Query q3 = entityManager.createNativeQuery("INSERT INTO serial_event_desc (`serial_id`, `serial_number`, `event_date`, `status`, `product_id`) VALUES (?, ?, ?, ?, ?)");
		q3.setParameter(1, serialId);
		q3.setParameter(2, serialNo);
		q3.setParameter(3, time);
		q3.setParameter(4, "Received");
		q3.setParameter(5, productId);
		q3.executeUpdate();
		
		return "Status Changed to Recieved";
	}

	@Override
	public OrderEvents getQuantity(Integer productId, Integer locationId) {
		// TODO Auto-generated method stub
		Query q2=entityManager.createQuery("");
		return null;
	}

	@Override

	public Serial getSerialBySerialNo(Integer serialNo, Integer locationId) {
		// TODO Auto-generated method stub
		if(serialNo==null || locationId==null) return null;
		Query q=entityManager.createQuery("select s from Serial s where s.locationId=:u and s.serialStatus=:v and s.serialNumber=:w");
		q.setParameter("u", locationId);
		q.setParameter("v", "Received");
		q.setParameter("w", serialNo);
		Serial s;
		try {
			 s=(Serial) q.getSingleResult();
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return s;
	}

	public List<Serial> getSerialShipped(Integer locationId,Integer serialNo) {
		// TODO Auto-generated method stub
		Query q = entityManager.createNativeQuery("select s from serial where serail_no=? and location_id=?");
		q.setParameter(1, serialNo);
		q.setParameter(2, locationId);
		List<Serial> list = q.getResultList();
		return list;

	}

	@Override
	public Product getDoseName(Integer productId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional
	@Override
	public void transferDose(int serialNo,int gettingLocationId) {
		// TODO Auto-generated method stub
		Serial s = serialRepo.findBySerialNumber(serialNo);
		System.out.println(s.toString());
//		if(s.getSerialStatus()=="Received") {
		int givingLocationId = s.getLocationId();
		int productId = s.getProductId();
		
		//decreasing product from onhand
		Query q = entityManager.createNativeQuery("select on_hand from Inventory where location_id=? and product_id=?");
		q.setParameter(1, givingLocationId);
		q.setParameter(2, productId);
		int onHand = (int)q.getSingleResult();
		Query q1 = entityManager.createNativeQuery("update Inventory set on_hand=? where location_id=? and product_id=?");
		q1.setParameter(1,onHand-1);
		q1.setParameter(2, givingLocationId);
		q1.setParameter(3, productId);
		q1.executeUpdate();
		
		//increasing product from onhand
		Query q2 = entityManager.createNativeQuery("select on_hand from Inventory where location_id=? and product_id=?");
		q2.setParameter(1, gettingLocationId);
		q2.setParameter(2, productId);
		int onHand2 = (int)q2.getSingleResult();
		Query q3 = entityManager.createNativeQuery("update Inventory set on_hand=? where location_id=? and product_id=?");
		q3.setParameter(1,onHand2+1);
		q3.setParameter(2, gettingLocationId);
		q3.setParameter(3, productId);
		q3.executeUpdate();
		
		Query q4 = entityManager.createNativeQuery("select transit_doses from Inventory where location_id=? and product_id=?");
		q4.setParameter(1, gettingLocationId);
		q4.setParameter(2, productId);
		int transitdoses = (int)q4.getSingleResult();
		Query q5 = entityManager.createNativeQuery("update Inventory set transit_doses=? where location_id=? and product_id=?");
		q5.setParameter(1,transitdoses+1);
		q5.setParameter(2, gettingLocationId);
		q5.setParameter(3, productId);
		q5.executeUpdate();
		Query q6 = entityManager.createNativeQuery("update Serial s set transited_dose=? where location_id=? and product_id=? and serial_number=?");
		q6.setParameter(1, 1);
		q6.setParameter(2, gettingLocationId);
		q6.setParameter(3, productId);
		q6.setParameter(4, serialNo);
		q6.executeUpdate();
		
		s.setLocationId(gettingLocationId);
		serialRepo.save(s);	
//	}
	}

	@Override
	public List<Serial> getSerialReceivedByLocationId(Integer locationId) {
		Query q=entityManager.createQuery("select s from Serial s where s.locationId=:u and s.serialStatus=:v");
		q.setParameter("u", locationId);
		q.setParameter("v", "Received");
		List<Object[]> list = new ArrayList<>();
		List<Serial> serialList = q.getResultList();
		List<Serial> result = new ArrayList<>();
		for(Serial s : serialList) {
			int productId = s.getProductId();
			Product p = productRepo.findById(productId).orElseThrow();
			s.setProductName(p.getProductName());
			result.add(s);
		}
		return result;
	}

	@Override
	public Serial getSerialDetailsBySerialNo(int serialNo) {
		// TODO Auto-generated method stub
		Serial s = serialRepo.findBySerialNumber(serialNo);
		Product p = productRepo.findById(s.getProductId()).orElseThrow();
		Query q = entityManager.createQuery("select s from SerialEventDesc s where s.serialNumber=:u");
		q.setParameter("u", serialNo);
		List<SerialEventDesc> se = (List<SerialEventDesc>)q.getResultList();
		s.setSerialEventDesc(se);
		s.setProductName(p.getProductName());
		return s;
	}
	
	

}
