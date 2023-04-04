package com.patient.Entity;

import java.sql.Date;

public class LastInjectionScreen {
	 
	private String patientName;
	private String patientLastName;
	private Date patientDOB;
	private Date CreatedOn;
	private String productname;
	private String lastInjection;
	
	public LastInjectionScreen() {}
	

	public LastInjectionScreen(String patientName, String patientLastName, Date patientDOB, Date createdOn,
			String productname, String lastInjection) {
		super();
		this.patientName = patientName;
		this.patientLastName = patientLastName;
		this.patientDOB = patientDOB;
		CreatedOn = createdOn;
		this.productname = productname;
		this.lastInjection = lastInjection;
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


	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
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
	
	
}


