package com.patient.Entity;

import java.sql.Date;

public class LastInjectionScreen {
	
	private int id;
	private String patientName;
	private String patientLastName;
	private Date patientDOB;
	private Date CreatedOn;
	private String productname;
	private String lastInjection;
	private Boolean flag;
	
	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public LastInjectionScreen() {}

	public String getPatientName() {
		return patientName;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientLastName() {
		return patientLastName;
	}

	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}

	public Date getPatientDOB() {
		return patientDOB;
	}

	public void setPatientDOB(Date patientDOB) {
		this.patientDOB = patientDOB;
	}

	public Date getCreatedOn() {
		return CreatedOn;
	}

	public void setCreatedOn(Date createdOn) {
		CreatedOn = createdOn;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getLastInjection() {
		return lastInjection;
	}

	public void setLastInjection(String lastInjection) {
		this.lastInjection = lastInjection;
	}

	@Override
	public String toString() {
		return "LastInjectionScreen [patientName=" + patientName + ", patientLastName=" + patientLastName
				+ ", patientDOB=" + patientDOB + ", CreatedOn=" + CreatedOn + ", productname=" + productname
				+ ", lastInjection=" + lastInjection + "]";
	}

	public LastInjectionScreen(int id, String patientName, String patientLastName, Date patientDOB, Date createdOn,
			String productname, String lastInjection, Boolean flag) {
		super();
		this.id = id;
		this.patientName = patientName;
		this.patientLastName = patientLastName;
		this.patientDOB = patientDOB;
		this.CreatedOn = createdOn;
		this.productname = productname;
		this.lastInjection = lastInjection;
		this.flag = flag;
	}

	

	

	
	

	
	
}


