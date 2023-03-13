package com.patient.Service;

import java.util.List;

import com.patient.Entity.Clinic;
import com.patient.Entity.OrderEvents;
import com.patient.Entity.Serial;

public interface ShippingService {
	
	
	
	public List<Clinic> getAllShipToId();
	
	public Clinic getShippingDataByShippingId(String shippingToId);
	
	public List<OrderEvents> getprocessedorderEvents();
	
	public List<Serial> getSerialByProductId(Integer productId);
}
