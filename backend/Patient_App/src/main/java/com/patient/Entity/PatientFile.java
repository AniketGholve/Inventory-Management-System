package com.patient.Entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PatientFile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String fileId;
	private String fileName;
	private String downloadUri;
	private String filePath;
	private Date uploadTime;
	private Long size;
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
	public String getDownloadUri() {
		return downloadUri;
	}
	public void setDownloadUri(String downloadUri) {
		this.downloadUri = downloadUri;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
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
	
	public PatientFile(String fileId, String fileName, String downloadUri, String filePath, Date uploadTime,
			Long size) {
		super();
		this.fileId = fileId;
		this.fileName = fileName;
		this.downloadUri = downloadUri;
		this.filePath = filePath;
		this.uploadTime = uploadTime;
		this.size = size;
	}
	public PatientFile() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "PatientFile [fileId=" + fileId + ", fileName=" + fileName + ", downloadUri=" + downloadUri
				+ ", filePath=" + filePath + ", uploadTime=" + uploadTime + ", size=" + size + "]";
	}
	
	
	
	
	
	
	

}
