package com.patient.Entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class DispenseToPatient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="dispense_id")
	private Integer dispenseId;
	@Column(name ="product_id")
	private Integer productId;
	@Column(name ="nurse_id")
	private Integer nurseId;
	@Column(name ="physician_id")
	private Integer physicianId;
	@Column(name ="dispense_date")
	private Date dispenseDate;
	@Column(name ="injection_site")
	private String injectionSite;
	@Column(name ="notes")
	private String notes;
	@Column(name ="initial")
	private String initial;
	@Column(name ="serial_id")
	private Integer serialId;
	@Column(name ="user_id")
	private Integer userId;
	@Column(name ="reader_id")
	private Integer readerId;
	@Column(name ="created_on")
	private Date createdOn;
	@Column(name ="modified_on")
	private Date modifiedOn;
	@Column(name ="serial_event_id")
	private Integer serialEventId;
	@Column(name ="location_id")
	private Integer locationId;
	@Column(name ="enterprise_id")
	private Integer enterprise_id;
	@Column(name ="exp_type")
	private String expType;
	@Column(name ="exp_message")
	private String expMessage;
	@Column(name ="order_num")
	private Integer orderNum;
	@Column(name ="payment_status")
	private String paymentStatus;
	@Column(name ="revision_notes")
	private String revisionNotes;
	@Column(name ="revision_initial")
	private String revisionInitial;
	@Column(name ="revision_email")
	private String revesionEmail;
	@Column(name ="src_id")
	private Integer srcId;
	
//	@OneToOne
//	@JoinColumn(name = "patientId")
//	private Patient id;
	
	@Column(name = "patient_id")
	private Integer patientId;

	public Integer getDispenseId() {
		return dispenseId;
	}

	public void setDispenseId(Integer dispenseId) {
		this.dispenseId = dispenseId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getNurseId() {
		return nurseId;
	}

	public void setNurseId(Integer nurseId) {
		this.nurseId = nurseId;
	}

	public Integer getPhysicianId() {
		return physicianId;
	}

	public void setPhysicianId(Integer physicianId) {
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

	public Integer getSerialId() {
		return serialId;
	}

	public void setSerialId(Integer serialId) {
		this.serialId = serialId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getReaderId() {
		return readerId;
	}

	public void setReaderId(Integer readerId) {
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

	public Integer getSerialEventId() {
		return serialEventId;
	}

	public void setSerialEventId(Integer serialEventId) {
		this.serialEventId = serialEventId;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public Integer getEnterprise_id() {
		return enterprise_id;
	}

	public void setEnterprise_id(Integer enterprise_id) {
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

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
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

	public Integer getSrcId() {
		return srcId;
	}

	public void setSrcId(Integer srcId) {
		this.srcId = srcId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	@Override
	public String toString() {
		return "DispenseToPatient [dispenseId=" + dispenseId + ", productId=" + productId + ", nurseId=" + nurseId
				+ ", physicianId=" + physicianId + ", dispenseDate=" + dispenseDate + ", injectionSite=" + injectionSite
				+ ", notes=" + notes + ", initial=" + initial + ", serialId=" + serialId + ", userId=" + userId
				+ ", readerId=" + readerId + ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn
				+ ", serialEventId=" + serialEventId + ", locationId=" + locationId + ", enterprise_id=" + enterprise_id
				+ ", expType=" + expType + ", expMessage=" + expMessage + ", orderNum=" + orderNum + ", paymentStatus="
				+ paymentStatus + ", revisionNotes=" + revisionNotes + ", revisionInitial=" + revisionInitial
				+ ", revesionEmail=" + revesionEmail + ", srcId=" + srcId + ", patientId=" + patientId + "]";
	}

	public DispenseToPatient(Integer dispenseId, Integer productId, Integer nurseId, Integer physicianId,
			Date dispenseDate, String injectionSite, String notes, String initial, Integer serialId, Integer userId,
			Integer readerId, Date createdOn, Date modifiedOn, Integer serialEventId, Integer locationId,
			Integer enterprise_id, String expType, String expMessage, Integer orderNum, String paymentStatus,
			String revisionNotes, String revisionInitial, String revesionEmail, Integer srcId, Integer patientId) {
		super();
		this.dispenseId = dispenseId;
		this.productId = productId;
		this.nurseId = nurseId;
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
		this.patientId = patientId;
	}

	public DispenseToPatient() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	
	
	
	
	
}
