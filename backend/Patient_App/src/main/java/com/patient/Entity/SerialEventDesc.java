package com.patient.Entity;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SerialEventDesc {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="serial_event_id")
	private Integer serialEventId;
	@Column(name="serial_id")
	private Integer serialId;
	@Column(name="serial_number")
	private Integer serialNumber;
	@Column(name="product_id")
	private String productId;
	@Column(name="status")
	private String status;
	@Column(name="event_date")
	private Date eventDate;
	
	public SerialEventDesc() {}

	public Integer getSerialEventId() {
		return serialEventId;
	}

	public void setSerialEventId(Integer serialEventId) {
		this.serialEventId = serialEventId;
	}

	public Integer getSerialId() {
		return serialId;
	}

	public void setSerialId(Integer serialId) {
		this.serialId = serialId;
	}

	public Integer getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public SerialEventDesc(Integer serialEventId, Integer serialId, Integer serialNumber, String productId,
			String status, Date eventDate) {
		super();
		this.serialEventId = serialEventId;
		this.serialId = serialId;
		this.serialNumber = serialNumber;
		this.productId = productId;
		this.status = status;
		this.eventDate = eventDate;
	}
	
	

	
}