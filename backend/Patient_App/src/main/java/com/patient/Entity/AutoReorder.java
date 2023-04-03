package com.patient.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AutoReorder {
	
	@Id
	@Column(name = "product_name")
	private String ProductName;
	@Column(name = "reorder_point")
	private int ReorderPoint;
	@Column(name = "reorder_quantity")
	private int ReorderQuantity;
	@Column(name = "add_on_dose")
	private String AddOnDose;
	@Column(name = "add_on_quantity")
	private int AddOnQuantity;
	
	public AutoReorder() {}

	public AutoReorder(String productName, int reorderPoint, int reorderQuantity, String addOnDose, int addOnQuantity) {
		super();
		ProductName = productName;
		ReorderPoint = reorderPoint;
		ReorderQuantity = reorderQuantity;
		AddOnDose = addOnDose;
		AddOnQuantity = addOnQuantity;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public int getReorderPoint() {
		return ReorderPoint;
	}

	public void setReorderPoint(int reorderPoint) {
		ReorderPoint = reorderPoint;
	}

	public int getReorderQuantity() {
		return ReorderQuantity;
	}

	public void setReorderQuantity(int reorderQuantity) {
		ReorderQuantity = reorderQuantity;
	}

	public String getAddOnDose() {
		return AddOnDose;
	}

	public void setAddOnDose(String addOnDose) {
		AddOnDose = addOnDose;
	}

	public int getAddOnQuantity() {
		return AddOnQuantity;
	}

	public void setAddOnQuantity(int addOnQuantity) {
		AddOnQuantity = addOnQuantity;
	}
	
	

}
