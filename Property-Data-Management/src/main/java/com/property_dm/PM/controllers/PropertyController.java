package com.property_dm.PM.controllers;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.lowagie.text.DocumentException;
import com.property_dm.PM.dto.PropertyDto;
import com.property_dm.PM.payload.ApiResponse;
import com.property_dm.PM.services.PdfGeneratorService;
import com.property_dm.PM.services.PropertyService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/property/v1")
@SecurityRequirement(name= "AuthSecurity")
public class PropertyController {

	@Autowired
	private PropertyService service;
	
	@Autowired
	private PdfGeneratorService pdfGeneratorService;
	
	private Logger logger = LoggerFactory.getLogger(PropertyController.class);
	
	//POST - for creating property detail
	@PostMapping("/")
	public ResponseEntity<PropertyDto> createProperty(@Valid @RequestBody PropertyDto dto){
		
		PropertyDto propertyDto = this.service.createProperty(dto);
		
		return new ResponseEntity<>(propertyDto, HttpStatus.CREATED);
	}
	
	
	//GET - getting property details
	@GetMapping("/property")
	@CircuitBreaker(name = "property_tax_breaker", fallbackMethod = "property_tax_fallback")
	public ResponseEntity<List<PropertyDto>> getAllProperty(){
		
		List<PropertyDto> propertyDtos = this.service.getProperty();
		
		return ResponseEntity.ok(propertyDtos);
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////	
	
//creating Fallback Method for circuit breaker(make sure the method name and return type is same as method)
	public ResponseEntity<List<PropertyDto>> property_tax_fallback(Exception ex){
	
		logger.info(" Fallback is executed, becoz service is down: ", ex.getMessage());
	
		//returning empty list
		List<PropertyDto> fallbackUsers = Collections.emptyList();
		return new ResponseEntity<>(fallbackUsers, HttpStatus.SERVICE_UNAVAILABLE);
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	//GET - getting property details by property id
	//@Hidden						// hides that path in swagger.ui page
	@GetMapping("/property/{propertyId}")
	public ResponseEntity<PropertyDto> getPropertyById(@PathVariable String propertyId){
		System.out.println("reached!!");
		return ResponseEntity.ok(this.service.getPropertyById(propertyId));
		
	}
	
	//GET - getting property details by property id
	@GetMapping("/propertyFind")
	public ResponseEntity<List<PropertyDto>> getPropertyByOwnerNameOrContactNumberOrAddress(
											@RequestParam(required = false) String ownerName,
											@RequestParam(required = false) String contactNumber,
											@RequestParam(required = false) String address){
			
			
		System.out.println("reached!! in propertyfind path");
		return ResponseEntity.ok(this.service.findByOwnerNameOrContactNumberOrAddress(ownerName, contactNumber, address));
			
	}
	
	//PUT - updating the property detail record
	@PutMapping("/property/{propertyId}")
	public ResponseEntity<PropertyDto> updateProperty(@Valid @RequestBody PropertyDto dto, @PathVariable String propertyId){
		
		PropertyDto updatedDto = this.service.editProperty(dto, propertyId);
		
		return ResponseEntity.ok(updatedDto);
	}
	
	
	//DELETE - deleting the property records for that ID
	@DeleteMapping("/property/{propertyId}")
	public ResponseEntity<ApiResponse> deleteProperty(@PathVariable String propertyId){
		
		this.service.deleteProperty(propertyId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Property deleted successfully", true, HttpStatus.OK), HttpStatus.OK);
	}
	
	
	//GET - getting property detail by user_id
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<PropertyDto>> getPropertyByUserId(@PathVariable String userId){
		
		return ResponseEntity.ok(this.service.getPropertyByUserId(userId));
			
	}
	
	//GET - getting property details by tax_id
	@GetMapping("/taxes/{taxId}")
	public ResponseEntity<PropertyDto> getPropertyByTaxId(@PathVariable String taxId){
		
		return ResponseEntity.ok(this.service.getPropertyByTaxId(taxId));
			
	}
	
	//GET - getting property type by property_id or tax_id
	 @GetMapping("/property_type")
	 //			          Set<PropertyType> 
	 public ResponseEntity<PropertyDto> getPropertyType(
	            @RequestParam(required = false) String propertyId, 
	            @RequestParam(required = false) String taxId) {
	        
	        //Set<PropertyType> propertyType = service.getPropertyTypeByPropertyNoOrTaxNo(propertyId, taxId);
		 	PropertyDto propertyDto = service.getPropertyTypeByPropertyNoOrTaxNo(propertyId, taxId);
		 
	        return ResponseEntity.ok(propertyDto);
	 }
	 
	//GET - getting list of property type by property_id
	 @GetMapping("/propertyTypeID/{propertyId}")
	 public ResponseEntity<List<String>> getPropertyTypes(@PathVariable String propertyId) {
		 
//		 PropertyPropertyType type = service.getPropertyTypesByPropertyId(propertyId);
		 List<String> propertyType = service.getPropertyTypesByPropertyId(propertyId);
		 
		 System.out.println(propertyType);	// check this result
		 
	        return ResponseEntity.ok(propertyType);
	 }
	 
	 
	 
	 //GET - getting property-tax details in PDF format
	 @GetMapping("/downloadPropertyTaxReport/{propertyId}")
	 public ResponseEntity<byte[]> downloadPdf(@PathVariable String propertyId)throws DocumentException, IOException {
	        byte[] pdfBytes = pdfGeneratorService.generatePropertyTaxPdf(propertyId);

	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_PDF);
	        headers.setContentDispositionFormData("attachment", "PropertyTaxReport.pdf");

	        return ResponseEntity.ok().headers(headers).body(pdfBytes);
	 }
}
