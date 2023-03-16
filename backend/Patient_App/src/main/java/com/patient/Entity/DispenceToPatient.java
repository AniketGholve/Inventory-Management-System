package com.patient.Entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DispenceToPatient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="dispense_id")
	private int dispenseId;
	@Column(name ="product_id")
	private int productId;
	@Column(name ="nurse_id")
	private int nurseId;
	@Column(name ="patient_id")
	private int patientId;
	@Column(name ="physician_id")
	private int physicianId;
	@Column(name ="dispense_date")
	private Date dispenseDate;
	@Column(name ="injection_site")
	private String injectionSite;
	@Column(name ="notes")
	private String notes;
	@Column(name ="initial")
	private String initial;
	@Column(name ="serial_id")
	private int serialId;
	@Column(name ="user_id")
	private int userId;
	@Column(name ="reader_id")
	private int readerId;
	@Column(name ="created_on")
	private Date createdOn;
	@Column(name ="modified_on")
	private Date modifiedOn;
	@Column(name ="serial_event_id")
	private int serialEventId;
	@Column(name ="location_id")
	private int locationId;
	@Column(name ="enterprise_id")
	private int enterprise_id;
	@Column(name ="exp_type")
	private String expType;
	@Column(name ="exp_message")
	private String expMessage;
	@Column(name ="order_num")
	private int orderNum;
	@Column(name ="payment_status")
	private String paymentStatus;
	@Column(name ="revision_notes")
	private String revisionNotes;
	@Column(name ="revision_initial")
	private String revisionInitial;
	@Column(name ="revision_email")
	private String revesionEmail;
	@Column(name ="src_id")
	private int srcId;
	
	public DispenceToPatient() {}

	public DispenceToPatient(int dispenseId, int productId, int nurseId, int patientId, int physicianId,
			Date dispenseDate, String injectionSite, String notes, String initial, int serialId, int userId,
			int readerId, Date createdOn, Date modifiedOn, int serialEventId, int locationId, int enterprise_id,
			String expType, String expMessage, int orderNum, String paymentStatus, String revisionNotes,
			String revisionInitial, String revesionEmail, int srcId) {
		super();
		this.dispenseId = dispenseId;
		this.productId = productId;
		this.nurseId = nurseId;
		this.patientId = patientId;
		this.physicianId = physicianId;
		this.dispenseDate = dispenseDate;
		this.injectionSite = injectionSite;
		this.notes = notes;
		this.initial = initial;
		this.serialId = serialId;
		this.userId = userId;
		this.readerId = readerId;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
		this.serialEventId = serialEventId;
		this.locationId = locationId;
		this.enterprise_id = enterprise_id;
		this.expType = expType;
		this.expMessage = expMessage;
		this.orderNum = orderNum;
		this.paymentStatus = paymentStatus;
		this.revisionNotes = revisionNotes;
		this.revisionInitial = revisionInitial;
		this.revesionEmail = revesionEmail;
		this.srcId = srcId;
	}

	public int getDispenseId() {
		return dispenseId;
	}

	public void setDispenseId(int dispenseId) {
		this.dispenseId = dispenseId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getNurseId() {
		return nurseId;
	}

	public void setNurseId(int nurseId) {
		this.nurseId = nurseId;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getPhysicianId() {
		return physicianId;
	}

	public void setPhysicianId(int physicianId) {
		this.physicianId = physicianId;
	}

	public Date getDispenseDate() {
		return dispenseDate;
	}

	public void setDispenseDate(Date dispenseDate) {
		this.dispenseDate = dispenseDate;
	}

	public String getInjectionSite() {
		return injectionSite;
	}

	public void setInjectionSite(String injectionSite) {
		this.injectionSite = injectionSite;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getInitial() {
		return initial;
	}

	public void setInitial(String initial) {
		this.initial = initial;
	}

	public int getSerialId() {
		return serialId;
	}

	public void setSerialId(int serialId) {
		this.serialId = serialId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getReaderId() {
		return readerId;
	}

	public void setReaderId(int readerId) {
		this.readerId = readerId;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public int getSerialEventId() {
		return serialEventId;
	}

	public void setSerialEventId(int serialEventId) {
		this.serialEventId = serialEventId;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public int getEnterprise_id() {
		return enterprise_id;
	}

	public void setEnterprise_id(int enterprise_id) {
		this.enterprise_id = enterprise_id;
	}

	public String getExpType() {
		return expType;
	}

	public void setExpType(String expType) {
		this.expType = expType;
	}

	public String getExpMessage() {
		return expMessage;
	}

	public void setExpMessage(String expMessage) {
		this.expMessage = expMessage;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getRevisionNotes() {
		return revisionNotes;
	}

	public void setRevisionNotes(String revisionNotes) {
		this.revisionNotes = revisionNotes;
	}

	public String getRevisionInitial() {
		return revisionInitial;
	}

	public void setRevisionInitial(String revisionInitial) {
		this.revisionInitial = revisionInitial;
	}

	public String getRevesionEmail() {
		return revesionEmail;
	}

	public void setRevesionEmail(String revesionEmail) {
		this.revesionEmail = revesionEmail;
	}

	public int getSrcId() {
		return srcId;
	}

	public void setSrcId(int srcId) {
		this.srcId = srcId;
	}
	
	
	
	
}
