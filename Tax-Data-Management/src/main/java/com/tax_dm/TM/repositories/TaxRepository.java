package com.tax_dm.TM.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tax_dm.TM.entities.Tax;

public interface TaxRepository extends JpaRepository<Tax, String>{
	
	Optional<Tax> findByTaxId(String taxId);
	
	boolean existsByReceiptNumber(String receiptNumber);
	
	//Optional<Property> findByTaxId(String taxId);
	Optional<Tax> findByPropertyId(String propertyId);
}
