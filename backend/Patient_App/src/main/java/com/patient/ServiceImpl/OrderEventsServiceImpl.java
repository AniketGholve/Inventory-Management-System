package com.patient.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.Entity.OrderEvents;
import com.patient.Repo.OrderEventsRepo;
import com.patient.Service.OrderEventsService;


@Service
public class OrderEventsServiceImpl implements OrderEventsService {
	
	@Autowired
	private OrderEventsRepo orderEventsRepo;

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
		else if(status=="submitted") orderEvents.setEventDesc("Submitted");
		orderEventsRepo.save(orderEvents);
		return "status changes successfully";
	}

	@Override
	public String cancelOrder(Integer orderEventId) {
		// TODO Auto-generated method stub
		orderEventsRepo.deleteById(orderEventId);
		return "order cancel successfully";
	} 
}
