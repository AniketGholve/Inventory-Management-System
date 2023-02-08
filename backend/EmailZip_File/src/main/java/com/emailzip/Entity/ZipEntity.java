package com.emailzip.Entity;



import java.util.Date;

import org.springframework.data.jpa.repository.Temporal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.TemporalType;
 


@Entity
@Table(name="zipEntity")
public class ZipEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String fileName;
	private String fileType;
	private String fileLocation;
	
	private Date createdDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getFileLocation() {
		return fileLocation;
	}
	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public ZipEntity(int id, String fileName, String fileType, String fileLocation, Date createdDate) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.fileType = fileType;
		this.fileLocation = fileLocation;
		this.createdDate = createdDate;
	}
	public ZipEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "ZipEntity [id=" + id + ", fileName=" + fileName + ", fileType=" + fileType + ", fileLocation="
				+ fileLocation + ", createdData=" + createdDate + "]";
	}
	
	
	
	
	
	
	

}
