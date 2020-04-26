package com.singlesignon.controller;

import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.singlesignon.model.JWTRequest;
import com.singlesignon.model.JWTResponse;
import com.singlesignon.utility.SecurityKeyUtility;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class Authonticate {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Authonticate.class);
	
	@Autowired
	private SecurityKeyUtility keyUtility;
	
	@Value("${security.jwttoken.expiration_in_seconds}")
	Integer JWT_TOKEN_EXPIRATION_SECONDS;
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticate(@RequestBody JWTRequest jwtRequest) throws Exception {
		String token = null;
		String error = null;
		LOGGER.debug("JWT_TOKEN_EXPIRATION_SECONDS : {}", JWT_TOKEN_EXPIRATION_SECONDS);
		LOGGER.debug("keyUtility : {}", keyUtility);
		if(jwtRequest != null) {
			if(jwtRequest.getUserId().equals("sarang") && jwtRequest.getPassword().equals("ok")) {
				HashMap<String, Object> claims = new HashMap<String, Object>();
				token = Jwts.builder().setClaims(claims).setSubject("1").setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_EXPIRATION_SECONDS * 1000))
				.signWith(SignatureAlgorithm.RS512, keyUtility.getPrivateKey()).compact();
			}
		}else {
			error = "Invalid Credentials";
		}
		String newPS = SecurityKeyUtility.convertKeyToBase64String(keyUtility.getPublicKey());
		
		LOGGER.debug("Claims : {}", Jwts.parser().setSigningKey(SecurityKeyUtility.convertBase64StringToKey(newPS)).parseClaimsJws(token).getBody());
		return ResponseEntity.ok(new JWTResponse(token, error));
	}
	
}
