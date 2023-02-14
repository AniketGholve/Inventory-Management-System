package com.patient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	

}
