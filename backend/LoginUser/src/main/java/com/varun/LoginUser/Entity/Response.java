package com.varun.LoginUser.Entity;

public class Response {
	
	
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Response(String token) {
		super();
		this.token = token;
	}

	public Response() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}