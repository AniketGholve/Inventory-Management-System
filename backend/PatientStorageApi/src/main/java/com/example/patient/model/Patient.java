package com.example.patient.model;

import java.util.Date;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Component
@Entity
public class Patient {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	int id;
	
	@Column(nullable = false)
	String patient_id;	
	
	@Column(nullable = false)
	String patient_first_name;	
	
	@Column(nullable = false)
	String patient_last_name;
	
	String patient_mi;		
	
	@Column(nullable = false)
	Date patient_date_of_birth;
	
	@Column(nullable = false)
	Boolean patient_status;
	
	String email;
	
	@Column(nullable = false)
	Date created_on;	
	
	Date modified_on;	
	int location_id;		
	int last_dispense_loc_id;		
	int enterprise_id;	
	
	@Column(nullable = false)
	String payer_type;
	
	@Column(nullable = false)
	Boolean pa_needed;
	
	@Column(nullable = false)
	Boolean independent_inventory;	
	
	String mail_to;
	
	int src_id;
	
	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Patient(int id, String patient_id, String patient_first_name, String patient_last_name, String patient_mi,
			Date patient_date_of_birth, Boolean patient_status, String email, Date created_on, Date modified_on,
			int location_id, int last_dispense_loc_id, int enterprise_id, String payer_type, Boolean pa_needed,
			Boolean independent_inventory, String mail_to, int src_id) {
		super();
		this.id = id;
		this.patient_id = patient_id;
		this.patient_first_name = patient_first_name;
		this.patient_last_name = patient_last_name;
		this.patient_mi = patient_mi;
		this.patient_date_of_birth = patient_date_of_birth;
		this.patient_status = patient_status;
		this.email = email;
		this.created_on = created_on;
		this.modified_on = modified_on;
		this.location_id = location_id;
		this.last_dispense_loc_id = last_dispense_loc_id;
		this.enterprise_id = enterprise_id;
		this.payer_type = payer_type;
		this.pa_needed = pa_needed;
		this.independent_inventory = independent_inventory;
		this.mail_to = mail_to;
		this.src_id = src_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPatient_id() {
		return patient_id;
	}

	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}

	public String getPatient_first_name() {
		return patient_first_name;
	}

	public void setPatient_first_name(String patient_first_name) {
		this.patient_first_name = patient_first_name;
	}

	public String getPatient_last_name() {
		return patient_last_name;
	}

	public void setPatient_last_name(String patient_last_name) {
		this.patient_last_name = patient_last_name;
	}

	public String getPatient_mi() {
		return patient_mi;
	}

	public void setPatient_mi(String patient_mi) {
		this.patient_mi = patient_mi;
	}

	public Date getPatient_date_of_birth() {
		return patient_date_of_birth;
	}

	public void setPatient_date_of_birth(Date patient_date_of_birth) {
		this.patient_date_of_birth = patient_date_of_birth;
	}

	public Boolean getPatient_status() {
		return patient_status;
	}

	public void setPatient_status(Boolean patient_status) {
		this.patient_status = patient_status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public Date getModified_on() {
		return modified_on;
	}

	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}

	public int getLocation_id() {
		return location_id;
	}

	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}

	public int getLast_dispense_loc_id() {
		return last_dispense_loc_id;
	}

	public void setLast_dispense_loc_id(int last_dispense_loc_id) {
		this.last_dispense_loc_id = last_dispense_loc_id;
	}

	public int getEnterprise_id() {
		return enterprise_id;
	}

	public void setEnterprise_id(int enterprise_id) {
		this.enterprise_id = enterprise_id;
	}

	public String getPayer_type() {
		return payer_type;
	}

	public void setPayer_type(String payer_type) {
		this.payer_type = payer_type;
	}

	public Boolean getPa_needed() {
		return pa_needed;
	}

	public void setPa_needed(Boolean pa_needed) {
		this.pa_needed = pa_needed;
	}

	public Boolean getIndependent_inventory() {
		return independent_inventory;
	}

	public void setIndependent_inventory(Boolean independent_inventory) {
		this.independent_inventory = independent_inventory;
	}

	public String getMail_to() {
		return mail_to;
	}

	public void setMail_to(String mail_to) {
		this.mail_to = mail_to;
	}

	public int getSrc_id() {
		return src_id;
	}

	public void setSrc_id(int src_id) {
		this.src_id = src_id;
	}

	
}
