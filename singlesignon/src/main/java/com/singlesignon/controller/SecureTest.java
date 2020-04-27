package com.singlesignon.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecureTest {

	@RequestMapping("/secure/test")
	public String test() {
		return "test.ok";
	}
	
	@RequestMapping("/test")
	public String test1() {
		return "test1.ok";
	}
}
