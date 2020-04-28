package com.singlesignon.controllers.secure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.singlesignon.services.CustomeUserDetailsService;

@RestController
public class UserController {
	
	@Autowired
	CustomeUserDetailsService userService;
	
	@GetMapping("/secure/getuser/{bytype}/{id}")
	public UserDetails getUser(@PathVariable("bytype") String byType, @PathVariable("id") String id){
		if(byType.equalsIgnoreCase("seqno")) {
			return userService.getUserBySeqNo(Long.parseLong(id));
		}else if(byType.equalsIgnoreCase("emailid")) {
			return userService.findByEmailId(id);
		}else if(byType.equalsIgnoreCase("mobileno")) {
			return userService.findByMobileNo(id);
		}
		return null;
	}
}
