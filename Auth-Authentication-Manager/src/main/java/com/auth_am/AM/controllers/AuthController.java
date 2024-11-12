package com.auth_am.AM.controllers;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth_am.AM.dto.AuthRequest;
import com.auth_am.AM.entities.Role;
import com.auth_am.AM.entities.UserInfo;
import com.auth_am.AM.service.AuthService;
import com.auth_am.AM.service.CustomUserDetailsService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("api/auth/v1")
@SecurityRequirement(name= "AuthSecurity")
public class AuthController {

	@Autowired
	private AuthService service;
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	private Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@PostMapping("/register")
    public String addNewUser(@RequestBody UserInfo user) {
        return service.saveUser(user);
    }
	
	
	@PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest) {
		
		Authentication authenticate = manager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		if (authenticate.isAuthenticated()) {
			return this.service.generateToken(authRequest.getUserName());
		} else {
			throw new RuntimeException("!!Invalid User!!");
		}
        
    }
	
	
	@GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        service.validateToken(token);
        return "Token is valid";
    }
	
	
	@GetMapping("/getRolesByUsername")
    public List<String> getRolesByUsername(@RequestParam("username") String username){
		logger.info("Received request to get roles for username: {}", username);
		System.out.println("Received request to get roles for username: {}"+ username);
		System.out.println("Roles retrieved: "+ service.getRolesByName(username));
		logger.info("Roles retrieved for username {}: {}", username, service.getRolesByName(username));
		return service.getRolesByName(username);
		
	}
	
	//for checking custom user details data
	@GetMapping("/getUserByUsername")
    public UserDetails getUserByUsername(@RequestParam("username") String username){
		logger.info("Received request to get username: ", username);
		System.out.println("Received request to get username: "+ username);
		System.out.println("UserDetails retrieved: "+ customUserDetailsService.loadUserByUsername(username));
		logger.info("Roles retrieved for username {}: {}", username, customUserDetailsService.loadUserByUsername(username));
		return customUserDetailsService.loadUserByUsername(username);
		
	}
}
