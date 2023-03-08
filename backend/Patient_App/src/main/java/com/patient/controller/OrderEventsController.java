package com.patient.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.patient.Entity.OrderEvents;
import com.patient.ServiceImpl.OrderEventsServiceImpl;

@CrossOrigin
@RestController
public class OrderEventsController {
	
	private OrderEventsServiceImpl orderEventsServiceImpl;
	
	@GetMapping("getAllOrdersDetails")
	public ResponseEntity<List<OrderEvents>> getAllOrdersDetails(){
		
		List<OrderEvents> result=orderEventsServiceImpl.getAllOrderDetails();
		return new ResponseEntity<List<OrderEvents>>(result,HttpStatus.OK);
	}
	
	
	
	@PostMapping("changeStatus/{orderEventsId}")
	public ResponseEntity<String> changeOrderStatus(@RequestParam("status") String status,@PathVariable Integer orderEventsId){
		
		String result=orderEventsServiceImpl.changeOrderStatus(orderEventsId,status);
		return new ResponseEntity<String>(result,HttpStatus.OK);
	}
	
	
	@DeleteMapping("cancelOrder/{orderEventId}")
	public ResponseEntity<String> cancelOrder(@PathVariable Integer orderEventId){
		String result=orderEventsServiceImpl.cancelOrder(orderEventId);
		return new ResponseEntity<String>(result,HttpStatus.OK);
	}
	

}
