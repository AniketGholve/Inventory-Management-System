package com.patient.ServiceImpl;

import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.patient.Entity.ClinicOrder;
import com.patient.Entity.Inventory;
import com.patient.Entity.Notifications;
import com.patient.Entity.OrderEvents;
import com.patient.Entity.Serial;
import com.patient.Repo.ClinicOrderRepo;
import com.patient.Repo.NotificatinsRepo;
import com.patient.Repo.OrderEventsRepo;
import com.patient.Repo.SerialRepo;
import com.patient.Service.OrderEventsService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
@EnableScheduling
@Service
public class OrderEventsServiceImpl implements OrderEventsService {

	@Autowired
	private OrderEventsRepo orderEventsRepo;

	@Autowired
	private ClinicOrderRepo clinicOrderRepo;

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private NotificatinsRepo notificationsRepo;
	
	@Autowired
	private SerialServiceImpl serialServiceImpl;
	
	@Autowired
	private SerialRepo serialSeviceRepo;
	
	private static int k = 1000;
	
	@Override
	public List<OrderEvents> createOrderEvent(List<Inventory> inventory, Integer clinicOrderId) {
		// TODO Auto-generated method stub

		long m = System.currentTimeMillis();
		Date d = new Date(m);
		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ClinicOrder clinicOrder = clinicOrderRepo.findById(clinicOrderId).orElseThrow();
		List<OrderEvents> orderEventsList = new ArrayList<>();
		List<Integer> l = new ArrayList<>();
		l.add(1);
		l.add(2);
		for (Inventory i : inventory) {
			if (i.getOrderedQty() != 0) {
				OrderEvents orderEvents = new OrderEvents();

				orderEvents.setActivityDate(formatter.format(d));
				orderEvents.setDeliveryOrderId(null);
				orderEvents.setEnterpriseId(clinicOrder.getEnterpriseId());
				orderEvents.setEventDesc("Submitted");
				orderEvents.setLocationId(clinicOrder.getLocationId());
				orderEvents.setOrderId(clinicOrder);
				orderEvents.setPackageType(null);
				orderEvents.setProductId(i.getProductId());
				orderEvents.setQuantity(i.getOrderedQty());
				int len = i.getOrderedQty();
				orderEvents.setShipmentTrackingId(123);
				orderEvents.setSrcId(clinicOrder.getSrcId());
				orderEvents.setStatusId(clinicOrder.getOrderStatusId());
				orderEvents.setUserId(clinicOrder.getUserId());
				OrderEvents oe=orderEventsRepo.save(orderEvents);
				orderEventsList.add(oe);
				for(int j=0;j<len;j++) {
					Serial s = new Serial();
					s.setCreatedOn(d);
					System.out.println("hello");
					System.out.println(oe.getLocationId());
					System.out.println(oe.getEnterpriseId());
					s.setLocationId(oe.getLocationId());
					s.setEnterpriseId(oe.getEnterpriseId());
					s.setProductId(oe.getProductId());
					s.setSerialStatus("Comissioned");
					s.setSerialNumber(k);
					k++;
					serialSeviceRepo.save(s);
				}
			}
		}
		return orderEventsList;
	}

	@Override
	public List<OrderEvents> getAllOrderDetails() {
		// TODO Auto-generated method stub
		List<OrderEvents> orderEventsList = orderEventsRepo.findAll();
		return orderEventsList;
	}

	@Override
	public String changeOrderStatus(Integer orderId) {
		// TODO Auto-generated method stub
		//OrderEvents orderEvents = orderEventsRepo.findById(orderEventsId).orElseThrow();
		Query q=entityManager.createNativeQuery("update order_events set event_desc=? where order_id=?");
		q.setParameter(1, "Processed");
		q.setParameter(2, orderId);
		q.executeUpdate();
		
		
		Notifications notification = new Notifications();
		Date date = new Date(System.currentTimeMillis());
		notification.setMessage("Status Changed to Processed Successfully");
		notification.setNotifiedTime(date);
		notification.setStatus("Success Order");
		notificationsRepo.save(notification);
		
		return "status changes successfully";
		
	}

	@Override
	public String cancelOrder(Integer orderId) {
		// TODO Auto-generated method stub
		Query q=entityManager.createNativeQuery("update order_events set event_desc=? where order_id=?");
		q.setParameter(1, "Cancelled");
		q.setParameter(2, orderId);
		q.executeUpdate();
		return "order cancel successfully";
	}

	@Override
	public List<OrderEvents> getOrderingScreen() {
		// TODO Auto-generated method stub
		Query q=entityManager.createNativeQuery("select t.activity_date,t.order_event_id,t.po_number,t.shipto_id,t.shipto_name,t.event_desc,t.quantity,t.product_id,t.location_id,p.product_name,t.order_id from (select oe.activity_date,oe.order_event_id,co.po_number,co.shipto_id,co.shipto_name,oe.event_desc,oe.quantity,oe.product_id,oe.location_id,oe.order_id from order_events oe inner join clinic_order co on oe.order_id=co.order_id) T inner join product p where t.product_id=p.product_id order by t.order_id;");
		List<Object[]> orderinglist=q.getResultList();
		List<OrderEvents> orderEventList=new ArrayList<>();
		Integer k=-1,l;
		 Map<String,Integer> m=new HashMap<>();
		OrderEvents orderEvents=new OrderEvents();
		for(Object [] o:orderinglist) {
			
			
			
			if(k==-1) {
				orderEvents.setActivityDate((String) o[0]);
				orderEvents.setOrderEventId((Integer) o[1]);
				orderEvents.setPoNumber((String)o[2]);
				orderEvents.setShiptoId((String)o[3]);
				orderEvents.setShiptoName((String)o[4]);
				orderEvents.setEventDesc((String)o[5]);
				orderEvents.setQuantity((Integer)o[6]);
				orderEvents.setProductId((Integer)o[7]);	
				orderEvents.setLocationId((Integer)o[8]);
				orderEvents.setProductName((String)o[9]);
				m.put((String)o[9], (Integer)o[6]);
				orderEvents.setDisplayId((Integer)o[10]);
				k=((Integer)o[10]);
			}
			
			else if(k==(Integer)o[10]){
				
				m.put((String)o[9], (Integer)o[6]);
				
				
			}
			else {
				orderEvents.setQuantityMap(m);
				orderEventList.add(orderEvents);
				orderEvents=new OrderEvents();
				m=new HashMap<>();
				orderEvents.setActivityDate((String) o[0]);
				orderEvents.setOrderEventId((Integer) o[1]);
				orderEvents.setPoNumber((String)o[2]);
				orderEvents.setShiptoId((String)o[3]);
				orderEvents.setShiptoName((String)o[4]);
				orderEvents.setEventDesc((String)o[5]);
				orderEvents.setQuantity((Integer)o[6]);
				orderEvents.setProductId((Integer)o[7]);	
				orderEvents.setLocationId((Integer)o[8]);
				orderEvents.setProductName((String)o[9]);
				m.put((String)o[9], (Integer)o[6]);
				orderEvents.setDisplayId((Integer)o[10]);
				k=((Integer)o[10]);
				
			}
			
		}
		orderEvents.setQuantityMap(m);
		orderEventList.add(orderEvents);
		return orderEventList;
	}

	public List<Inventory> getinventoryByProductId(Integer productId, Integer locationId) {
		// TODO Auto-generated method stub
		List<Inventory> resultList = new ArrayList<>();
		Query q = entityManager.createNativeQuery(
				"select i.product_id,p.product_name,i.expired,i.on_hand from inventory i inner join product p on i.product_id=p.product_id where i.location_id=?");
		q.setParameter(1, locationId);
		System.out.println(q);
		List<Object[]> l = q.getResultList();
		for(Object [] o:l)
		{
			Inventory i = new Inventory();
			i.setProductId(o[0] == null ? null : (Integer) o[0]);
			i.setProductName(o[1] == null ? null : (String) o[1]);
			i.setExpiredQty(o[2] == null ? null : (Integer) o[2]);
			i.setOnHand(o[3] == null ? null : (Integer) o[3]);
			resultList.add(i);
		}
		return resultList;

	}


	@Override
	@Transactional
	@Scheduled(cron = "0 0 12 * * MON-FRI")
	public void checkProcessedEvents() {
		Date twoDaysAgo = new Date(System.currentTimeMillis()-(2*24*60*60*1000));
		List<OrderEvents> ordersToCancel = orderEventsRepo.findByEventDescAndActivityDateBefore("Processed", twoDaysAgo);
		System.out.print(twoDaysAgo);
		for(OrderEvents event:ordersToCancel) {
			event.setEventDesc("Cancelled");
			orderEventsRepo.save(event);
		}
	}

	@Override
	public String changeShipToRecieve(Integer orderId) {
		// TODO Auto-generated method stub
		Query q=entityManager.createNativeQuery("update order_events set event_desc=? where event_desc=? AND orderId=?");
		q.setParameter(1, "Recieved");
		q.setParameter(2, "Shipped");
		q.setParameter(3, orderId);
		q.executeUpdate();
		return "status changes successfully";
		
	}

	

}
