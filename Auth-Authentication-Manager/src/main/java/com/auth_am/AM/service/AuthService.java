package com.auth_am.AM.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth_am.AM.entities.Role;
import com.auth_am.AM.entities.UserInfo;
import com.auth_am.AM.repositories.UserInfoRepository;

@Service
public class AuthService {

	@Autowired
	private UserInfoRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	public String saveUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "User added to the system!!!!";
    }
	
	public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
	
    public List<String> getRolesByName(String username) {
        
    	List<String> role = this.repository.findRolesByUsername(username).orElseThrow(() -> new RuntimeException("No Role found for User: "+ username));
        System.out.println("roles: "+role);
		return role;
    }
}
