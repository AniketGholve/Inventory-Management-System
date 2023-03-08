package com.patient.ServiceImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.Entity.ClinicOrder;
import com.patient.Entity.Inventory;
import com.patient.Entity.OrderEvents;
import com.patient.Repo.ClinicOrderRepo;
import com.patient.Repo.OrderEventsRepo;
import com.patient.Service.OrderEventsService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;


@Service
public class OrderEventsServiceImpl implements OrderEventsService {
	
	
	@Autowired
	private OrderEventsRepo orderEventsRepo;
	
	@Autowired
	private ClinicOrderRepo clinicOrderRepo;
	
	@Autowired
	private EntityManager entityManager;
	
	
	
	@Override
	public List<OrderEvents> createOrderEvent(List<Inventory> inventory,Integer clinicOrderId) {
		// TODO Auto-generated method stub
		long m=System.currentTimeMillis();
		Date d=new Date(m);
		ClinicOrder clinicOrder=clinicOrderRepo.findById(clinicOrderId).orElseThrow();
		List<OrderEvents> orderEventsList=new ArrayList<>();
		for(Inventory i:inventory) {
			OrderEvents orderEvents=new OrderEvents();
			orderEvents.setActivityDate(d);
			orderEvents.setDeliveryOrderId(null);
			orderEvents.setEnterpriseId(clinicOrder.getEnterpriseId());
			orderEvents.setEventDesc("Submitted");
			orderEvents.setLocationId(clinicOrder.getLocationId());
			orderEvents.setOrderId(clinicOrder.getOrderId());
			orderEvents.setPackageType(null);
			orderEvents.setProductId(i.getProductId());
			orderEvents.setQuantity(i.getOrderedQty());
			orderEvents.setShipmentTrackingId(123);
			orderEvents.setSrcId(clinicOrder.getSrcId());
			orderEvents.setStatusId(clinicOrder.getOrderStatusId());
			orderEvents.setUserId(clinicOrder.getUserId());
			OrderEvents oe=orderEventsRepo.save(orderEvents);
			orderEventsList.add(oe);

		}
		return orderEventsList;
	} 
	

	@Override
	public List<OrderEvents> getAllOrderDetails() {
		// TODO Auto-generated method stub
		List<OrderEvents> orderEventsList=orderEventsRepo.findAll();
		return orderEventsList;
	}

	@Override
	public String changeOrderStatus(Integer orderEventsId, String status) {
		// TODO Auto-generated method stub
		OrderEvents orderEvents=orderEventsRepo.findById(orderEventsId).orElseThrow();
		if(status=="process") orderEvents.setEventDesc("processes");
		else if(status=="Submitted") orderEvents.setEventDesc("Submitted");
		orderEventsRepo.save(orderEvents);
		return "status changes successfully";
	}

	@Override
	public String cancelOrder(Integer orderEventId) {
		// TODO Auto-generated method stub
		orderEventsRepo.deleteById(orderEventId);
		return "order cancel successfully";
	}


	@Override
	public List<OrderEvents> getOrderingScreen() {
		// TODO Auto-generated method stub
		Query q=entityManager.createNativeQuery("select oe.activity_date,oe.order_event_id,oe.delivery_order_id,co.shipto_id,co.shipto_name,oe.event_desc,oe.quantity from order_events oe inner join clinic_order co on oe.order_id=co.order_id");
		List<Object[]> orderinglist=q.getResultList();
		List<OrderEvents> orderEventList=new ArrayList<>();
		for(Object [] o:orderinglist) {
			OrderEvents orderEvents=new OrderEvents();
			orderEvents.setActivityDate((Date)o[0]);
			orderEvents.setOrderEventId((Integer) o[1]);
			orderEvents.setDeliveryOrderId((Integer)o[2]);
			orderEvents.setShiptoId((Integer)o[3]);
			orderEvents.setShiptoName((String)o[4]);
			orderEvents.setEventDesc((String)o[5]);
			orderEvents.setQuantity((Integer)o[6]);
			orderEventList.add(orderEvents);
		}
		return orderEventList;
	}


	public Inventory getinventoryByProductId(Integer productId,Integer locationId) {
		// TODO Auto-generated method stub
		List<Inventory> resultList=new ArrayList<>();
		Query q=entityManager.createNativeQuery("select i.product_id,p.product_name,i.expired,i.on_hand from inventory i inner join product p on i.product_id=p.product_id where i.location_id=? and i.product_id=?");
		q.setParameter(1, locationId);
		q.setParameter(2, productId);
		System.out.println(q.getSingleResult());
		Object[] l=(Object[]) q.getSingleResult();
			Inventory i=new Inventory();
			i.setProductId(l[0]==null?null:(Integer) l[0]);
			i.setProductName(l[1]==null?null:(String) l[1]);
			i.setExpiredQty(l[2]==null?null:(Integer) l[2]);
			i.setOnHand(l[3]==null?null:(Integer) l[3]);
			return i;
			
			
		
	}

	
	

}
