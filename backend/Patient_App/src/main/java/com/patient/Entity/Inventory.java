package com.patient.Entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class Inventory {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="inventory_id")
	private Integer inventoryId;
	@Column(name="enterprise_id")
	private Integer enterpriseId;
	@Column(name="initial")
	private String initial;
	@Column(name="location_id")
	private Integer locationId;
	@Column(name="on_hand")
	private Integer onHand;
	@Column(name="ordered_qty")
	private Integer orderedQty;
	@Column(name="over_due")
	private Date overDue;
	@Column(name="product_id")
	private Integer productId;
	@Column(name="created_on")
	private Date createdOn;
	@Column(name="modified_on")
	private Date modifiedOn;
	@Column(name="expired")
	private Integer expiredQty;
	
	@Transient
	private String productName;

	
	public Integer getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(Integer inventoryId) {
		this.inventoryId = inventoryId;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getInitial() {
		return initial;
	}

	public void setInitial(String initial) {
		this.initial = initial;
	}

	public Integer getLoactionId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public Integer getOnHand() {
		return onHand;
	}

	
	public void setOnHand(Integer onHand) {
		this.onHand = onHand;
	}

	public Integer getOrderedQty() {
		return orderedQty;
	}

	public void setOrderedQty(Integer orderedQty) {
		this.orderedQty = orderedQty;
	}

	public Date getOverDue() {
		return overDue;
	}

	public void setOverDue(Date overDue) {
		this.overDue = overDue;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
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

	public Integer getExpiredQty() {
		return expiredQty;
	}

	public void setExpiredQty(Integer expiredQty) {
		this.expiredQty = expiredQty;
	}

	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getLocationId() {
		return locationId;
	}
	
	
	

	public Inventory(Integer inventoryId, Integer enterpriseId, String initial, Integer locationId, Integer onHand,
			Integer orderedQty, Date overDue, Integer productId, Date createdOn, Date modifiedOn, Integer expiredQty,
			String productName) {
		super();
		this.inventoryId = inventoryId;
		this.enterpriseId = enterpriseId;
		this.initial = initial;
		this.locationId = locationId;
		this.onHand = onHand;
		this.orderedQty = orderedQty;
		this.overDue = overDue;
		this.productId = productId;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
		this.expiredQty = expiredQty;
		this.productName = productName;
	}

	public Inventory() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	

	
	

}
