package com.patient.Entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class ManualReorder {
	
	@Id
	@Column(name = "product_name")
	private String ProductName;
	@Column(name = "low_inventory_alerts")
	private boolean LowInventoryAlerts;
	@Column(name = "alert_quantity")
	private int AlertQuantity;
	@Column(name = "in_system")
	private boolean inSystem;
	@Column(name = "email")
	private boolean email;
	
	@Transient
	private UsageOverLastMonths usageOverLastMonths;

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public boolean isLowInventoryAlerts() {
		return LowInventoryAlerts;
	}

	public void setLowInventoryAlerts(boolean lowInventoryAlerts) {
		LowInventoryAlerts = lowInventoryAlerts;
	}

	public int getAlertQuantity() {
		return AlertQuantity;
	}

	public void setAlertQuantity(int alertQuantity) {
		AlertQuantity = alertQuantity;
	}

	public boolean isInSystem() {
		return inSystem;
	}

	public void setInSystem(boolean inSystem) {
		this.inSystem = inSystem;
	}

	public boolean isEmail() {
		return email;
	}

	public void setEmail(boolean email) {
		this.email = email;
	}

	public UsageOverLastMonths getUsageOverLastMonths() {
		return usageOverLastMonths;
	}

	public void setUsageOverLastMonths(UsageOverLastMonths usageOverLastMonths) {
		this.usageOverLastMonths = usageOverLastMonths;
	}

	@Override
	public String toString() {
		return "ManualReorder [ProductName=" + ProductName + ", LowInventoryAlerts=" + LowInventoryAlerts
				+ ", AlertQuantity=" + AlertQuantity + ", inSystem=" + inSystem + ", email=" + email
				+ ", usageOverLastMonths=" + usageOverLastMonths + "]";
	}

	public ManualReorder(String productName, boolean lowInventoryAlerts, int alertQuantity, boolean inSystem,
			boolean email, UsageOverLastMonths usageOverLastMonths) {
		super();
		ProductName = productName;
		LowInventoryAlerts = lowInventoryAlerts;
		AlertQuantity = alertQuantity;
		this.inSystem = inSystem;
		this.email = email;
		this.usageOverLastMonths = usageOverLastMonths;
	}

	public ManualReorder() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	
}
