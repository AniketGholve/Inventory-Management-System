package com.patient.Entity;

import java.sql.Timestamp;

import org.springframework.stereotype.Service;
@Service
public class DisplayClinic {
	
	private String territory;
	private String clinicName;
	private int clinicNumber;
	private String city;
	private int zipcode;
	private int facilityCount;
	private Timestamp lastOrder;
	private Timestamp lastDispense;
	
	public DisplayClinic() {}

	public DisplayClinic(String territory, String clinicName, int clinicNumber, String city, int zipcode,
			int facilityCount, Timestamp lastOrder, Timestamp lastDispense) {
		super();
		this.territory = territory;
		this.clinicName = clinicName;
		this.clinicNumber = clinicNumber;
		this.city = city;
		this.zipcode = zipcode;
		this.facilityCount = facilityCount;
		this.lastOrder = lastOrder;
		this.lastDispense = lastDispense;
	}

	public String getTerritory() {
		return territory;
	}

	public void setTerritory(String territory) {
		this.territory = territory;
	}

	public String getClinicName() {
		return clinicName;
	}

	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}

	public int getClinicNumber() {
		return clinicNumber;
	}

	public void setClinicNumber(int clinicNumber) {
		this.clinicNumber = clinicNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public int getFacilityCount() {
		return facilityCount;
	}

	public void setFacilityCount(int facilityCount) {
		this.facilityCount = facilityCount;
	}

	public Timestamp getLastOrder() {
		return lastOrder;
	}

	public void setLastOrder(Timestamp lastOrder) {
		this.lastOrder = lastOrder;
	}

	public Timestamp getLastDispense() {
		return lastDispense;
	}

	public void setLastDispense(Timestamp lastDispense) {
		this.lastDispense = lastDispense;
	}
	
	
}
