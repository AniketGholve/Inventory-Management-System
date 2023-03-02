package com.patient.Entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Users {
	
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Integer userId;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName; 
	@Column(name="middle_name")
	private String middleName; 
	@Column(name="user_login")
	private String userLogin; 
	@Column(name="email")
	private String email; 
	@Column(name="enterprise_id")
	private Integer enterpriseId;
	@Column(name="default_location_id")
	private Integer defaultLocationId; 
	@Column(name="job_title")
	private String jobTitle; 
	@Column(name="active")
	private Boolean active; 
	@Column(name="deleted")
	private Boolean deleted; 
	@Column(name="work_phone")
	private String workPhone; 
	@Column(name="mobile_phone")
	private String mobilePhone; 
	@Column(name="prompt_for_location")
	private String promptForLocation; 
	@Column(name="allow_override")
	private Boolean allowOverride; 
	@Column(name="created_on")
	private Date createdOn; 
	@Column(name="modified_on")
	private Date modifiedOn; 
	@Column(name="external_id")
	private String externalId; 
	@Column(name="password")
	private String password; 
	@Column(name="password_updated_date")
	private Date passwordUpdatedDate; 
	@Column(name="token")
	private String token; 
	@Column(name="token_expiry_time")
	private String tokenExpiryTime;
	@Column(name="role")
	private String role;
	@Column(name="last_login")
	private Date lastLogin; 
	@Column(name="src_id")
	private Integer srcId;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getUserLogin() {
		return userLogin;
	}
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public Integer getDefaultLocationId() {
		return defaultLocationId;
	}
	public void setDefaultLocationId(Integer defaultLocationId) {
		this.defaultLocationId = defaultLocationId;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
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
	public String getWorkPhone() {
		return workPhone;
	}
	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getPromptForLocation() {
		return promptForLocation;
	}
	public void setPromptForLocation(String promptForLocation) {
		this.promptForLocation = promptForLocation;
	}
	public Boolean getAllowOverride() {
		return allowOverride;
	}
	public void setAllowOverride(Boolean allowOverride) {
		this.allowOverride = allowOverride;
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
	public String getExternalId() {
		return externalId;
	}
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getPasswordUpdatedDate() {
		return passwordUpdatedDate;
	}
	public void setPasswordUpdatedDate(Date passwordUpdatedDate) {
		this.passwordUpdatedDate = passwordUpdatedDate;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getTokenExpiryTime() {
		return tokenExpiryTime;
	}
	public void setTokenExpiryTime(String tokenExpiryTime) {
		this.tokenExpiryTime = tokenExpiryTime;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public Integer getSrcId() {
		return srcId;
	}
	public void setSrcId(Integer srcId) {
		this.srcId = srcId;
	}
	public Users(Integer userId, String firstName, String lastName, String middleName, String userLogin, String email,
			Integer enterpriseId, Integer defaultLocationId, String jobTitle, Boolean active, Boolean deleted,
			String workPhone, String mobilePhone, String promptForLocation, Boolean allowOverride, Date createdOn,
			Date modifiedOn, String externalId, String password, Date passwordUpdatedDate, String token,
			String tokenExpiryTime, String role, Date lastLogin, Integer srcId) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.userLogin = userLogin;
		this.email = email;
		this.enterpriseId = enterpriseId;
		this.defaultLocationId = defaultLocationId;
		this.jobTitle = jobTitle;
		this.active = active;
		this.deleted = deleted;
		this.workPhone = workPhone;
		this.mobilePhone = mobilePhone;
		this.promptForLocation = promptForLocation;
		this.allowOverride = allowOverride;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
		this.externalId = externalId;
		this.password = password;
		this.passwordUpdatedDate = passwordUpdatedDate;
		this.token = token;
		this.tokenExpiryTime = tokenExpiryTime;
		this.role = role;
		this.lastLogin = lastLogin;
		this.srcId = srcId;
	}
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Users [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", middleName="
				+ middleName + ", userLogin=" + userLogin + ", email=" + email + ", enterpriseId=" + enterpriseId
				+ ", defaultLocationId=" + defaultLocationId + ", jobTitle=" + jobTitle + ", active=" + active
				+ ", deleted=" + deleted + ", workPhone=" + workPhone + ", mobilePhone=" + mobilePhone
				+ ", promptForLocation=" + promptForLocation + ", allowOverride=" + allowOverride + ", createdOn="
				+ createdOn + ", modifiedOn=" + modifiedOn + ", externalId=" + externalId + ", password=" + password
				+ ", passwordUpdatedDate=" + passwordUpdatedDate + ", token=" + token + ", tokenExpiryTime="
				+ tokenExpiryTime + ", role=" + role + ", lastLogin=" + lastLogin + ", srcId=" + srcId + "]";
	}
	
	
	
	
	
}
