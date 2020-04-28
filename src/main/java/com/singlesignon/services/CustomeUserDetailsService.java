package com.singlesignon.services;

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

@Service
public class CustomeUserDetailsService implements UserDetailsService{

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomeUserDetailsService.class);
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserDetails user = userRepository.findByEmailIdOrMobileNo(userName, userName);
		LOGGER.debug("User found with {} : {}", userName, user);
		if(user == null) {
			throw new UsernameNotFoundException("faild to find User using userName");
		}else {
			return user;
		}
	}
	
	public UserDetails findByEmailId(String emailId) {
		UserDetails user = userRepository.findByEmailId(emailId);
		LOGGER.debug("User found with emailId : {} : User : {}", emailId, user);
		return user;
	}
	
	public UserDetails findByMobileNo(String mobileNo) {
		UserDetails user = userRepository.findByMobileNo(mobileNo);
		LOGGER.debug("User found with emailId : {} : User : {}", mobileNo, user);
		return user;
	}
	
	public UserDetails getUserBySeqNo(Long id) {
		Optional<User> opUser = userRepository.findById(id);
		return opUser.isPresent() ? opUser.get() : null;
	}

}
