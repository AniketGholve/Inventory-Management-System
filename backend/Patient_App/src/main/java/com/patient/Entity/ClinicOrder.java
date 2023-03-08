package com.patient.Entity;

import java.sql.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ClinicOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private int orderId;
	@Column(name = "order_datetime")
	private Date orderDatetime;
	@Column(name = "po_number")
	private String poNumber;
	@Column(name = "person_initial")
	private String personInitial;
	@Column(name = "order_note")
	private String orderNote;
	@Column(name = "location_id")
	private int locationId;
	@Column(name = "enterprise_id")
	private int enterpriseId;
	@Column(name = "user_id")
	private int userId;
	@Column(name = "billto_id")
	private int billtoId;
	@Column(name = "billto_name")
	private String billtoName;
	@Column(name = "shipto_id")
	private int shiptoId;
	@Column(name = "shipto_name")
	private String shiptoName;
	@Column(name = "activity_date")
	private Date activityDate;
	@Column(name = "shipfrom_id")
	private int shipfromId;
	@Column(name = "meu")
	private String meu;
	@Column(name = "order_status_id")
	private int orderStatusId;
	@Column(name = "order_type")
	private String orderType;
	@Column(name = "src_id")
	private int srcId;

	
//	public ClinicOrder() {}
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public Date getOrderDatetime() {
		return orderDatetime;
	}
	public void setOrderDatetime(Date orderDatetime) {
		this.orderDatetime = orderDatetime;
	}
	public String getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	public String getPersonInitial() {
		return personInitial;
	}
	public void setPersonInitial(String personInitial) {
		this.personInitial = personInitial;
	}
	public String getOrderNote() {
		return orderNote;
	}
	public void setOrderNote(String orderNote) {
		this.orderNote = orderNote;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public int getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(int enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getBilltoId() {
		return billtoId;
	}
	public void setBilltoId(int billtoId) {
		this.billtoId = billtoId;
	}
	public String getBilltoName() {
		return billtoName;
	}
	public void setBilltoName(String billtoName) {
		this.billtoName = billtoName;
	}
	public int getShiptoId() {
		return shiptoId;
	}
	public void setShiptoId(int shiptoId) {
		this.shiptoId = shiptoId;
	}
	public String getShiptoName() {
		return shiptoName;
	}
	public void setShiptoName(String shiptoName) {
		this.shiptoName = shiptoName;
	}
	public Date getActivityDate() {
		return activityDate;
	}
	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}
	public int getShipfromId() {
		return shipfromId;
	}
	public void setShipfromId(int shipfromId) {
		this.shipfromId = shipfromId;
	}
	public String getMeu() {
		return meu;
	}
	public void setMeu(String meu) {
		this.meu = meu;
	}
	public int getOrderStatusId() {
		return orderStatusId;
	}
	public void setOrderStatusId(int orderStatusId) {
		this.orderStatusId = orderStatusId;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public int getSrcId() {
		return srcId;
	}
	public void setSrcId(int srcId) {
		this.srcId = srcId;
	}

	public ClinicOrder(int orderId, Date orderDatetime, String poNumber, String personInitial, String orderNote,
			int locationId, int enterpriseId, int userId, int billtoId, String billtoName, int shiptoId,
			String shiptoName, Date activityDate, int shipfromId, String meu, int orderStatusId, String orderType,
			int srcId) {
		super();
		this.orderId = orderId;
		this.orderDatetime = orderDatetime;
		this.poNumber = poNumber;
		this.personInitial = personInitial;
		this.orderNote = orderNote;
		this.locationId = locationId;
		this.enterpriseId = enterpriseId;
		this.userId = userId;
		this.billtoId = billtoId;
		this.billtoName = billtoName;
		this.shiptoId = shiptoId;
		this.shiptoName = shiptoName;
		this.activityDate = activityDate;
		this.shipfromId = shipfromId;
		this.meu = meu;
		this.orderStatusId = orderStatusId;
		this.orderType = orderType;
		this.srcId = srcId;
	}
	public ClinicOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}


	
	
