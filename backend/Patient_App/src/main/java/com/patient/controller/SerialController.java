package com.patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.patient.Entity.Serial;
import com.patient.ServiceImpl.SerialServiceImpl;


@RestController
@CrossOrigin
public class SerialController {
	
	@Autowired
	private SerialServiceImpl serialServiceImpl;
	
	@PostMapping("/createSerial")
	public ResponseEntity<?> createSerial(@RequestBody Serial serial)
	{
		Serial s=serialServiceImpl.createSerial(serial);
		return new ResponseEntity<Serial>(s,HttpStatus.OK);
	}
	
	
	@GetMapping("/getSerialByLocationId/{locationId}")
	public ResponseEntity<List<Serial>> getSerialByLocationId(@PathVariable Integer locationId){
		
		List<Serial> result=serialServiceImpl.getSerialByLocationId(locationId);
		return new ResponseEntity<List<Serial>>(result,HttpStatus.OK);
	}
	
	@GetMapping("/getSerialBySerialId/{serialNo}/{locationId}")
	public ResponseEntity<Serial> getSerialBySerialId(@PathVariable Integer serialNo,@PathVariable Integer locationId){
		Serial result=serialServiceImpl.getSerialBySerialId(serialNo,locationId);
		return new ResponseEntity<Serial>(result,HttpStatus.OK);
	}
	
	@GetMapping("/getSerialBySerialNo/{serialNo}/{locationId}")
	public ResponseEntity<Serial> getSerialBySerialNo(@PathVariable Integer serialNo,@PathVariable Integer locationId){
		Serial result=serialServiceImpl.getSerialBySerialNo(serialNo,locationId);
		return new ResponseEntity<Serial>(result,HttpStatus.OK);
	}
	
	@PostMapping("/changeStatusAvailable/{serialId}/{locationId}/{patientSpecific}/{UserMail}")
	public String changeStatus(@PathVariable("serialId") int serialId,@PathVariable("locationId") int locationId,@PathVariable("patientSpecific") int patientSpecific,@PathVariable("UserMail") String UserMail) {
		String s = serialServiceImpl.changeSerialStatus(serialId, locationId,patientSpecific,UserMail);
		System.out.println("Hello Ship ");
		return s;
		
		
	}
	
	@GetMapping("/getSerialareShipped/{serialNo}/{locationId}")
	public ResponseEntity<List<Serial>> getSerialareShipped(@PathVariable Integer serialNo,@PathVariable Integer locationId){
		List<Serial> list = serialServiceImpl.getSerialShipped(locationId, serialNo);
		return new ResponseEntity<List<Serial>>(list,HttpStatus.OK);
		
	}
	
	@PostMapping("/transferDose/{serialNo}/{gettingLocationId}")
	public void trasferDose(@PathVariable int serialNo,@PathVariable int gettingLocationId) {
		System.out.println("Transferred Dose");
		serialServiceImpl.transferDose(serialNo, gettingLocationId);
	}
	
	@GetMapping("/getSerialReceivedByLocationId/{locationId}")
	public List<Serial> getSerialReceivedByLocationId(@PathVariable int locationId){
		List<Serial> list = serialServiceImpl.getSerialReceivedByLocationId(locationId);
		return list;
	}
	
	@GetMapping("/getSerialDetailsBySerialNo/{serialNo}")
	public Serial getSerialDetailsBySerialNo(@PathVariable int serialNo) {
		Serial s = serialServiceImpl.getSerialDetailsBySerialNo(serialNo);
		return s;
	}
	
	@PostMapping("/removeSerial/{serialNo}")
	public Serial removeSerial(@PathVariable int serialNo) {
		Serial s = serialServiceImpl.removeSerial(serialNo);
		return s;
	}
	

}
