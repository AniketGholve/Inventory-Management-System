package com.patient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.patient.Entity.PatientFile;
import com.patient.ServiceImpl.PatientFileServiceImpl;

@RestController
@CrossOrigin
public class PatientFileController {
	
	@Autowired
	private PatientFileServiceImpl patientFileServiceImpl;
	
	
	
	@PostMapping("/uploadFile")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file)
	{
		System.out.println("original file name "+file.getOriginalFilename());
		System.out.println("get size "+file.getSize());
		System.out.println("get content Type "+file.getContentType());
		System.out.println("get file name "+file.getName());		
		try {
			
			if(file.isEmpty())
			{
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request Must contain file");
			}
			
			PatientFile patientFile=patientFileServiceImpl.uploadPatientFile(file);
			return new ResponseEntity<Object>(patientFile,HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Some thing went wrong please try again",HttpStatus.INTERNAL_SERVER_ERROR);
		
		
	}
	
//	@GetMapping("/testfile")
//	public void getfile()
//	{
//		patientFileServiceImpl.getTest();
//	}
//	
	

}
