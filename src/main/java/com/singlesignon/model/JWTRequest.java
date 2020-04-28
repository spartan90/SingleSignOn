package com.singlesignon.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class JWTRequest implements Serializable {
	private static final long serialVersionUID = -8104227884733624339L;
	
	@NotBlank(message = "User Id is mandatory")
	String userId;
	
	@NotBlank(message = "Password is mandatory")
	String password;
	
	public JWTRequest() {
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
