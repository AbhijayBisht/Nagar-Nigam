package com.property_dm.PM.services;

import java.util.List;

import com.property_dm.PM.dto.PropertyDto;

public interface PropertyService {

	
	// for saving property data with unique ID generation ( create new property )
	public PropertyDto createProperty(PropertyDto propertyDto);
	
	// list all property data ------------ for admin and super-admin
	public List<PropertyDto> getProperty();
	
	
	// list all property data by property id
	public PropertyDto getPropertyById(String propertyId);
	
	// finding property detail by propertyId, ownerName, contact number OR address
	public List<PropertyDto> findByOwnerNameOrContactNumberOrAddress(
															String ownerName, 
															String contactNumber, 
															String address);
	
	// editing property details for property id
	public PropertyDto editProperty(PropertyDto propertyDto, String propertyId); 

	//deleting property details by property_id
	public void deleteProperty(String propertyId);
	
	// list property data by user_id
	public List<PropertyDto> getPropertyByUserId(String userId);
	
	// list property data by tax_id
	public PropertyDto getPropertyByTaxId(String taxId);
	
	// list property type by property_id or tax_id
	public PropertyDto getPropertyTypeByPropertyNoOrTaxNo(
														String propertyId, 
											            String taxId);
	
	public List<String> getPropertyTypesByPropertyId(String propertyId);
	
}
