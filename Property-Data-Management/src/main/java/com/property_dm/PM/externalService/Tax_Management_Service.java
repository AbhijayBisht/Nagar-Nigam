package com.property_dm.PM.externalService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.property_dm.PM.dto.TaxDto;

@FeignClient(name = "TAX-DATA-MANAGEMENT", path = "api/tax/v1")
public interface Tax_Management_Service {

	// get detail of tax data by property id
	@GetMapping("/property/{propertyId}")
	TaxDto getTaxByPropertyId(@PathVariable String propertyId);

}
