package com.patient.Service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.patient.Entity.PatientFile;

public interface PatientFileService {
	
	public PatientFile uploadPatientFile(MultipartFile multipartFile) throws IOException;
	
	//public void getTest();

}
