package com.patient.Entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class Enterprises {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="enterprise_id")
	private int enterpriseId;
	@Column(name="name")
	private String name;
	@Column(name="addr_link_1")
	private String addrLink1;
	@Column(name="addr_link_2")
	private String addrLink2;
	@Column(name="city")
	private String city;
	@Column(name="state")
	private String state;
	@Column(name="state_code")
	private String stateCode;
	@Column(name="zipcode")
	private String zipcode;
	@Column(name="phone")
	private String phone;
	@Column(name="email")
	private String email;
	@Column(name="is_corporate")
	private Boolean isCorporate;
	@Column(name="active")
	private Boolean active;
	@Column(name="deleted")
	private Boolean deleted;
	@Column(name="country")
	private String country;
	@Column(name="ext_enterprise_id")
	private String extEnterpriseId;
	@Column(name="modified_by")
	private String modifiedBy;
	@Column(name="created_by")
	private String createdBy;
	@Column(name="created_on")
	private Date createdOn;
	@Column(name="modified_on")
	private Date modifiedOn;
	@Column(name="shared_patients")
	private Boolean sharedPatients;
	@Column(name="scheduled_reports")
	private Boolean scheduledReports;
	@Column(name="show_jit")
	private String showJit;
	@Column(name="gln")
	private String gln;
	@Column(name="ehr_implementation_status")
	private Boolean ehrImplementationStatus;
	@Transient
	private int facilityCount;
	@Transient
	private int users;
	
	
	public Enterprises() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public Enterprises(int enterpriseId, String name, String addrLink1, String addrLink2, String city, String state,
			String stateCode, String zipcode, String phone, String email, Boolean isCorporate, Boolean active,
			Boolean deleted, String country, String extEnterpriseId, String modifiedBy, String createdBy,
			Date createdOn, Date modifiedOn, Boolean sharedPatients, Boolean scheduledReports, String showJit,
			String gln, Boolean ehrImplementationStatus, int facilityCount, int users) {
		super();
		this.enterpriseId = enterpriseId;
		this.name = name;
		this.addrLink1 = addrLink1;
		this.addrLink2 = addrLink2;
		this.city = city;
		this.state = state;
		this.stateCode = stateCode;
		this.zipcode = zipcode;
		this.phone = phone;
		this.email = email;
		this.isCorporate = isCorporate;
		this.active = active;
		this.deleted = deleted;
		this.country = country;
		this.extEnterpriseId = extEnterpriseId;
		this.modifiedBy = modifiedBy;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
		this.sharedPatients = sharedPatients;
		this.scheduledReports = scheduledReports;
		this.showJit = showJit;
		this.gln = gln;
		this.ehrImplementationStatus = ehrImplementationStatus;
		this.facilityCount = facilityCount;
		this.users = users;
	}




	public int getFacilityCount() {
		return facilityCount;
	}
	public void setFacilityCount(int facilityCount) {
		this.facilityCount = facilityCount;
	}
	public int getUsers() {
		return users;
	}
	public void setUsers(int users) {
		this.users = users;
	}
	public int getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(int enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddrLink1() {
		return addrLink1;
	}
	public void setAddrLink1(String addrLink1) {
		this.addrLink1 = addrLink1;
	}
	public String getAddrLink2() {
		return addrLink2;
	}
	public void setAddrLink2(String addrLink2) {
		this.addrLink2 = addrLink2;
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
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getIsCorporate() {
		return isCorporate;
	}
	public void setIsCorporate(Boolean isCorporate) {
		this.isCorporate = isCorporate;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getExtEnterpriseId() {
		return extEnterpriseId;
	}
	public void setExtEnterpriseId(String extEnterpriseId) {
		this.extEnterpriseId = extEnterpriseId;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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
	public Boolean getSharedPatients() {
		return sharedPatients;
	}
	public void setSharedPatients(Boolean sharedPatients) {
		this.sharedPatients = sharedPatients;
	}
	public Boolean getScheduledReports() {
		return scheduledReports;
	}
	public void setScheduledReports(Boolean scheduledReports) {
		this.scheduledReports = scheduledReports;
	}
	public String getShowJit() {
		return showJit;
	}
	public void setShowJit(String showJit) {
		this.showJit = showJit;
	}
	public String getGln() {
		return gln;
	}
	public void setGln(String gln) {
		this.gln = gln;
	}
	public Boolean getEhrImplementationStatus() {
		return ehrImplementationStatus;
	}
	public void setEhrImplementationStatus(Boolean ehrImplementationStatus) {
		this.ehrImplementationStatus = ehrImplementationStatus;
	}
}
