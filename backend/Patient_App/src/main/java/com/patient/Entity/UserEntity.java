package com.patient.Entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

////
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
//@Builder
//implements UserDetails
public class UserEntity implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@JsonProperty("id")
	private Integer id;
	//@JsonProperty("username")
	private String username;
	//@JsonProperty("password")
	private String password;
	//@JsonProperty("role")
	private String role;
	//@JsonProperty("firstName")
	private String firstName;
	//@JsonProperty("lastName")
	private String lastName;
	//@JsonProperty("dateofBirth")
	private Date dateofBirth;
	//@JsonProperty("phoneNo")
	private String phoneNo;
//	@Column(name = "enterprise_id")
//	private int enterpriseId;
//	@Column(name = "job_titile")
//	private String jobTitile;
//	@Column(name = "location_id")
//	private int loactionId;
//	@Column(name = "active")
//	private boolean active;
//	@Column(name = "deleted")
//	private boolean deleted;
	
	
//	public int getEnterpriseId() {
//		return enterpriseId;
//	}
//
//	public void setEnterpriseId(int enterpriseId) {
//		this.enterpriseId = enterpriseId;
//	}
//
//	public String getJobTitile() {
//		return jobTitile;
//	}
//
//	public void setJobTitile(String jobTitile) {
//		this.jobTitile = jobTitile;
//	}
//
//	public int getLoactionId() {
//		return loactionId;
//	}
//
//	public void setLoactionId(int loactionId) {
//		this.loactionId = loactionId;
//	}
//
//	public boolean isActive() {
//		return active;
//	}
//
//	public void setActive(boolean active) {
//		this.active = active;
//	}
//
//	public boolean isDeleted() {
//		return deleted;
//	}
//
//	public void setDeleted(boolean deleted) {
//		this.deleted = deleted;
//	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public Date getDateofBirth() {
		return dateofBirth;
	}

	public void setDateofBirth(Date dateofBirth) {
		this.dateofBirth = dateofBirth;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@Override
	public String toString() {
		return "UserEntity [firstName=" + firstName + ", lastName=" + lastName + ", dateofBirth=" + dateofBirth
				+ ", phoneNo=" + phoneNo + ", id=" + id + ", username=" + username + ", password=" + password
				+ ", role=" + role + "]";
	}
	
	
	public UserEntity(Integer id, String username, String password, String role, String firstName, String lastName,
			Date dateofBirth, String phoneNo, int enterpriseId, String jobTitile, int loactionId, boolean active,
			boolean deleted) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateofBirth = dateofBirth;
		this.phoneNo = phoneNo;
//		this.enterpriseId = enterpriseId;
//		this.jobTitile = jobTitile;
//		this.loactionId = loactionId;
//		this.active = active;
//		this.deleted = deleted;
	}

	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		
		// TODO Auto-generated method stub
//		System.out.println("in authpority");
//		System.out.println("role"+this.getRole());
		//System.out.println(this.getAuthorities().isEmpty());
		//System.out.println(this.getAuthorities());
		List<SimpleGrantedAuthority> authorities=new ArrayList<>();
//		System.out.println("authorities");
//		System.out.println(authorities);
		authorities.add(new SimpleGrantedAuthority(this.getRole()));
//		System.out.println("authorities");
//		System.out.println(authorities.toString());
		return authorities;
	}
	

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	
	

}
