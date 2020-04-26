package com.singlesignon.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.singlesignon.model.User;
import com.singlesignon.repositories.UserRepository;

import io.jsonwebtoken.Claims;

@Service
public class CustomeUserDetailsService implements UserDetailsService{

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomeUserDetailsService.class);
	private static final String claim_emailid = "emailId";
	private static final String claim_mobileNo = "mobileNo";
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		List<User> userList = userRepository.findByEmailIdOrMobileNo(userName, userName);
		LOGGER.debug("User found with {} : {}", userName, userList);
		if(userList == null || userList.size() != 1) {
			throw new UsernameNotFoundException("faild to find User using userName");
		}else {
			return userList.get(0);
		}
	}
	
	public HashMap<String, Object> buildClaimFromUser(User user) {
		if(user != null) {
			HashMap<String, Object> claim = new HashMap<String, Object>();
			claim.put(claim_emailid, user.getEmailId());
			claim.put(claim_mobileNo, user.getMobileNo());
			return claim;
		}
		return null;
	}
	
	public UserDetails buildUserFromClaim(Claims claim) {
		if(claim != null) {
			User user = new User();
			user.setUserSeqNo(Long.parseLong(claim.getSubject()));
			user.setEmailId((String)claim.get(claim_emailid));
			user.setMobileNo((String)claim.get(claim_mobileNo));
			return user;
		}
		return null;
	}

}
