package com.singlesignon.controller;

import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.singlesignon.service.CustomeUserDetailsService;
import com.singlesignon.utility.SecurityKeyUtility;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class Authonticate {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Authonticate.class);
	
	@Autowired
	private SecurityKeyUtility keyUtility;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Value("${security.jwttoken.expiration_in_seconds}")
	Integer JWT_TOKEN_EXPIRATION_SECONDS;
	
	@Autowired
	private CustomeUserDetailsService userDetailService;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody JWTRequest jwtRequest) throws Exception {
		String token = null;
		Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUserId(), jwtRequest.getPassword()));
		LOGGER.debug("auth >>>> {}" , auth);
		User user = (User)auth.getPrincipal();
		HashMap<String, Object> claims = userDetailService.buildClaimFromUser(user);
		token = Jwts.builder()
				.setClaims(claims)
				.setSubject(""+user.getUserSeqNo())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_EXPIRATION_SECONDS * 1000))
				.signWith(SignatureAlgorithm.RS512, keyUtility.getPrivateKey())
				.compact();
		
		return ResponseEntity.ok(new JWTResponse(token));
	}
	
}
