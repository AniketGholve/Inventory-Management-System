package com.patient.Entity;

import java.beans.JavaBean;

import org.springframework.stereotype.Service;

import jakarta.persistence.Id;

@Service
public class MinDaysToDis {
	
	@Id
	private int productId;
	private String productName;
	private int minimumDays;
	
	public MinDaysToDis() {}

	public MinDaysToDis(int productId, String productName, int minimumDays) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.minimumDays = minimumDays;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getMinimumDays() {
		return minimumDays;
	}

	public void setMinimumDays(int minimumDays) {
		this.minimumDays = minimumDays;
	}
	
	
}
