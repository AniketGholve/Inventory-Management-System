package com.patient.Service;

import java.util.List;

import com.patient.Entity.Clinic;
import com.patient.Entity.ClinicOrder;
import com.patient.Entity.ScannedShipmentDetails;
import com.patient.Entity.Serial;

public interface ShippingService {
	
	
	
	public List<Clinic> getAllShipToId();
	
	public Clinic getShippingDataByShippingId(String shippingToId);
	
	public List<ClinicOrder> getprocessedorderEvents(Integer locationId);
	
	public List<Serial> getSerialByProductId(Integer productId);
	
	public List<ScannedShipmentDetails> getScannedShipmentDetails(Integer serialId,Integer productId,Integer orderId);

	public String changeSerialAndOrderStatus(Integer orderId);
}
