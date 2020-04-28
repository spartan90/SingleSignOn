package com.singlesignon.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecureTest {

	@GetMapping("/secure/test")
	public String test() {
		return "test.ok";
	}
	
	@GetMapping("/test")
	public String test1() {
		return "test1.ok";
	}
}
