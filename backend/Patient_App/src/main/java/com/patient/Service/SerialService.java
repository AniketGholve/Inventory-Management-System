package com.patient.Service;

import java.util.List;

import com.patient.Entity.OrderEvents;
import com.patient.Entity.Product;
import com.patient.Entity.Serial;

public interface SerialService {

	public Serial createSerial(Serial serial);
	
	public List<Serial> getSerialByLocationId(Integer locationId);
	
	public Serial getSerialBySerialId(Integer serialId,Integer locationId);
	
	public String changeSerialStatus(Integer serialId,Integer locationId);
	
	public OrderEvents getQuantity(Integer productId,Integer locationId);
	
	public List<Serial> getSerialShipped(Integer locationId,Integer serialNo);
}
