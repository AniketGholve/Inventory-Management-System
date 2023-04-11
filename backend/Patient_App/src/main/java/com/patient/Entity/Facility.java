package com.patient.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Facility {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "facility_id")
	private int facilityId;
	@Column(name = "Facility_Name")
	private String facilityName;
	@Column(name = "Address")
	private String Address;
	@Column(name = "City")
	private String city;
	@Column(name = "state")
	private String state;
	@Column(name = "zipcode")
	private int zipcode;
	@Column(name = "enterprise_id")
	private int enterpriseId;
	@Column(name = "location_id")
	private int locationId;
	
	public Facility() {}

	

	public Facility(int facilityId, String facilityName, String address, String city, String state, int zipcode,
			int enterpriseId, int locationId) {
		super();
		this.facilityId = facilityId;
		this.facilityName = facilityName;
		this.Address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.enterpriseId = enterpriseId;
		this.locationId = locationId;
	}



	public int getEnterpriseId() {
		return enterpriseId;
	}



	public void setEnterpriseId(int enterpriseId) {
		this.enterpriseId = enterpriseId;
	}



	public int getLocationId() {
		return locationId;
	}



	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}



	public int getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(int facilityId) {
		this.facilityId = facilityId;
	}

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	
	
}
