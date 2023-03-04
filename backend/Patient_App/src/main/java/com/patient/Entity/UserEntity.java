package com.patient.Entity;

import java.sql.Date;
import java.util.ArrayList;

import java.util.Collection;
import java.util.List;
////
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

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
			Date dateofBirth, String phoneNo) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateofBirth = dateofBirth;
		this.phoneNo = phoneNo;
	}
	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		
		// TODO Auto-generated method stub
		System.out.println("in authpority");
		System.out.println("role"+this.getRole());
		//System.out.println(this.getAuthorities().isEmpty());
		//System.out.println(this.getAuthorities());
		List<SimpleGrantedAuthority> authorities=new ArrayList<>();
		System.out.println("authorities");
		System.out.println(authorities);
		authorities.add(new SimpleGrantedAuthority(this.getRole()));
		System.out.println("authorities");
		System.out.println(authorities.toString());
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
