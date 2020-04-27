package com.singlesignon.service;

import java.util.List;

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
		List<User> userList = userRepository.findByEmailIdOrMobileNo(userName, userName);
		LOGGER.debug("User found with {} : {}", userName, userList);
		if(userList == null || userList.size() != 1) {
			throw new UsernameNotFoundException("faild to find User using userName");
		}else {
			return userList.get(0);
		}
	}

}
