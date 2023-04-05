package com.patient.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class AutoReorder {
	
	@Id
	@Column(name = "product_name")
	private String ProductName;
	@Column(name = "product_id")
	private int productId;
	@Column(name = "reorder_point")
	private int ReorderPoint;
	@Column(name = "reorder_quantity")
	private int ReorderQuantity;
	@Column(name = "add_on_dose")
	private String AddOnDose;
	@Column(name = "add_on_quantity")
	private int AddOnQuantity;
	@Transient
	private UsageOverLastMonths usageOverLastMonths;
	
	public AutoReorder() {}

	
	public AutoReorder(String productName, int productId, int reorderPoint, int reorderQuantity, String addOnDose,
			int addOnQuantity, UsageOverLastMonths usageOverLastMonths) {
		super();
		ProductName = productName;
		this.productId = productId;
		ReorderPoint = reorderPoint;
		ReorderQuantity = reorderQuantity;
		AddOnDose = addOnDose;
		AddOnQuantity = addOnQuantity;
		this.usageOverLastMonths = usageOverLastMonths;
	}


	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	public UsageOverLastMonths getUsageOverLastMonths() {
		return usageOverLastMonths;
	}


	public void setUsageOverLastMonths(UsageOverLastMonths usageOverLastMonths) {
		this.usageOverLastMonths = usageOverLastMonths;
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
