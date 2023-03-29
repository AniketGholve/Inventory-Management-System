package com.patient.Service;

import java.util.List;

import com.patient.Entity.MinDaysToDis;

public interface MinDayToDisService {
	
	public List<MinDaysToDis> getAll();
	
	public MinDaysToDis create(MinDaysToDis minDaysToDis);
	
	public MinDaysToDis edit(MinDaysToDis minDaysToDis);
	
	public void deleteById(int productId);
}
