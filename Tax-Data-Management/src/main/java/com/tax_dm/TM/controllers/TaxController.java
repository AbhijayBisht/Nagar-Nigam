package com.tax_dm.TM.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.tax_dm.TM.dto.TaxDto;
import com.tax_dm.TM.payload.ApiResponse;
import com.tax_dm.TM.services.TaxService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/tax/v1")
@SecurityRequirement(name= "AuthSecurity")
public class TaxController {

	@Autowired
	private TaxService taxServiceImpl;

	
	
	//POST - for creating tax record detail
	@PostMapping("/")
	public ResponseEntity<TaxDto> createTax(@Valid @RequestBody TaxDto taxDto){
		
		TaxDto dto = this.taxServiceImpl.createTax(taxDto);
		return new ResponseEntity<>(dto, HttpStatus.ACCEPTED);
		
	}
	
	
	//GET - getting property details
	@GetMapping("/taxes")
	public ResponseEntity<List<TaxDto>> getAllTaxes(){
		
		List<TaxDto> taxDtos = this.taxServiceImpl.getUser();
		
		return ResponseEntity.ok(taxDtos);
	}
	
	//GET - getting property details by property id
	@Hidden		// hides that path in swagger.ui page
	@GetMapping("/tax/{taxId}")
	public ResponseEntity<TaxDto> getTaxById(@PathVariable String taxId){
		
		System.out.println("reached!!");
		
		return ResponseEntity.ok(this.taxServiceImpl.getTaxById(taxId));
	}
	
	//GET - getting tax record by property ID
	@GetMapping("/property/{propertyId}")
	public ResponseEntity<TaxDto> getTaxByPropertyId(@PathVariable String propertyId){
		
		System.out.println("reached!!");
		
		return ResponseEntity.ok(this.taxServiceImpl.getTaxByPropertyId(propertyId));
	}
	
	//PUT - updating the tax detail record
	@PutMapping("/tax/{taxId}")
	public ResponseEntity<TaxDto> updateTax(@Valid @RequestBody TaxDto taxDto, @PathVariable String taxId){
		
		TaxDto dto = this.taxServiceImpl.editTax(taxDto, taxId);
		
		return ResponseEntity.ok(dto);
	}
	
	//DELETE - deleting the tax records for that tax_ID
	@DeleteMapping("/tax/{taxId}")
	public ResponseEntity<ApiResponse> deleteTax(@PathVariable String taxId){
		
		this.taxServiceImpl.deleteTax(taxId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Tax Record deleted successfully", true, HttpStatus.OK), HttpStatus.OK);
	}
	
	//GET - getting updated tax details with calculated tax record by tax_id
	@GetMapping("/updatedtax/{taxId}")
	public ResponseEntity<TaxDto> getUpdatedTaxById(@PathVariable String taxId){
		
		return ResponseEntity.ok(this.taxServiceImpl.getUpdatedTaxById(taxId));
	}
	
	
	
}
