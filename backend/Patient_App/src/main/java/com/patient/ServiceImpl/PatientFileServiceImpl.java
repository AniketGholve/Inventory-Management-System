package com.patient.ServiceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.patient.Entity.PatientFile;
import com.patient.Repo.PatientFileRepo;
import com.patient.Service.PatientFileService;


@Service
public class PatientFileServiceImpl implements PatientFileService {
	
	@Autowired
	private PatientFileRepo patientFileRepo;

	
	private String uploadDir=new ClassPathResource("").getFile().getAbsolutePath();

	
	public PatientFileServiceImpl() throws IOException
	{
		
	}
	@Override
	public PatientFile uploadPatientFile(MultipartFile multipartFile) throws IOException {
		// TODO Auto-generated method stub
		long m=System.currentTimeMillis();

		PatientFile patientFile=new PatientFile(multipartFile.getOriginalFilename(), "/",multipartFile.getContentType(), uploadDir+File.separator+multipartFile.getOriginalFilename(),new Date(m),multipartFile.getSize());
		Files.copy(multipartFile.getInputStream(), Paths.get(uploadDir+File.separator+multipartFile.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);

		patientFileRepo.save(patientFile);
				
	
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
			.path(uploadDir)
			.path(multipartFile.getOriginalFilename())
			.toUriString();
			patientFile.setDownloadUri(fileDownloadUri);
	
	return patientFile;

	
		

	}

//	@Override
//	public void getTest() {
//		// TODO Auto-generated method stub
//		System.out.println("lkjhgfgl");
//		System.out.println(uploadDir);
//		
//
//		
//	}
	
	
}
