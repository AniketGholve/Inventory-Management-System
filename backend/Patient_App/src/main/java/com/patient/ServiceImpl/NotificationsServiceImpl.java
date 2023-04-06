package com.patient.ServiceImpl;

import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.patient.Entity.Clinic;
import com.patient.Entity.ClinicOrder;
import com.patient.Entity.Inventory;
import com.patient.Entity.Notifications;
import com.patient.Entity.OrderEvents;
import com.patient.Repo.ClinicOrderRepo;
import com.patient.Repo.ClinicRepo;
import com.patient.Repo.NotificatinsRepo;
import com.patient.Repo.OrderEventsRepo;
import com.patient.Service.NotificationsService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Service
public class NotificationsServiceImpl implements NotificationsService {
	
	@Autowired
	private NotificatinsRepo notificationsRepo;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private ClinicRepo clinicRepo;
	
	@Autowired
	private ClinicOrderRepo clinicOrderRepo;
	
	@Autowired
	private OrderEventsRepo orderEventsRepo;

	@Override
	public List<Notifications> getAll() {
		// TODO Auto-generated method stub
		List<Notifications> list = notificationsRepo.findAll();
		return list;
	}
	
	//ManualOrder
	@Override
	@Transactional
	@Scheduled(fixedRate = 86400000)//
	public Notifications createNotification() {
		// TODO Auto-generated method stub
		Query q = entityManager.createNativeQuery("select i.on_hand,m.product_name from \r\n"
				+ "inventory i inner join product p on i.product_id=p.product_id \r\n"
				+ "inner join manual_reorder m on m.product_name=p.product_name \r\n"
				+ "where i.on_hand<m.alert_quantity and m.low_inventory_alerts = true");
		List<Object[]> list = q.getResultList();
		Notifications notification = new Notifications();
		if(list.size()>0) {
		Date date = new Date(System.currentTimeMillis());
		notification.setMessage("Inventory is low on products");
		notification.setNotifiedTime(date);
		notification.setStatus("Error Order");
		notificationsRepo.save(notification);
		}
		return null;
		
	}

	@Override
	public Notifications deleteNotification() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Notifications> deleteAllNotification() {
		// TODO Auto-generated method stub
		notificationsRepo.deleteAll();
		return null;
	}
	
	
	//AutoOrder
	@Override
	@Scheduled(fixedRate = 86400000)
	public void AutoOrder() {
		// TODO Auto-generated method stub
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

		ClinicOrder clinicOrder;

		if(i.getOnHand()<5 && co==-1) {

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

		if(i.getOnHand()<5 && co==0) {

		 

		OrderEvents orderEvents = new OrderEvents();

		 

		orderEvents.setActivityDate(formatter.format(d));

		orderEvents.setDeliveryOrderId(null);

		orderEvents.setEnterpriseId(savedClinicOrder.getEnterpriseId());

		orderEvents.setEventDesc("Submitted");

		orderEvents.setLocationId(savedClinicOrder.getLocationId());

		orderEvents.setOrderId(savedClinicOrder);

		orderEvents.setPackageType(null);

		orderEvents.setProductId(i.getProductId());

		orderEvents.setQuantity(i.getOrderedQty());

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
