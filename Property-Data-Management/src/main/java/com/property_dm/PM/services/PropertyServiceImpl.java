package com.property_dm.PM.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.property_dm.PM.dto.PropertyDto;
import com.property_dm.PM.entities.Property;
import com.property_dm.PM.exceptions.ResourceNotFoundException;
import com.property_dm.PM.externalService.Tax_Management_Service;
import com.property_dm.PM.repositories.PropertyRepository;
import com.property_dm.PM.dto.TaxDto;


@Service
public class PropertyServiceImpl implements PropertyService {
	
	@Autowired
	private PropertyRepository repository;
	
//	@Autowired
//	private PropertyTypeRepository propertyTypeRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private Tax_Management_Service tax_management_service;
	
//	private Logger logger = LoggerFactory.getLogger(PropertyService.class); 
	
	@Override
	public PropertyDto createProperty(PropertyDto propertyDto) {
		
		Property property = this.propertyDtoToProperty(propertyDto);
		Property saveProperty = this.repository.save(property);
		
		return this.propertyTopropertyDto(saveProperty);
	}


	@Override
	public List<PropertyDto> getProperty() {
		
		List<Property> properties = this.repository.findAll();
		//List<PropertyDto> propertyDtos = properties.stream().map(p -> this.propertyTopropertyDto(p)).collect(Collectors.toList());
		
		List<PropertyDto> propertyDtos = getAllTaxInfo(properties);
		
		return propertyDtos;
	}

	
	@Override
	public PropertyDto getPropertyById(String propertyId) {
		System.out.println("reached here also!!");
		Property property = this.repository.findByPropertyId(propertyId)
											.orElseThrow(
													() -> new ResourceNotFoundException("Property Owner name","property id", propertyId));
		
		PropertyDto dto = getSingleTaxInfo(property);
		
		return dto;
	}
	
	
	@Override
	public List<PropertyDto> findByOwnerNameOrContactNumberOrAddress(String ownerName,
			String contactNumber, String address) {
		
		List<Property> property = this.repository.findByOwnerNameOrContactNumberOrAddress(ownerName, contactNumber, address);
		List<PropertyDto> propertyDtos = property.stream().map(p -> this.propertyTopropertyDto(p)).collect(Collectors.toList());
		
		return propertyDtos;
	}

	@Override
	public PropertyDto editProperty(PropertyDto propertyDto, String propertyId) {
		
		Property property = this.repository.findByPropertyId(propertyId)
										   .orElseThrow(
													() -> new ResourceNotFoundException("Property Owner name","property id", propertyId));
		
		
		property.setAddress(propertyDto.getAddress());
		property.setArea(propertyDto.getArea());
		property.setBaseMapImageUrl(propertyDto.getBaseMapImageUrl());
		property.setBuildingPermissionDetails(propertyDto.getBuildingPermissionDetails());
		property.setContactNumber(propertyDto.getContactNumber());
		property.setDemandNoticeGenerated(propertyDto.isDemandNoticeGenerated());		// for boolean lombok generate is
		property.setLeaseDetails(propertyDto.getLeaseDetails());
		property.setMarketValue(propertyDto.getMarketValue());
		property.setOwnerName(propertyDto.getOwnerName());
		property.setTradeLicenseDetails(propertyDto.getTradeLicenseDetails());
		property.setPropertyType(propertyDto.getPropertyType());
		
		Property updatedProperty = this.repository.save(property);
		PropertyDto dto = this.propertyTopropertyDto(updatedProperty);
		
		return dto;
	}

	@Override
	public void deleteProperty(String propertyId) {
		
		Property property = this.repository.findByPropertyId(propertyId).orElseThrow(
											() -> new ResourceNotFoundException("Property Not found by this ID", " property_id ", propertyId));
		
		this.repository.delete(property);
		System.out.println("Property detail deleted!!!");
		
	}
	

	@Override
	public List<PropertyDto> getPropertyByUserId(String userId) {

		List<Property> property = this.repository.findByUserId(userId)
				.orElseThrow(
						() -> new ResourceNotFoundException("Property does not exist for ","user_id: ", userId));
		
		List<PropertyDto> propertyDtos = property.stream().map(p -> this.propertyTopropertyDto(p)).collect(Collectors.toList());
		
		return propertyDtos;
	}


	@Override
	public PropertyDto getPropertyByTaxId(String taxId) {

		Property property = this.repository.findByTaxId(taxId)
				.orElseThrow(
						() -> new ResourceNotFoundException("Property does not exist for ","tax_id: ", taxId));
		
		return this.propertyTopropertyDto(property);
	}
	

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	
	
	//for converting propertyDto modal to property entity
	public Property propertyDtoToProperty(PropertyDto propertyDto) {
		Property property = this.modelMapper.map(propertyDto, Property.class);
		return property;
	}
	
	//for converting property entity to propertyDto modal
	public PropertyDto propertyTopropertyDto(Property property) {
		PropertyDto propertyDto = this.modelMapper.map(property, PropertyDto.class);
		return propertyDto;
	}

	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	//for getting all info on tax related to property ID
	public List<PropertyDto> getAllTaxInfo(List<Property> properties){
		
			List<PropertyDto> propertyDtos = properties.stream().map(property -> {
						
					PropertyDto propertyDto = this.propertyTopropertyDto(property);
						
							TaxDto taxDto = this.tax_management_service.getTaxByPropertyId(property.getPropertyId());
							
							System.out.println("Info of Tax : " + taxDto);
							propertyDto.setTaxesDto(taxDto);
						
					return propertyDto;
			}).collect(Collectors.toList());
		
		return propertyDtos;
	}
		
	//for getting all info on tax related to property ID
	public PropertyDto getSingleTaxInfo(Property property){
					
			PropertyDto propertyDto = this.propertyTopropertyDto(property);
					
					TaxDto taxDto = this.tax_management_service.getTaxByPropertyId(property.getPropertyId());
						
					System.out.println("Info of Tax : " + taxDto);
					propertyDto.setTaxesDto(taxDto);
					
		return propertyDto;
	}


	// list property type by property_id or tax_id
	//	   Set<PropertyType>
	public PropertyDto getPropertyTypeByPropertyNoOrTaxNo(String propertyId, String taxId) {
		
		Property property = null;
		
		if(propertyId != null) {
			property = this.repository.findByPropertyId(propertyId).orElseThrow(
												() -> new ResourceNotFoundException("Property does not exist for ","property_id: ", propertyId));
		}else if(taxId != null) {
			property = this.repository.findByTaxId(taxId).orElseThrow(
												() -> new ResourceNotFoundException("Property does not exist for ","tax_id: ", taxId));
		}
		
		PropertyDto propertyDto = this.propertyTopropertyDto(property);
		
		if(property == null) {
			throw new RuntimeException("No Property exist for entered property/tax ID!!");
		}
		
		System.out.println(propertyDto);
		//return propertyDto.getPropertyType();
		
		return propertyDto;
	}


	@Override
	public List<String> getPropertyTypesByPropertyId(String propertyId) {
		
		List<String> propertyType = this.repository.findPropertyTypeByPropertyId(propertyId).orElseThrow(
				() -> new ResourceNotFoundException("Property does not exist for ","property_id: ", propertyId));
		
		return propertyType;
	}
	
	
	
	
	
	
	
	
}
