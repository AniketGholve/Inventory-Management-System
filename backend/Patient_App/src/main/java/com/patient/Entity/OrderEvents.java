package com.patient.Entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class OrderEvents {
	
	@Id
	@Column(name="order_event_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderEventId;
	@Column(name="status_id")
	private Integer statusId;
	@Column(name="activity_date")
	private Date activityDate;
	@Column(name="quantity")
	private Integer quantity;
	@Column(name="event_desc")
	private String eventDesc;
	@Column(name="order_id")
	private Integer orderId;
	@Column(name="location_id")
	private Integer locationId;
	@Column(name="product_id")
	private Integer productId;
	@Column(name="package_type")
	private String packageType;
	@Column(name="enterprise_id")
	private Integer enterpriseId;
	@Column(name="delivery_order_id")
	private Integer deliveryOrderId;
	@Column(name="shipment_tracking_id")
	private Integer shipmentTrackingId;
	@Column(name="user_id")
	private Integer userId;
	@Column(name="src_id")
	private Integer srcId;
	
	@Transient
	private int shiptoId;
	@Transient
	private String shiptoName;
	@Transient
	private String poNumber;
	public OrderEvents() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderEvents(Integer orderEventId, Integer statusId, Date activityDate, Integer quantity, String eventDesc,
			Integer orderId, Integer locationId, Integer productId, String packageType, Integer enterpriseId,
			Integer deliveryOrderId, Integer shipmentTrackingId, Integer userId, Integer srcId, int shiptoId,
			String shiptoName, String poNumber) {
		super();
		this.orderEventId = orderEventId;
		this.statusId = statusId;
		this.activityDate = activityDate;
		this.quantity = quantity;
		this.eventDesc = eventDesc;
		this.orderId = orderId;
		this.locationId = locationId;
		this.productId = productId;
		this.packageType = packageType;
		this.enterpriseId = enterpriseId;
		this.deliveryOrderId = deliveryOrderId;
		this.shipmentTrackingId = shipmentTrackingId;
		this.userId = userId;
		this.srcId = srcId;
		this.shiptoId = shiptoId;
		this.shiptoName = shiptoName;
		this.poNumber = poNumber;
	}
	public Integer getOrderEventId() {
		return orderEventId;
	}
	public void setOrderEventId(Integer orderEventId) {
		this.orderEventId = orderEventId;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public Date getActivityDate() {
		return activityDate;
	}
	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getEventDesc() {
		return eventDesc;
	}
	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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
	public String getPackageType() {
		return packageType;
	}
	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}
	public Integer getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public Integer getDeliveryOrderId() {
		return deliveryOrderId;
	}
	public void setDeliveryOrderId(Integer deliveryOrderId) {
		this.deliveryOrderId = deliveryOrderId;
	}
	public Integer getShipmentTrackingId() {
		return shipmentTrackingId;
	}
	public void setShipmentTrackingId(Integer shipmentTrackingId) {
		this.shipmentTrackingId = shipmentTrackingId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getSrcId() {
		return srcId;
	}
	public void setSrcId(Integer srcId) {
		this.srcId = srcId;
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
	public String getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	

}
