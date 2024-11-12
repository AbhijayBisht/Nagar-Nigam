package com.user_dm.UM.externalService;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.user_dm.UM.dto.PropertyDto;

@FeignClient(name = "Property-Data-Management", path = "api/property/v1")
public interface Property_Management_Service {

	
	// list all property data by property id
	@GetMapping("/users/{userId}")
	List<PropertyDto> getPropertyByUserId(@PathVariable String userId);
	
	// for saving property data with unique ID generation ( create new property )
//	@PostMapping("/users")
//	PropertyDto createProperty(@RequestBody PropertyDto propertyDto); 
	
	// editing property details for property id
//	@PutMapping("/users/{propertyId}")
//	PropertyDto editProperty(@RequestBody PropertyDto propertyDto,@PathVariable String propertyId);
	
	//deleting property details by property_id
//	@DeleteMapping("/users/{propertyId}")
//	void deleteProperty(@PathVariable String propertyId);
	
}
