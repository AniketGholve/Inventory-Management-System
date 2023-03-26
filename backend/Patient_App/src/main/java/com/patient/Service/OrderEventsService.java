package com.patient.Service;

import java.util.List;

import com.patient.Entity.ClinicOrder;
import com.patient.Entity.Inventory;
import com.patient.Entity.OrderEvents;

public interface OrderEventsService {
	
	public List<OrderEvents> getAllOrderDetails();
	
	public String changeOrderStatus(Integer orderId);
	
	public String cancelOrder(Integer orderId);
	
	public List<OrderEvents> createOrderEvent(List<Inventory> inventory,Integer clinicOrderId);

	
	public List<OrderEvents> getOrderingScreen();
	
	public List<Inventory> getinventoryByProductId(Integer productId,Integer locationId);
	
	public void checkProcessedEvents();
	
	public String changeShipToRecieve(Integer orderId);
}
