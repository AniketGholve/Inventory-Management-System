package com.patient.Entity;



import java.sql.Date;

import com.patient.encryption.AESEncryption;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Patient {
	
	
	@Convert(converter = AESEncryption.class)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Convert(converter = AESEncryption.class)
	@Column(name="patient_id")
	private String  patientId;
	@Convert(converter = AESEncryption.class)
	@Column(name="patient_first_name")
	private String patientFirstName;	
	@Convert(converter = AESEncryption.class)
	@Column(name="patient_last_name")
	private String patientLastName;	
	@Convert(converter = AESEncryption.class)
	@Column(name="patient_mi")
	private String patientMiddleName;		
	@Convert(converter = AESEncryption.class)
	@Column(name="patient_date_of_birth")
	private Date patientDob;	
	@Column(name="patient_status")
	private String patientStatus;
	@Column(name="email")
	private String patientEmail;
	@Column(name="created_on")
	private Date patientCreatedOn;
	@Column(name="modified_on")
	private Date patientModifiedOn;
	@Column(name="location_id")
	private Integer patientLocationId;
	@Column(name="last_dispense_loc_id")
	private Integer patientLastDispenseId;
	@Column(name="enterprise_id")
	private Integer patientEnterpriseId;	
	@Column(name="payer_type")
	private String patientPayerType;	
	@Column(name="pa_needed")
	private Boolean patientPaNeeded;
	@Column(name="independent_inventory")
	private Boolean independentInventory;
	@Column(name="src_id")
	private Integer patientSrcId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getPatientFirstName() {
		return patientFirstName;
	}
	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}
	public String getPatientLastName() {
		return patientLastName;
	}
	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}
	public String getPatientMiddleName() {
		return patientMiddleName;
	}
	public void setPatientMiddleName(String patientMiddleName) {
		this.patientMiddleName = patientMiddleName;
	}
	public Date getPatientDob() {
		return patientDob;
	}
	public void setPatientDob(Date patientDob) {
		this.patientDob = patientDob;
	}
	public String getPatientStatus() {
		return patientStatus;
	}
	public void setPatientStatus(String patientStatus) {
		this.patientStatus = patientStatus;
	}
	public String getPatientEmail() {
		return patientEmail;
	}
	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}
	public Date getPatientCreatedOn() {
		return patientCreatedOn;
	}
	public void setPatientCreatedOn(Date patientCreatedOn) {
		this.patientCreatedOn = patientCreatedOn;
	}
	public Date getPatientModifiedOn() {
		return patientModifiedOn;
	}
	public void setPatientModifiedOn(Date patientModifiedOn) {
		this.patientModifiedOn = patientModifiedOn;
	}
	public Integer getPatientLocationId() {
		return patientLocationId;
	}
	public void setPatientLocationId(Integer patientLocationId) {
		this.patientLocationId = patientLocationId;
	}
	public Integer getPatientLastDispenseId() {
		return patientLastDispenseId;
	}
	public void setPatientLastDispenseId(Integer patientLastDispenseId) {
		this.patientLastDispenseId = patientLastDispenseId;
	}
	public Integer getPatientEnterpriseId() {
		return patientEnterpriseId;
	}
	public void setPatientEnterpriseId(Integer patientEnterpriseId) {
		this.patientEnterpriseId = patientEnterpriseId;
	}
	public String getPatientPayerType() {
		return patientPayerType;
	}
	public void setPatientPayerType(String patientPayerType) {
		this.patientPayerType = patientPayerType;
	}
	public Boolean getPatientPaNeeded() {
		return patientPaNeeded;
	}
	public void setPatientPaNeeded(Boolean patientPaNeeded) {
		this.patientPaNeeded = patientPaNeeded;
	}
	public Boolean getIndependentInventory() {
		return independentInventory;
	}
	public void setIndependentInventory(Boolean independentInventory) {
		this.independentInventory = independentInventory;
	}
	public Integer getPatientSrcId() {
		return patientSrcId;
	}
	public void setPatientSrcId(Integer patientSrcId) {
		this.patientSrcId = patientSrcId;
	}
	public Patient(Integer id, String patientId, String patientFirstName, String patientLastName,
			String patientMiddleName, Date patientDob, String patientStatus, String patientEmail, Date patientCreatedOn,
			Date patientModifiedOn, Integer patientLocationId, Integer patientLastDispenseId,
			Integer patientEnterpriseId, String patientPayerType, Boolean patientPaNeeded, Boolean independentInventory,
			Integer patientSrcId) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.patientFirstName = patientFirstName;
		this.patientLastName = patientLastName;
		this.patientMiddleName = patientMiddleName;
		this.patientDob = patientDob;
		this.patientStatus = patientStatus;
		this.patientEmail = patientEmail;
		this.patientCreatedOn = patientCreatedOn;
		this.patientModifiedOn = patientModifiedOn;
		this.patientLocationId = patientLocationId;
		this.patientLastDispenseId = patientLastDispenseId;
		this.patientEnterpriseId = patientEnterpriseId;
		this.patientPayerType = patientPayerType;
		this.patientPaNeeded = patientPaNeeded;
		this.independentInventory = independentInventory;
		this.patientSrcId = patientSrcId;
	}
	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	
	
	
	
}
