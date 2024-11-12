package com.user_dm.UM.controllers;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user_dm.UM.dto.UserDto;
import com.user_dm.UM.payload.ApiResponse;
import com.user_dm.UM.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/user/v1")
@SecurityRequirement(name = "AuthSecurity")
public class UserController {

	@Autowired
	private UserService serviceImpl;
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	//POST - for creating user detail
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		
		UserDto dto = this.serviceImpl.createUser(userDto);
		return new ResponseEntity<>(dto,HttpStatus.CREATED);
	}
	
	//GET - for getting user detail
	@GetMapping("/users")
	//@CircuitBreaker(name = "user_property_breaker", fallbackMethod = "user_property_fallback")
	@Retry(name = "user_property_retry",fallbackMethod = "user_property_fallback")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		
		List<UserDto> dtos = this.serviceImpl.getUser();
		return ResponseEntity.ok(dtos);
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	//creating Fallback Method for circuit breaker(make sure the method name and return type is same as method)
	public ResponseEntity<List<UserDto>> user_property_fallback(Exception ex){
		
		logger.info(" Fallback is executed, becoz service is down: ", ex.getMessage());
		
		//returning empty list
		List<UserDto> fallbackUsers = Collections.emptyList();
		return new ResponseEntity<>(fallbackUsers, HttpStatus.SERVICE_UNAVAILABLE);
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//GET - getting user details by Name Or UserId
	@GetMapping("/userFindByNameOrUserId")
	//@CircuitBreaker(name = "user_property_breaker", fallbackMethod = "user_property_ByNameOrUserId_fallback")
	@RateLimiter(name = "user_property_limiter", fallbackMethod = "user_property_ByNameOrUserId_fallback")
	public ResponseEntity<List<UserDto>> getUserByNameOrUserId(
												@RequestParam(required = false) String name,
												@RequestParam(required = false) String userId){
		
		return ResponseEntity.ok(this.serviceImpl.getUserByNameOrUserId(name, userId));
		
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////	
	
//creating Fallback Method for circuit breaker(make sure the method name and return type is same as method)
	public ResponseEntity<List<UserDto>> user_property_ByNameOrUserId_fallback(
												@RequestParam(required = false) String name,
												@RequestParam(required = false) String userId, 
												Exception ex){
	
			logger.info(" Fallback is executed, becoz service is down: ", ex.getMessage());
	
			//returning empty list
			List<UserDto> fallbackUsers = Collections.emptyList();
		return new ResponseEntity<>(fallbackUsers, HttpStatus.SERVICE_UNAVAILABLE);
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	
	//GET - getting user details by AdhaarCard Or PanCard
	@GetMapping("/userFindByAdhaarCardOrPanCard")
	public ResponseEntity<UserDto> getUserByAdhaarCardOrPanCard(
												@RequestParam(required = false) String adhaarCard,
												@RequestParam(required = false) String panCard){
		
		return ResponseEntity.ok(this.serviceImpl.getUserByAdhaarCardOrPanCard(adhaarCard, panCard));
		
	}
	
	//PUT - updating the user detail record
	@PutMapping("/users/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable String userId) {
		
		UserDto dto = this.serviceImpl.editUser(userDto, userId);
		return ResponseEntity.ok(dto);
		
	}
	
	//DELETE - deleting the user records using user_ID
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable String userId){
		
		this.serviceImpl.deleteUser(userId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("User successfully deleted!!", true, HttpStatus.OK), HttpStatus.ACCEPTED);
		
	}
	
		
}
