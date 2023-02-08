package com.emailzip.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emailzip.Entity.EmailEntity;
import com.emailzip.Entity.ZipEntity;
import com.emailzip.ServiceImpl.ZipServiceImpl;

import jakarta.mail.MessagingException;

@RestController
public class ZipController {
	
	@Autowired
	private ZipServiceImpl zipServiceImpl;
	
	@PostMapping("/insertData")
	public ResponseEntity<ZipEntity> createData(@RequestBody ZipEntity zipEntity ){
		
		ZipEntity zipInsertedData=zipServiceImpl.createData(zipEntity);
		return new ResponseEntity<ZipEntity>(zipInsertedData,HttpStatus.CREATED);
		
	}
	
	
	@PostMapping("/zipAndMail")
	public ResponseEntity<String> zipAndMail(@RequestBody EmailEntity email) throws IOException, MessagingException{
		
		String s=zipServiceImpl.zipAndMail(email);
		return new ResponseEntity<String>(s,HttpStatus.OK);
		
		
	}

}
