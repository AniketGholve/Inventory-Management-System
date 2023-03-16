package com.patient.Entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Nurse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private int id;
	@Column(name ="first_name")
	private String firstName;
	@Column(name ="middle_name")
	private String middleName;
	@Column(name ="last_name")
	private String lastName;
	@Column(name ="occupation_type")
	private String occupationType;
	@Column(name ="created_on")
	private Date createdOn;
	@Column(name ="modified_on")
	private Date modifiedOn;
	@Column(name ="active")
	private boolean active;
	@Column(name ="enterprise_id")
	private int enterpriseId;
	@Column(name ="location_id")
	private int locationId;
	@Column(name ="src_id")
	private int srcId;
	
	public Nurse() {}
	
	public Nurse(int id, String firstName, String middleName, String lastName, String occupationType,
			Date createdOn, Date modifiedOn, boolean active, int enterpriseId, int locationId, int srcId) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.occupationType = occupationType;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
		this.active = active;
		this.enterpriseId = enterpriseId;
		this.locationId = locationId;
		this.srcId = srcId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOccupationType() {
		return occupationType;
	}

	public void setOccupationType(String occupationType) {
		this.occupationType = occupationType;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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

	public int getSrcId() {
		return srcId;
	}

	public void setSrcId(int srcId) {
		this.srcId = srcId;
	}
	
}
