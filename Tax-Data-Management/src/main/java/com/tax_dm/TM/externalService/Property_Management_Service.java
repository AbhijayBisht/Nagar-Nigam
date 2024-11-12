package com.tax_dm.TM.externalService;

import java.util.List;
import java.util.Set;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.tax_dm.TM.dto.PropertyDto;

@FeignClient(name = "Property-Data-Management", path = "api/property/v1")
public interface Property_Management_Service {

	//GET - getting property type by property_id
	@GetMapping("/propertyTypeID/{propertyId}")
	List<String> getPropertyType(
            @RequestParam(required = false) String propertyId, 
            @RequestParam(required = false) String taxId);
	
	
	//GET - getting record of property based on tax_id
	@GetMapping("/taxes/{taxId}")
	PropertyDto getPropertyByTaxId(@PathVariable String taxId);
}
