package com.patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping("/getSerialBySerialId/{serialId}/{locationId}")
	public ResponseEntity<Serial> getSerialBySerialId(@PathVariable Integer serialId,@PathVariable Integer locationId){
		Serial result=serialServiceImpl.getSerialBySerialId(serialId,locationId);
		return new ResponseEntity<Serial>(result,HttpStatus.OK);
	}

}
