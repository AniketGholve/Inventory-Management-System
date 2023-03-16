package com.patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.patient.Entity.Clinic;
import com.patient.Entity.ClinicOrder;
import com.patient.Entity.OrderEvents;
import com.patient.Entity.ScannedShipmentDetails;
import com.patient.Entity.Serial;
import com.patient.ServiceImpl.ShippingServiceImpl;

import jakarta.transaction.Transactional;

@RestController
@CrossOrigin
public class ShippingController {
	
	@Autowired
	private ShippingServiceImpl shippingServiceImpl;
	
	
	@GetMapping("/getAllShipToId")
	public ResponseEntity<List<Clinic>> getAllShipToId(){
		
		List<Clinic> result=shippingServiceImpl.getAllShipToId();
		return new ResponseEntity<List<Clinic>>(result,HttpStatus.OK);
		
		
		
	}
	
	@GetMapping("getShippingDataByShippingId/{shippingId}")
	public ResponseEntity<Clinic> getShippingDataByShippingId(@PathVariable String shippingId){
		Clinic result=shippingServiceImpl.getShippingDataByShippingId(shippingId);
		return new ResponseEntity<Clinic>(result,HttpStatus.OK);
	}
	
	
	@GetMapping("/getprocessedorderEvents/{locationId}")
	public ResponseEntity<List<ClinicOrder>> getprocessedorderEvents(@PathVariable Integer locationId){
		List<ClinicOrder> result=shippingServiceImpl.getprocessedorderEvents(locationId);
		return new ResponseEntity<List<ClinicOrder>>(result,HttpStatus.OK);
	}
	
	@GetMapping("/getserialbyproductId/{productId}")
	public ResponseEntity<List<Serial>> getserialbyproductId(@PathVariable Integer productId){
		List<Serial> result=shippingServiceImpl.getSerialByProductId(productId);
		return new ResponseEntity<List<Serial>>(result,HttpStatus.OK);
	}
	
	@GetMapping("/scannedShipmentDetails/{serialId}/{productId}/{orderId}")
	@Transactional
	public ResponseEntity<List<ScannedShipmentDetails>> getScannedShipmentDetails(@PathVariable Integer serialId,@PathVariable Integer productId,@PathVariable Integer orderId){
		System.out.println("cccc");
		List<ScannedShipmentDetails> result=shippingServiceImpl.getScannedShipmentDetails(serialId,productId,orderId);
		return new ResponseEntity<List<ScannedShipmentDetails>>(result,HttpStatus.OK);	
	}
	@GetMapping("/shippedInventoryDetails/{locationId}")
	public ResponseEntity<List<OrderEvents>>shippedInventoryDetails (@PathVariable Integer locationId){
		List<OrderEvents> result=shippingServiceImpl.shippedInventoryDetails(locationId);
		return new ResponseEntity<List<OrderEvents>>(result,HttpStatus.OK);	
	}
	
	
	
	@GetMapping("/changeSerialAndOrderStatusToShipped/{orderId}")
	@Transactional
	public ResponseEntity<String> changeSerialAndOrderStatus(@PathVariable Integer orderId){

		String s=shippingServiceImpl.changeSerialAndOrderStatus(orderId);
		return new ResponseEntity<String>("Status Changed Successfully",HttpStatus.OK);
	}
}
