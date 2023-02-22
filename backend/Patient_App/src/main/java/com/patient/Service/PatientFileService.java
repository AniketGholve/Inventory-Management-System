package com.patient.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.patient.Entity.Patient;
import com.patient.Entity.PatientFile;
import jakarta.servlet.http.HttpServletResponse;

public interface PatientFileService {
	
	//public PatientFile uploadPatientFile(MultipartFile multipartFile,Patient patient) throws IOException;
	
	
	public List<PatientFile> uploadMultiplePatientFile(MultipartFile[] multipartFile,Patient patient) throws IOException;

	
	//
	//public Patient updateUploadPatientFile(MultipartFile multipartFile,Patient patient) throws IOException;
	
	//public void getTest();
	
	//public void downloadFile(String id);
	
	//public Patient updateUploadMultiplePatientFile(MultipartFile multipartFile,Patient patient) throws IOException;


	public void deleteFile(String fileId);
	public void downloadFile(String id,HttpServletResponse response) throws IOException;
}