package com.patient.Entity;

public class ScannedShipmentDetails {
	
	private String dose;
	private Integer quantity;
	private String status;
	private String batch;
	private Integer productId;
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getDose() {
		return dose;
	}
	public void setDose(String dose) {
		this.dose = dose;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	@Override
	public String toString() {
		return "ScannedShipmentDetails [dose=" + dose + ", quantity=" + quantity + ", status=" + status + ", batch="
				+ batch + ", productId=" + productId + "]";
	}
	public ScannedShipmentDetails(String dose, Integer quantity, String status, String batch, Integer productId) {
		super();
		this.dose = dose;
		this.quantity = quantity;
		this.status = status;
		this.batch = batch;
		this.productId = productId;
	}
	public ScannedShipmentDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
