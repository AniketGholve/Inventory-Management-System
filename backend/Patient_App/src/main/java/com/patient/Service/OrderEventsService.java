package com.patient.Service;

import java.util.List;

import com.patient.Entity.OrderEvents;

public interface OrderEventsService {
	
	public List<OrderEvents> getAllOrderDetails();
	
	public String changeOrderStatus(Integer orderEventsId, String status);
	
	public String cancelOrder(Integer orderEventId);

}
