package com.patient.ServiceImpl;


import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.patient.Entity.Patient;
import com.patient.Entity.PatientFile;
import com.patient.Repo.PatientFileRepo;
import com.patient.Repo.PatientRepo;
import com.patient.Service.PatientFileService;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;


@Service
public class PatientFileServiceImpl implements PatientFileService {
	
	@Autowired
	private PatientFileRepo patientFileRepo;
	
	@Autowired
	private PatientRepo patientRepo;
	
		
	@Override
	public List<PatientFile> uploadMultiplePatientFile(MultipartFile[] multipartFile, Patient patient) throws IOException {
		// TODO Auto-generated method stub
		long m=System.currentTimeMillis();
		List<PatientFile> patientFilelist=new ArrayList<>();
		Patient p=patientRepo.findById(patient.getId()).orElseThrow();
		for (MultipartFile file:multipartFile) {
			
			PatientFile patientFile=new PatientFile();
			patientFile.setCreatedDate(new Date(m));
			patientFile.setEnterpriseId(null);
			patientFile.setFileStatus(null);
			patientFile.setLocationId(null);
			patientFile.setMessageDetails(null);
			patientFile.setMsgFormatVersion(null);
			patientFile.setOperation(null);
			patientFile.setReceiverId(null);
			patientFile.setReceiverName(null);
			patientFile.setRecvthrough(null);
			patientFile.setRequest(null);
			patientFile.setResponse(null);
			patientFile.setSenderId(null);
			patientFile.setSenderName(null);
			patientFile.setSoapMessageType(null);
			patientFile.setSourceDataFile(null);
			patientFile.setSourceFileFormatId(null);
			patientFile.setSourceFileSubformatId(null);
			patientFile.setSourceMimeType(null);
			patientFile.setTransMimeType(null);
			patientFile.setTransDataFile(null);
			patientFile.setTransFileFormatId(null);
			patientFile.setTransFileSubformatId(null);
			patientFile.setUpdatedDate(new Date(m));
			patientFile.setFileName(file.getOriginalFilename());
			patientFile.setSize(file.getSize());
			patientFile.setPatient(p);
			patientFile.setFileData(file.getBytes());
			patientFileRepo.save(patientFile);
			patientFilelist.add(patientFile);
			
	}
		
		p.setPatientFile(patientFilelist);
		return patientFilelist;
}
	public void downloadFile(String id,HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
		PatientFile patientFile=patientFileRepo.findById(id).orElseThrow();
		response.setContentType("application/octet-stream");
		String headerKey="Content-Disposition";
		String headerValue="attachment; filename="+patientFile.getFileName();
		
		response.setHeader(headerKey, headerValue);
		ServletOutputStream outputStream=response.getOutputStream();
		outputStream.write(patientFile.getFileData());
		outputStream.close();
		
	}

	@Override
	public void deleteFile(String fileId) {
		// TODO Auto-generated method stub
		
		patientFileRepo.deleteById(fileId);
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@Override
//	public Patient updateUploadPatientFile(MultipartFile multipartFile,Integer patientId,String FileId) throws IOException {
//		// TODO Auto-generated method stub
//		//System.out.println(patient.toString());
//		System.out.println("update upload patient filr");
//			PatientFile patientFile=this.patientFileRepo.findById(FileId).orElseThrow();
//			System.out.println(patientFile.getFileId());
//			Patient patient=patientRepo.findById(patientId).orElseThrow();
//			File f=new File(patientFile.getFilePath());
//			f.delete();
//			Files.copy(multipartFile.getInputStream(), Paths.get(uploadDir+File.separator+multipartFile.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
//			long m=System.currentTimeMillis();
//			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(patientFile.getFileId()).toUriString();
//			//System.out.println(fileDownloadUri);
//			System.out.println(multipartFile.getOriginalFilename());
//			System.out.println(uploadDir+File.separator+multipartFile.getOriginalFilename());
//			System.out.println("kjhgfd");
//			patientFile.setDownloadUri(fileDownloadUri);
//			patientFile.setUploadTime(new Date(m));
//			patientFile.setFileName(multipartFile.getOriginalFilename());
//			patientFile.setSize(multipartFile.getSize());
//			patientFile.setFilePath(uploadDir+File.separator+multipartFile.getOriginalFilename());
//			System.out.println(this.patientFile.toString());
//			//System.out.println(patient.toString());
//			patientFile.setPatient(patient);
//			//Patient p=this.patientRepo.findById(patient.getId()).orElseThrow();
//			//this.patientFile.setPatient(p);
//			System.out.println(uploadDir+File.separator+multipartFile.getOriginalFilename());
//			patientFileRepo.save(patientFile);
//			
//			Patient p=this.patientRepo.findById(patient.getId()).orElseThrow();
//			//this.patientRepo.save(patient);
//			patientFile.setDownloadUri(fileDownloadUri);
//	
//			return p;
//	}

//	@Override
//	public void getTest() {
//		// TODO Auto-generated method stub
//		System.out.println("lkjhgfgl");
//		System.out.println(uploadDir);
//		
//
//		
//	}
	
	
	
	
	
//	@Override
//	public PatientFile uploadPatientFile(MultipartFile multipartFile,Patient patient) throws IOException {
//		// TODO Auto-generated method stub
//		System.out.println(patient.toString());
//			long m=System.currentTimeMillis();
//			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(patientFile.getFileId()).toUriString();
//			System.out.println(fileDownloadUri);
//			this.patientFile.setDownloadUri(fileDownloadUri);
//			this.patientFile.setUploadTime(new Date(m));
//			this.patientFile.setFileName(multipartFile.getOriginalFilename());
//			this.patientFile.setSize(multipartFile.getSize());
//			this.patientFile.setFilePath(uploadDir+File.separator+multipartFile.getOriginalFilename());
//			//System.out.println(patient.toString());
//			//this.patientFile.setPatient(patient);
//			Patient p=this.patientRepo.findById(patient.getId()).orElseThrow();
//			this.patientFile.setPatient(p);
//			Files.copy(multipartFile.getInputStream(), Paths.get(uploadDir+File.separator+multipartFile.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
//			patientFileRepo.save(patientFile);
//			
//			
//			//this.patientRepo.save(patient);
//			patientFile.setDownloadUri(fileDownloadUri);
//	
//			return patientFile;
//	}
	
	
}
