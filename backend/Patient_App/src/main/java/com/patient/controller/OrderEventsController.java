package com.patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.patient.Entity.Inventory;
import com.patient.Entity.OrderEvents;
import com.patient.ServiceImpl.OrderEventsServiceImpl;

import jakarta.transaction.Transactional;

@RestController
@CrossOrigin
public class OrderEventsController {
	
	@Autowired
	private OrderEventsServiceImpl orderEventsServiceImpl;
	
	 
	
	
	
	
	@PostMapping("/createOrderEvent/{clinicOrderId}")
	public ResponseEntity<List<Inventory>> createOrderEvent(@RequestBody List<Inventory> inventory,@PathVariable Integer clinicOrderId){
		orderEventsServiceImpl.createOrderEvent(inventory,clinicOrderId);
		return null;
	}
	
	
	@GetMapping("getAllOrdersDetails")
	public ResponseEntity<List<OrderEvents>> getAllOrdersDetails(){
		
		List<OrderEvents> result=orderEventsServiceImpl.getAllOrderDetails();
		return new ResponseEntity<List<OrderEvents>>(result,HttpStatus.OK);
	}
	
	
	
	@PostMapping("changeStatus/{orderId}")
	@Transactional
	public ResponseEntity<String> changeOrderStatus(@PathVariable Integer orderId){
		
		String result=orderEventsServiceImpl.changeOrderStatus(orderId);
		return new ResponseEntity<String>(result,HttpStatus.OK);
	}
	
	
	@DeleteMapping("/cancelOrder/{orderId}")
	@Transactional
	public ResponseEntity<String> cancelOrder(@PathVariable Integer orderId){
		String result=orderEventsServiceImpl.cancelOrder(orderId);
		return new ResponseEntity<String>(result,HttpStatus.OK);
	}
	
	
	@GetMapping("/getOrderingScreen")
	public ResponseEntity<List<OrderEvents>> getOrderingScreen()
	{
		List<OrderEvents> resultList=orderEventsServiceImpl.getOrderingScreen();
		return new ResponseEntity<List<OrderEvents>>(resultList,HttpStatus.OK);
	}
	
	
	@GetMapping("/getinventoryByProductId/{productId}/{locationId}")
	public ResponseEntity<List<Inventory>> getinventoryByProductId(@PathVariable Integer productId,@PathVariable Integer locationId ){
		List<Inventory> inventory =orderEventsServiceImpl.getinventoryByProductId(productId,locationId);
		return new ResponseEntity<List<Inventory>>(inventory,HttpStatus.OK);
	}
	

}
