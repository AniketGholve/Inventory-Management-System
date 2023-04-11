package com.patient.ServiceImpl;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.patient.Entity.AutoReorder;
import com.patient.Entity.Clinic;
import com.patient.Entity.ClinicOrder;
import com.patient.Entity.Inventory;
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
	public List<Serial> getSerialNumber(int productId) {
		// TODO Auto-generated method stub
		Query q=entityManager.createNativeQuery("select * from serial where product_id=?");
		q.setParameter(1, productId);
		List<Object[]> l=q.getResultList();
		List<Serial> resultList=new ArrayList<>();
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
	@Transactional
	//@Scheduled(cron = "0 10 20 * * MON-FRI") //cron = "0 10 20 * * MON-FRI"
	@Scheduled(fixedRate = 86400000)
	public void AutoOrder() 
	{	
		List<Clinic> clinicList=clinicRepo.findAll();

		long m=System.currentTimeMillis();

		Date d=new Date(m);

		Format formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

		for(Clinic c:clinicList) {

		Query q=entityManager.createQuery("select i from Inventory i where i.locationId=:u");

		q.setParameter("u",c.getLocationId());

		List<Inventory> inventoryList=q.getResultList();
		

		int co=-1;

		ClinicOrder savedClinicOrder=null;

		for(Inventory i:inventoryList)

		{
			Query q2=entityManager.createQuery("select ar from AutoReorder ar where ar.productId=:v");
			q2.setParameter("v",i.getProductId());
			System.out.println("product id"+i.getProductId()+" "+i.getLoactionId());
			AutoReorder autoReorder=(AutoReorder) q2.getSingleResult();
		
		ClinicOrder clinicOrder;

		if(i.getOnHand()<autoReorder.getReorderPoint() && co==-1) {
			
		

		clinicOrder=new ClinicOrder();

		Clinic clinic=clinicRepo.findById(i.getLoactionId()).orElseThrow();

		clinicOrder.setActivityDate(formatter.format(d));

		clinicOrder.setBilltoId(123);

		clinicOrder.setBilltoName(clinic.getBillTo());

		clinicOrder.setEnterpriseId(1);

		clinicOrder.setLocationId(i.getLoactionId());

		clinicOrder.setMeu(null);

		clinicOrder.setOrderDatetime(d);

		clinicOrder.setOrderId(12345);

		clinicOrder.setOrderNote(null);

		clinicOrder.setOrderStatusId(123);

		clinicOrder.setOrderType(null);

		clinicOrder.setPersonInitial(null);

		clinicOrder.setShipfromId(0);

		clinicOrder.setShiptoId(clinic.getLocationId()+clinic.getName());

		clinicOrder.setShiptoName(clinic.getName());

		clinicOrder.setSrcId(123);

		clinicOrder.setUserId(0);

		savedClinicOrder=clinicOrderRepo.save(clinicOrder);

		co=0;

		}

		if(i.getOnHand()<autoReorder.getReorderPoint() && co==0) {
			
		Query q3=entityManager.createNativeQuery("update inventory i set i.on_hand=? where i.location_id=? and i.product_id=?" );
		q3.setParameter(1, i.getOnHand()+autoReorder.getReorderQuantity());
		q3.setParameter(2, i.getLoactionId());
		System.out.println("product id+++++"+i.getProductId()+" "+i.getLoactionId());
		q3.setParameter(3, autoReorder.getProductId());
		q3.executeUpdate();

		 

		OrderEvents orderEvents = new OrderEvents();

		 

		orderEvents.setActivityDate(formatter.format(d));

		orderEvents.setDeliveryOrderId(null);

		orderEvents.setEnterpriseId(savedClinicOrder.getEnterpriseId());

		orderEvents.setEventDesc("Submitted");

		orderEvents.setLocationId(savedClinicOrder.getLocationId());

		orderEvents.setOrderId(savedClinicOrder);

		orderEvents.setPackageType(null);

		orderEvents.setProductId(i.getProductId());

		orderEvents.setQuantity(autoReorder.getReorderQuantity());

		orderEvents.setShipmentTrackingId(123);

		orderEvents.setSrcId(savedClinicOrder.getSrcId());

		orderEvents.setStatusId(savedClinicOrder.getOrderStatusId());

		orderEvents.setUserId(savedClinicOrder.getUserId());

		OrderEvents oe=orderEventsRepo.save(orderEvents);

		 

		}

		}

		 

		 

		}
		
		
	}
	
	
	
	

}

