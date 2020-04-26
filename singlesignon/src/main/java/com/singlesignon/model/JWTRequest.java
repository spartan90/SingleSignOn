package com.singlesignon.model;

import java.io.Serializable;

public class JWTRequest implements Serializable {
	private static final long serialVersionUID = -8104227884733624339L;
	String userId;
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
