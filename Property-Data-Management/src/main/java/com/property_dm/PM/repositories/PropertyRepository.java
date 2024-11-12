package com.property_dm.PM.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.property_dm.PM.entities.Property;

public interface PropertyRepository extends JpaRepository<Property, String> {

	
	Optional<Property> findByPropertyId(String propertyId);
	
	
	List<Property> findByOwnerNameOrContactNumberOrAddress(
			String ownerName, 
			String contactNumber, 
			String address);
	
	
	Optional<List<Property>> findByUserId(String userId);
	
	Optional<Property> findByTaxId(String taxId);
	
	@Query(value = "SELECT property_type " + "FROM property pt WHERE pt.property_id = :propertyId", nativeQuery = true)
	Optional<List<String>> findPropertyTypeByPropertyId(@Param("propertyId") String propertyId);
}
