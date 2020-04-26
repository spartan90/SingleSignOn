package com.singlesignon.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.singlesignon.model.User;

@Service
public interface UserRepository extends CrudRepository<User, Long> {
	List<User> findByUserName(String userName);
	List<User> findByEmailIdOrMobileNo(String emailId, String mobileNo);
}
