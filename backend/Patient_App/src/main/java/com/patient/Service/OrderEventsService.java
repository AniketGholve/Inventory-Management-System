package com.patient.Service;

import java.util.List;

import com.patient.Entity.ClinicOrder;
import com.patient.Entity.Inventory;
import com.patient.Entity.OrderEvents;

public interface OrderEventsService {
	
	public List<OrderEvents> getAllOrderDetails();
	
	public String changeOrderStatus(Integer orderEventsId, String status);
	
	public String cancelOrder(Integer orderEventId);
	
	public List<OrderEvents> createOrderEvent(List<Inventory> inventory,Integer clinicOrderId);

	
	public List<OrderEvents> getOrderingScreen();
	
	public Inventory getinventoryByProductId(Integer productId,Integer locationId);
}
