package com.user_dm.UM.externalService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.user_dm.UM.dto.TaxDto;

@FeignClient(name = "TAX-DATA-MANAGEMENT", path = "api/tax/v1")
public interface Tax_Management_Service {

	// list tax record data by tax_id
	@GetMapping("/tax/{taxId}")
	TaxDto getTaxById(@PathVariable String taxId);
	
}
