package com.patient.Entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Clinic {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "location_id")
	private Integer locationId;
	@Column(name = "enterprise_id")
	private Integer enterpriseId;
	@Column(name = "name")
	private String name;
	@Column(name = "loc_type_id")
	private String locTypeId;
	@Column(name = "customer_number")
	private String customerNumber;
	@Column(name = "email")
	private String email;
	@Column(name = "addr_line_1")
	private String addrLine1;
	@Column(name = "addr_line_2")
	private String addrLine2;
	@Column(name = "city")
	private String city;
	@Column(name = "state")
	private String state;
	@Column(name = "state_code")
	private String stateCode;
	@Column(name = "country")
	private String country;
	@Column(name = "fax")
	private String fax;
	@Column(name = "phone")
	private String phone;
	@Column(name = "zipcode")
	private String zipCode;
	@Column(name = "bill_to")
	private String billTo;
	@Column(name = "ship_to")
	private String shipTo;
	@Column(name = "bill_to_name")
	private String billToName;
	@Column(name = "ship_to_name")
	private String shipToName;
	@Column(name = "gln")
	private String gln;
	@Column(name = "account_notes")
	private String accountNotes;
	@Column(name = "account_status")
	private String accountStatus;
	@Column(name = "contract_pricing")
	private String contractPricing;
	@Column(name = "order_po_number")
	private String orderPoNumber;
	@Column(name = "forecast_meu")
	private String forecastMeu;
	@Column(name = "timezone")
	private String timeZone;
	@Column(name = "shipment_method")
	private String shipmentMethod;
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "modified_by")
	private String modifiedBy;
	@Column(name = "created_on")
	private Date createdOn;
	@Column(name = "modified_on")
	private Date modifiedOn;
	@Column(name = "ehr_enabled")
	private String ehrEnabled;
	@Column(name = "edi_enabled")
	private String ediEnabled;
	@Column(name = "beep_enabled")
	private String beepEnabled;
	@Column(name = "active")
	private Boolean active;
	@Column(name = "sales_rep1")
	private String salesRep1;
	@Column(name = "sales_rep2")
	private String salesRep2;
	@Column(name = "division_manager")
	private String divisionManager;
	@Column(name = "regional_manager")
	private String regionalManager;
	@Column(name = "override_rep")
	private String overrideRep;
	@Column(name = "deleted")
	private Boolean deleted;
	@Column(name = "src_id")
	private Integer src_id;
	public Integer getLocationId() {
		return locationId;
	}
	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}
	public Integer getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocTypeId() {
		return locTypeId;
	}
	public void setLocTypeId(String locTypeId) {
		this.locTypeId = locTypeId;
	}
	public String getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddrLine1() {
		return addrLine1;
	}
	public void setAddrLine1(String addrLine1) {
		this.addrLine1 = addrLine1;
	}
	public String getAddrLine2() {
		return addrLine2;
	}
	public void setAddrLine2(String addrLine2) {
		this.addrLine2 = addrLine2;
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getBillTo() {
		return billTo;
	}
	public void setBillTo(String billTo) {
		this.billTo = billTo;
	}
	public String getShipTo() {
		return shipTo;
	}
	public void setShipTo(String shipTo) {
		this.shipTo = shipTo;
	}
	public String getBillToName() {
		return billToName;
	}
	public void setBillToName(String billToName) {
		this.billToName = billToName;
	}
	public String getShipToName() {
		return shipToName;
	}
	public void setShipToName(String shipToName) {
		this.shipToName = shipToName;
	}
	public String getGln() {
		return gln;
	}
	public void setGln(String gln) {
		this.gln = gln;
	}
	public String getAccountNotes() {
		return accountNotes;
	}
	public void setAccountNotes(String accountNotes) {
		this.accountNotes = accountNotes;
	}
	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
	public String getContractPricing() {
		return contractPricing;
	}
	public void setContractPricing(String contractPricing) {
		this.contractPricing = contractPricing;
	}
	public String getOrderPoNumber() {
		return orderPoNumber;
	}
	public void setOrderPoNumber(String orderPoNumber) {
		this.orderPoNumber = orderPoNumber;
	}
	public String getForecastMeu() {
		return forecastMeu;
	}
	public void setForecastMeu(String forecastMeu) {
		this.forecastMeu = forecastMeu;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public String getShipmentMethod() {
		return shipmentMethod;
	}
	public void setShipmentMethod(String shipmentMethod) {
		this.shipmentMethod = shipmentMethod;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
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
	public String getEhrEnabled() {
		return ehrEnabled;
	}
	public void setEhrEnabled(String ehrEnabled) {
		this.ehrEnabled = ehrEnabled;
	}
	public String getEdiEnabled() {
		return ediEnabled;
	}
	public void setEdiEnabled(String ediEnabled) {
		this.ediEnabled = ediEnabled;
	}
	public String getBeepEnabled() {
		return beepEnabled;
	}
	public void setBeepEnabled(String beepEnabled) {
		this.beepEnabled = beepEnabled;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public String getSalesRep1() {
		return salesRep1;
	}
	public void setSalesRep1(String salesRep1) {
		this.salesRep1 = salesRep1;
	}
	public String getSalesRep2() {
		return salesRep2;
	}
	public void setSalesRep2(String salesRep2) {
		this.salesRep2 = salesRep2;
	}
	public String getDivisionManager() {
		return divisionManager;
	}
	public void setDivisionManager(String divisionManager) {
		this.divisionManager = divisionManager;
	}
	public String getRegionalManager() {
		return regionalManager;
	}
	public void setRegionalManager(String regionalManager) {
		this.regionalManager = regionalManager;
	}
	public String getOverrideRep() {
		return overrideRep;
	}
	public void setOverrideRep(String overrideRep) {
		this.overrideRep = overrideRep;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	public Integer getSrc_id() {
		return src_id;
	}
	public void setSrc_id(Integer src_id) {
		this.src_id = src_id;
	}
	public Clinic(Integer locationId, Integer enterpriseId, String name, String locTypeId, String customerNumber,
			String email, String addrLine1, String addrLine2, String city, String state, String stateCode,
			String country, String fax, String phone, String zipCode, String billTo, String shipTo, String billToName,
			String shipToName, String gln, String accountNotes, String accountStatus, String contractPricing,
			String orderPoNumber, String forecastMeu, String timeZone, String shipmentMethod, String createdBy,
			String modifiedBy, Date createdOn, Date modifiedOn, String ehrEnabled, String ediEnabled,
			String beepEnabled, Boolean active, String salesRep1, String salesRep2, String divisionManager,
			String regionalManager, String overrideRep, Boolean deleted, Integer src_id) {
		super();
		this.locationId = locationId;
		this.enterpriseId = enterpriseId;
		this.name = name;
		this.locTypeId = locTypeId;
		this.customerNumber = customerNumber;
		this.email = email;
		this.addrLine1 = addrLine1;
		this.addrLine2 = addrLine2;
		this.city = city;
		this.state = state;
		this.stateCode = stateCode;
		this.country = country;
		this.fax = fax;
		this.phone = phone;
		this.zipCode = zipCode;
		this.billTo = billTo;
		this.shipTo = shipTo;
		this.billToName = billToName;
		this.shipToName = shipToName;
		this.gln = gln;
		this.accountNotes = accountNotes;
		this.accountStatus = accountStatus;
		this.contractPricing = contractPricing;
		this.orderPoNumber = orderPoNumber;
		this.forecastMeu = forecastMeu;
		this.timeZone = timeZone;
		this.shipmentMethod = shipmentMethod;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
		this.ehrEnabled = ehrEnabled;
		this.ediEnabled = ediEnabled;
		this.beepEnabled = beepEnabled;
		this.active = active;
		this.salesRep1 = salesRep1;
		this.salesRep2 = salesRep2;
		this.divisionManager = divisionManager;
		this.regionalManager = regionalManager;
		this.overrideRep = overrideRep;
		this.deleted = deleted;
		this.src_id = src_id;
	}
	@Override
	public String toString() {
		return "Clinic [locationId=" + locationId + ", enterpriseId=" + enterpriseId + ", name=" + name + ", locTypeId="
				+ locTypeId + ", customerNumber=" + customerNumber + ", email=" + email + ", addrLine1=" + addrLine1
				+ ", addrLine2=" + addrLine2 + ", city=" + city + ", state=" + state + ", stateCode=" + stateCode
				+ ", country=" + country + ", fax=" + fax + ", phone=" + phone + ", zipCode=" + zipCode + ", billTo="
				+ billTo + ", shipTo=" + shipTo + ", billToName=" + billToName + ", shipToName=" + shipToName + ", gln="
				+ gln + ", accountNotes=" + accountNotes + ", accountStatus=" + accountStatus + ", contractPricing="
				+ contractPricing + ", orderPoNumber=" + orderPoNumber + ", forecastMeu=" + forecastMeu + ", timeZone="
				+ timeZone + ", shipmentMethod=" + shipmentMethod + ", createdBy=" + createdBy + ", modifiedBy="
				+ modifiedBy + ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn + ", ehrEnabled=" + ehrEnabled
				+ ", ediEnabled=" + ediEnabled + ", beepEnabled=" + beepEnabled + ", active=" + active + ", salesRep1="
				+ salesRep1 + ", salesRep2=" + salesRep2 + ", divisionManager=" + divisionManager + ", regionalManager="
				+ regionalManager + ", overrideRep=" + overrideRep + ", deleted=" + deleted + ", src_id=" + src_id
				+ "]";
	}
	
	public Clinic() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	

}
