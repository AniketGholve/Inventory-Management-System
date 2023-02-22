package com.patient.controller;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patient.Entity.Patient;
import com.patient.Entity.PatientFile;
import com.patient.ServiceImpl.PatientFileServiceImpl;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
public class PatientFileController {
	
	@Autowired
	private PatientFileServiceImpl patientFileServiceImpl;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	

	
	@PreAuthorize("hasAuthority('CLP')")
	@PostMapping(value="/uploadMultiplePatientFile",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_OCTET_STREAM_VALUE})
	public ResponseEntity<?> uploadMultiplePatientFile(@RequestPart("file") MultipartFile[] files,@RequestPart("patient")  String p) throws JsonMappingException, JsonProcessingException
	{
		System.out.println(p);
		
		//converting a string to json
		Patient patient=objectMapper.readValue(p, Patient.class);
		
		
		for(MultipartFile file:files)
		{
			System.out.println("lkjhg");
			System.out.println("original file name "+file.getOriginalFilename());
			System.out.println("get size "+file.getSize());
			System.out.println("get content Type "+file.getContentType());
			System.out.println("get file name "+file.getName());
			//System.out.println(patient.getPatientFirstName());
			
			
		}
		System.out.println(patient.getId());
		try {
			
			List<PatientFile> patientFileList=patientFileServiceImpl.uploadMultiplePatientFile(files,patient);
			return new ResponseEntity<Object>(patientFileList,HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Some thing went wrong please try again",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	@PreAuthorize("hasAuthority('CLP')")
	@PutMapping(value="/updateUploadMultiplePatientFile",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_OCTET_STREAM_VALUE})
	public ResponseEntity<?> updateUploadMultiplePatientFile(@RequestPart("file") MultipartFile[] files,@RequestPart("patient")  String p) throws JsonMappingException, JsonProcessingException
	{
		System.out.println(p);
		
		//converting a string to json
		Patient patient=objectMapper.readValue(p, Patient.class);
		
		
		for(MultipartFile file:files)
		{
			System.out.println("lkjhg");
			System.out.println("original file name "+file.getOriginalFilename());
			System.out.println("get size "+file.getSize());
			System.out.println("get content Type "+file.getContentType());
			System.out.println("get file name "+file.getName());
			//System.out.println(patient.getPatientFirstName());
			
			
		}
		System.out.println(patient.getId());
		try {
			
			List<PatientFile> patientFileList=patientFileServiceImpl.uploadMultiplePatientFile(files,patient);
			return new ResponseEntity<Object>(patientFileList,HttpStatus.OK);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Some thing went wrong please try again",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	@PreAuthorize("hasAuthority('CLP')")
	@GetMapping("/downloadFile")
	public void downloadFile(@RequestParam("id") String id,HttpServletResponse response) throws IOException
	{
		patientFileServiceImpl.downloadFile(id,response);
	}
	
	@PreAuthorize("hasAuthority('CLP')")
	@DeleteMapping("/deleteFile")
	public void deleteFile(@RequestParam("fileId") String fileId)
	{
		patientFileServiceImpl.deleteFile(fileId);
	}
 

}