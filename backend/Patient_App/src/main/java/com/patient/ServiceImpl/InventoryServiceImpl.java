package com.patient.ServiceImpl;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.patient.Entity.AutoReorder;
import com.patient.Entity.Clinic;
import com.patient.Entity.ClinicOrder;
import com.patient.Entity.Inventory;
import com.patient.Entity.Notifications;
import com.patient.Entity.OrderEvents;
import com.patient.Entity.Serial;
import com.patient.Repo.ClinicOrderRepo;
import com.patient.Repo.ClinicRepo;
import com.patient.Repo.InventoryRepo;
import com.patient.Repo.OrderEventsRepo;
import com.patient.Service.InventoryService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Service
public class InventoryServiceImpl implements InventoryService {
	
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private InventoryRepo inventoryRepo;
	
	@Autowired
	private OrderEventsRepo orderEventsRepo;
	
	@Autowired
	private ClinicOrderRepo clinicOrderRepo;
	
	@Autowired
	private ClinicRepo clinicRepo;

	public Inventory createInventory(Inventory createdInventory) {
		
		System.out.println(createdInventory.toString());
		return inventoryRepo.save(createdInventory);
	}

	
	@Override
	public List<Serial> getSerialNumber(int productId,int locationId) {
		// TODO Auto-generated method stub
		Query q=entityManager.createNativeQuery("select * from serial s where s.product_id=? and s.serial_Status=? and s.location_id=? and expiry_date>CURRENT_DATE");
		q.setParameter(1, productId);
		q.setParameter(2, "Received");
		q.setParameter(3,locationId);
		List<Object[]> l=q.getResultList();
		List<Serial> resultList=new ArrayList<>();
		
		Query q1 = entityManager.createNativeQuery("select c.name from clinic c where c.location_id =?");
		q1.setParameter(1, locationId);
		String clinicName = (String)q1.getSingleResult();
		
		Query q2 = entityManager.createNativeQuery("select e.name from enterprises e inner join clinic c on e.enterprise_id=c.enterprise_id where c.location_id =?");
		q2.setParameter(1, locationId);
		String enterpriseName = (String)q2.getSingleResult();
		
		for (Object[] o:l)
		{
			Serial s=new Serial();
			s.setSerialId((Integer) o[0]);
			s.setCreatedOn((Date) o[1]);
			s.setEnterpriseId((Integer) o[2]);
			s.setExpiryDate((Date) o[3]);
			s.setLocationId((Integer) o[4]);
			s.setLot((Integer) o[5]);
			s.setNdc((Integer) o[6]);
			s.setPatientSpecific((String) o[7]);
			s.setProductId((Integer) o[8]);
			s.setSerialNumber((Integer) o[9]);
			s.setSerialStatus((String) o[10]);
			s.setSrcId((Integer) o[11]);
			s.setClinicName(clinicName);
			s.setEnterpriseName(enterpriseName);
			resultList.add(s);
			
			
		}
		
		//System.out.println(l.toString());
		return resultList;
	}

	@Override
	public List<Inventory> getScreen() {
		// TODO Auto-generated method stub
		List<Inventory> resultList=new ArrayList<>();
		Query q=entityManager.createNativeQuery("select i.product_id,p.product_name,i.expired,i.on_hand from inventory i inner join product p on i.product_id=p.product_id");
		List<Object[]> l=q.getResultList();
		for(Object[] result:l)
		{
			Inventory i=new Inventory();
			i.setProductId(result[0]==null?null:(Integer) result[0]);
			i.setProductName(result[1]==null?null:(String) result[1]);
			i.setExpiredQty(result[2]==null?null:(Integer) result[2]);
			i.setOnHand(result[3]==null?null:(Integer) result[3]);
			resultList.add(i);
			
		}
		return resultList;
		
		
		//return null;
	}

	@Override
	public List<Serial> getExpiredSerialDetails(int productId) {
		// TODO Auto-generated method stub
		
		Query q=entityManager.createNativeQuery("select * from serial where product_id=? and expiry_date<sysdate()");
		q.setParameter(1, productId);
		List<Object[]> l=q.getResultList();
		
		
		List<Serial> resultList=new ArrayList<>();
		for(Object[] o:l)
		{
			Serial s=new Serial();
			s.setSerialId((Integer) o[0]);
			s.setCreatedOn((Date) o[1]);
			s.setEnterpriseId((Integer) o[2]);
			s.setExpiryDate((Date) o[3]);
			s.setLocationId((Integer) o[4]);
			s.setLot((Integer) o[5]);
			s.setNdc((Integer) o[6]);
			s.setPatientSpecific((String) o[7]);
			s.setProductId((Integer) o[8]);
			s.setSerialNumber((Integer) o[9]);
			s.setSerialStatus((String) o[10]);
			s.setSrcId((Integer) o[11]);
			resultList.add(s);
			
		}
		return resultList;
	}
	
	//here
	
	public  List<Inventory> getInventoryByClinic(Integer clinicLocationId)
	{
		Query q=entityManager.createNativeQuery("select i.product_id,p.product_name,i.expired,i.on_hand from inventory i inner join product p on i.product_id=p.product_id where i.location_id=?");
		q.setParameter(1, clinicLocationId);
		List<Object[]> l=q.getResultList();	
		List<Inventory> resultList=new ArrayList<>();
		for(Object[] result:l)
		{
			Inventory i=new Inventory();
			i.setProductId(result[0]==null?null:(Integer) result[0]);
			i.setProductName(result[1]==null?null:(String) result[1]);
			i.setExpiredQty(result[2]==null?null:(Integer) result[2]);
			i.setOnHand(result[3]==null?null:(Integer) result[3]);
			resultList.add(i);
			
		}
		return resultList;
	}


	@Override
	public void AutoOrder() {
		// TODO Auto-generated method stub
		
	}


	
	
	
	
	
	

}

