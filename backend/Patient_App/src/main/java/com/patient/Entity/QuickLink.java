package com.patient.Entity;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "QuickLink")
public class QuickLink {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String url;
	@Column(name = "created_on")
	private Date CreatedOn;
	
	public QuickLink() {}

	public QuickLink(int id, String title, String url, Date createdOn) {
		super();
		this.id = id;
		this.title = title;
		this.url = url;
		this.CreatedOn = createdOn;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCreatedOn() {
		return CreatedOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.CreatedOn = createdOn;
	}

	
	
	
}
