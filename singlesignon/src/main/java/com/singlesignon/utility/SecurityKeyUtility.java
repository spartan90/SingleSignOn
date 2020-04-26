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

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SecurityKeyUtility {

	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityKeyUtility.class);
	private final String algorithm = "RSA";
	private final int keySize = 4096;
	private KeyPair pair = null;
	
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
	
	public static String convertKeyToBase64String(PublicKey key) {
		byte[] keyAsBytes = key.getEncoded();
		String keyAsString = Base64.getEncoder().encodeToString(keyAsBytes);
		return keyAsString;
	}
	
	public static Key convertBase64StringToKey(String keyAsString) throws NoSuchAlgorithmException, InvalidKeySpecException {
		Key key = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(keyAsString)));
		return key;
	}
}
