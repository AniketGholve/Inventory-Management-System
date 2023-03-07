package com.patient.Service;

import java.util.List;

import com.patient.Entity.ClinicOrder;

public interface ClinicOrderService {
	
	public ClinicOrder createOrder(ClinicOrder clinicOrder);
	
	public List<ClinicOrder> getAllOrdersById(Integer locId);
}
