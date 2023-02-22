package com.patient.Entity;

import java.sql.Date;
import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
@Component
public class PatientFile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String fileId;
	private String fileName;
	private Date uploadTime;
	private Long size;
	
	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] fileData;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	@JsonBackReference
//	private Patient patient;
	
	
	
	@ManyToOne 
	@JsonBackReference
	private Patient patient;
	
	

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public PatientFile(String fileId, String fileName, Date uploadTime, Long size, byte[] fileData, Patient patient) {
		super();
		this.fileId = fileId;
		this.fileName = fileName;
		this.uploadTime = uploadTime;
		this.size = size;
		this.fileData = fileData;
		this.patient = patient;
	}

	public PatientFile() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "PatientFile [fileId=" + fileId + ", fileName=" + fileName + ", uploadTime=" + uploadTime + ", size="
				+ size + ", fileData=" + Arrays.toString(fileData) + ", patient=" + patient + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
