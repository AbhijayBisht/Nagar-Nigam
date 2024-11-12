package com.auth_am.AM.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.auth_am.AM.config.CustomUserDetails;
import com.auth_am.AM.entities.UserInfo;
import com.auth_am.AM.repositories.UserInfoRepository;

public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserInfoRepository userInfoRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<UserInfo> info = userInfoRepository.findByName(username);
		logger.info("Repository returned: " + (info != null ? info : "null"));
		return info.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("User not found with name :" + username));
				// here we did the creation of customUserDetails becoz the return type is UserDetails 
				// for this function(which if you check we have implemented in my custumUserDetails class)
	}

}
