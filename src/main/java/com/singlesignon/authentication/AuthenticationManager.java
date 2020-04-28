package com.singlesignon.authentication;

import org.springframework.stereotype.Component;

@Component
public class AuthenticationManager {

	public boolean authenticate(String userId, String encryptedPassword) {
		return false;
	}
}
