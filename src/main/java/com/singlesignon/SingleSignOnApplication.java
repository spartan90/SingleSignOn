package com.singlesignon;

import java.security.NoSuchAlgorithmException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.singlesignon.utility.JWTUtility;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@EnableAdminServer
@SpringBootApplication
public class SingleSignOnApplication {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		SpringApplication.run(SingleSignOnApplication.class, args);
	}

}
