package com.singlesignon.utility;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.singlesignon.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtility {

	private static final Logger LOGGER = LoggerFactory.getLogger(JWTUtility.class);
	private static final String claim_emailid = "emailId";
	private static final String claim_mobileNo = "mobileNo";
	private static final String algorithm = "RSA";
	private static final int keySize = 4096;
	private KeyPair pair = null;
	
	@Value("${security.jwttoken.expiration_in_seconds}")
	private Integer JWT_TOKEN_EXPIRATION_SECONDS;
	
	@PostConstruct
	public void init() throws NoSuchAlgorithmException {
		createNewKeyPair();
	}
 
	public synchronized void createNewKeyPair() throws NoSuchAlgorithmException {
		LOGGER.debug("creating new encription key pairs(private and public)");
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance(algorithm);
		keyGen.initialize(keySize);
		pair = keyGen.generateKeyPair();
		LOGGER.debug("encription key created(private and public)!");
	}
	
	public PrivateKey getPrivateKey() {
		return pair.getPrivate();
	}
	
	public PublicKey getPublicKey() {
		return pair.getPublic();
	}
	
	public String convertKeyToBase64String(PublicKey key) {
		byte[] keyAsBytes = key.getEncoded();
		String keyAsString = Base64.getEncoder().encodeToString(keyAsBytes);
		return keyAsString;
	}
	
	public Key convertBase64StringToKey(String keyAsString) throws NoSuchAlgorithmException, InvalidKeySpecException {
		Key key = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(keyAsString)));
		return key;
	}
	
	public String generateToken(User user) {
		HashMap<String, Object> claims = buildClaimFromUser(user);
		String token = Jwts.builder()
			.setClaims(claims)
			.setSubject(""+user.getUserSeqNo())
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_EXPIRATION_SECONDS * 1000))
			.signWith(SignatureAlgorithm.RS512, getPrivateKey())
			.compact();
		return token;
	}
	
	public HashMap<String, Object> buildClaimFromUser(User user) {
		HashMap<String, Object> claim = new HashMap<String, Object>();
		claim.put(claim_emailid, user.getEmailId());
		claim.put(claim_mobileNo, user.getMobileNo());
		return claim;
	}
	
	public UserDetails buildUserFromClaim(Claims claim) {
		User user = new User();
		user.setUserSeqNo(Long.parseLong(claim.getSubject()));
		user.setEmailId((String)claim.get(claim_emailid));
		user.setMobileNo((String)claim.get(claim_mobileNo));
		return user;
	}
	
	public UserDetails validateAndExtractUser(String token) {
		Claims claim = Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(token).getBody();
		return buildUserFromClaim(claim);
	}
}
