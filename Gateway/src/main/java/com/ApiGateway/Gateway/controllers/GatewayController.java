package com.ApiGateway.Gateway.controllers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ApiGateway.Gateway.filter.AuthenticationFilter;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("api/gateway/v1")
@Slf4j
public class GatewayController {

	private Logger logger = LoggerFactory.getLogger(GatewayController.class);
	
//	@Autowired
//	private AuthenticationFilter authenticationFilter;
	
	@GetMapping("/user-info")
    public ResponseEntity<?> getUserInfo(@RequestHeader(value="loggedInUser", required=false) String username,
                              @RequestHeader(value="loggedInUserRoles", required =false) String roles) {
		
//		request = authenticationFilter.apply(null);
		
//		 // If headers aren't found through @RequestHeader, try getting them directly
//        if (username == null) {
//            username = request.getHeaders().getFirst("loggedInUser");
//            log.info("Retrieved username from request headers: {}", username);
//        }
//        
//        if (roles == null) {
//            roles = request.getHeaders().getFirst("loggedInUserRoles");
//            log.info("Retrieved roles from request headers: {}", roles);
//            log.info("Retrieved request: {}", request);
//        }
//
//        if (username == null || roles == null) {
//            log.error("Required headers missing - username: {}, roles: {}", username, roles);
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                .body(Map.of("error", "Authentication information missing"));
//        }
		
		logger.info("User's name: {}",username);
		logger.info("User's roles are: {}",roles);
		
		Map<String, String> response = new HashMap<>();
        response.put("username", username);
        response.put("roles", roles);
		
		return ResponseEntity.ok(response);
    }
}
