package com.singlesignon.model;

import java.io.Serializable;

public class JWTResponse implements Serializable {
	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwtToken;
	private String error;

	public JWTResponse(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public JWTResponse(String jwtToken, String error) {
		this.jwtToken = jwtToken;
		this.error = error;
	}
	
	public String getToken() {
		return this.jwtToken;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
