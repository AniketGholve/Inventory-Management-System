package com.patient.Service;

import java.util.List;

import com.patient.Entity.Inventory;
import com.patient.Entity.Serial;



public interface InventoryService {
	
public Inventory createInventory(Inventory inventory);
	
	//public List<?> getScreen();
	
	public List<Serial> getSerialNumber(int productId,int locationId);
	
	public List<Inventory> getScreen();
	
	public List<Serial> getExpiredSerialDetails(int productId);
	
	public List<Inventory> getInventoryByClinic(Integer clinicLocationId);
	
	public void AutoOrder();
	
	
}
