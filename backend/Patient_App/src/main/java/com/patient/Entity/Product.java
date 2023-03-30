package com.patient.Entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class Product {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_id")
	private Integer productId;
	@Column(name="product_name")
	private String productName;
	@Column(name="enterprise_id")
	private Integer enterpriseId;
	@Column(name="active")
	private Boolean active;
	@Column(name="ndc")
	private Integer ndc;
	@Column(name="package_type")
	private String packageType; 
	@Column(name="manufacturer")
	private String manufacturer;
	@Column(name="created_on")
	private Date createdOn; 
	@Column(name="modified_on")
	private Date modifiedOn;
	@Column(name="gtin")
	private String gtin;
	@Column(name="minimum_days")
	private Integer minimumDays;
	@Transient
	private Integer quantity;
	@Transient
	private String Status;
	
	
	
	public Integer getMinimumDays() {
		return minimumDays;
	}
	public void setMinimumDays(Integer minimumDays) {
		this.minimumDays = minimumDays;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Integer getNdc() {
		return ndc;
	}
	public void setNdc(Integer ndc) {
		this.ndc = ndc;
	}
	public String getPackageType() {
		return packageType;
	}
	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
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
	public String getGtin() {
		return gtin;
	}
	public void setGtin(String gtin) {
		this.gtin = gtin;
	}
	
	
	public Product(Integer productId, String productName, Integer enterpriseId, Boolean active, Integer ndc,
			String packageType, String manufacturer, Date createdOn, Date modifiedOn, String gtin, Integer minimumDays,
			Integer quantity, String status) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.enterpriseId = enterpriseId;
		this.active = active;
		this.ndc = ndc;
		this.packageType = packageType;
		this.manufacturer = manufacturer;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
		this.gtin = gtin;
		this.minimumDays = minimumDays;
		this.quantity = quantity;
		this.Status = status;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

	

}
