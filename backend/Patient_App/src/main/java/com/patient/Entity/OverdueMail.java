package com.patient.Entity;

public class OverdueMail {
	
	private String[] Mails;
	private String patientName;
	private String Dose;
	private String ClinicName;
	private String NextInjection;
	
	public OverdueMail() {}

	public String[] getMails() {
		return Mails;
	}

	public void setMails(String[] mails) {
		Mails = mails;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getDose() {
		return Dose;
	}

	public void setDose(String dose) {
		Dose = dose;
	}

	public String getClinicName() {
		return ClinicName;
	}

	public void setClinicName(String clinicName) {
		ClinicName = clinicName;
	}

	public String getNextInjection() {
		return NextInjection;
	}

	public void setNextInjection(String nextInjection) {
		NextInjection = nextInjection;
	}

	public OverdueMail(String[] mails, String patientName, String dose, String clinicName, String nextInjection) {
		super();
		this.Mails = mails;
		this.patientName = patientName;
		this.Dose = dose;
		this.ClinicName = clinicName;
		this.NextInjection = nextInjection;
	}
	
	
}
