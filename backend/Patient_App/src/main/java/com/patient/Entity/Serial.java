package com.patient.Entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Serial {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="serial_id")
	private Integer serialId;
	@Column(name="serial_number")
	private Integer serialNumber;
	@Column(name="ndc")
	private Integer ndc;
	@Column(name="lot")
	private Integer lot;
	@Column(name="expiry_date")
	private Date expiryDate;
	@Column(name="serial_status")
	private Boolean serialStatus;
	@Column(name="created_on")
	private Date createdOn;
	@Column(name="enterprise_id")
	private Integer enterpriseId;
	@Column(name="location_id")
	private Integer locationId;
	@Column(name="product_id")
	private Integer productId;
	@Column(name="patient_specific")
	private String  patientSpecific;
	@Column(name="src_id")
	private Integer srcId;
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
	public Integer getNdc() {
		return ndc;
	}
	public void setNdc(Integer ndc) {
		this.ndc = ndc;
	}
	public Integer getLot() {
		return lot;
	}
	public void setLot(Integer lot) {
		this.lot = lot;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public Boolean getSerialStatus() {
		return serialStatus;
	}
	public void setSerialStatus(Boolean serialStatus) {
		this.serialStatus = serialStatus;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Integer getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public Integer getLocationId() {
		return locationId;
	}
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getPatientSpecific() {
		return patientSpecific;
	}
	public void setPatientSpecific(String patientSpecific) {
		this.patientSpecific = patientSpecific;
	}
	public Integer getSrcId() {
		return srcId;
	}
	public void setSrcId(Integer srcId) {
		this.srcId = srcId;
	}
	@Override
	public String toString() {
		return "Serial [serialId=" + serialId + ", serialNumber=" + serialNumber + ", ndc=" + ndc + ", lot=" + lot
				+ ", expiryDate=" + expiryDate + ", serialStatus=" + serialStatus + ", createdOn=" + createdOn
				+ ", enterpriseId=" + enterpriseId + ", locationId=" + locationId + ", productId=" + productId
				+ ", patientSpecific=" + patientSpecific + ", srcId=" + srcId + "]";
	}
	public Serial(Integer serialId, Integer serialNumber, Integer ndc, Integer lot, Date expiryDate,
			Boolean serialStatus, Date createdOn, Integer enterpriseId, Integer locationId, Integer productId,
			String patientSpecific, Integer srcId) {
		super();
		this.serialId = serialId;
		this.serialNumber = serialNumber;
		this.ndc = ndc;
		this.lot = lot;
		this.expiryDate = expiryDate;
		this.serialStatus = serialStatus;
		this.createdOn = createdOn;
		this.enterpriseId = enterpriseId;
		this.locationId = locationId;
		this.productId = productId;
		this.patientSpecific = patientSpecific;
		this.srcId = srcId;
	}
	public Serial() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
