package com.patient.Entity;


import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Notifications {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "Status")
	private String Status;
	@Column(name = "Message")
	private String Message;
	@Column(name = "notified_time")
	private Date NotifiedTime;
	
	public Notifications() {}

	public Notifications(int id, String status, String message, Date notifiedTime) {
		super();
		this.id = id;
		Status = status;
		Message = message;
		NotifiedTime = notifiedTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public Date getNotifiedTime() {
		return NotifiedTime;
	}

	public void setNotifiedTime(Date notifiedTime) {
		NotifiedTime = notifiedTime;
	}
	
	
	
}
