package com.singlesignon.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.singlesignon.model.JWTRequest;
import com.singlesignon.model.JWTResponse;
import com.singlesignon.model.User;
import com.singlesignon.utility.JWTUtility;

@RestController
public class Authonticate {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Authonticate.class);
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JWTUtility jwtUtil;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(@Valid @RequestBody JWTRequest jwtRequest) throws Exception {
		String token = null;
		Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUserId(), jwtRequest.getPassword()));
		LOGGER.debug("auth >>>> {}" , auth);
		User user = (User)auth.getPrincipal();
		token = jwtUtil.generateToken(user);
		return ResponseEntity.ok(new JWTResponse(token));
	}
	
}
