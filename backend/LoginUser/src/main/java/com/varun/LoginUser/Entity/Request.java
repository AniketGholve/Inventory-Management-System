package com.varun.LoginUser.Entity;

public class Request {
	
	private String username;
	private String password;
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
	public Request(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public Request() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
