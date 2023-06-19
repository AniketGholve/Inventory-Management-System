package com.patient.Entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;


@Entity
public class Serial {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="serial_id")
	private Integer serialId;
//	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="serial_number")
	private Integer serialNumber;
	@Column(name="ndc")
	private Integer ndc;
	@Column(name="lot")
	private Integer lot;
	@Column(name="expiry_date")
	private Date expiryDate;
	@Column(name="serial_status")
	private String serialStatus;
	@Column(name="created_on")
	private Date createdOn;
	@Column(name="enterprise_id")
	private Integer enterpriseId;
	@Column(name="location_id")
	private Integer locationId;
	@Column(name="product_id")
	private Integer productId;
	@Column(name="patient_specific")
	private Integer  patientSpecific;
	@Column(name="src_id")
	private Integer srcId;
	@Column(name="Transited_dose")
	private Boolean transitedDose;
	@Transient
	private String ClinicName;
	@Transient
	private String EnterpriseName;
	@Transient
	private SerialEventDesc serialEventDesc;
	@Transient 
	private String productName;
	
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public SerialEventDesc getSerialEventDesc() {
		return serialEventDesc;
	}
	public void setSerialEventDesc(SerialEventDesc serialEventDesc) {
		this.serialEventDesc = serialEventDesc;
	}
	public Boolean getTransitedDose() {
		return transitedDose;
	}
	public void setTransitedDose(Boolean transitedDose) {
		this.transitedDose = transitedDose;
	}
	public String getClinicName() {
		return ClinicName;
	}
	public void setClinicName(String clinicName) {
		ClinicName = clinicName;
	}
	public String getEnterpriseName() {
		return EnterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		EnterpriseName = enterpriseName;
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
	public String getSerialStatus() {
		return serialStatus;
	}
	public void setSerialStatus(String serialStatus) {
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
	public Integer getPatientSpecific() {
		return patientSpecific;
	}
	public void setPatientSpecific(Integer patientSpecific) {
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
			String serialStatus, Date createdOn, Integer enterpriseId, Integer locationId, Integer productId,
			Integer patientSpecific, Integer srcId,Boolean transitedDose, String clinicName, String enterpriseName, SerialEventDesc serialEventDesc) {
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
		this.transitedDose = transitedDose;
		this.ClinicName = clinicName;
		this.EnterpriseName = enterpriseName;
		this.serialEventDesc = serialEventDesc;
	}
	public Serial() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	

}
