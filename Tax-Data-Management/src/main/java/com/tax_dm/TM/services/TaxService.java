package com.tax_dm.TM.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.tax_dm.TM.dto.PropertyDto;
import com.tax_dm.TM.dto.TaxDto;

public interface TaxService {

	// for creating new tax records
	public TaxDto createTax(TaxDto taxDto);
	
	// list all tax records data ------------ for admin and super-admin
	public List<TaxDto> getUser();

	// list tax record data by tax_id
	public TaxDto getTaxById(String taxId);
	
	// getting tax record by property ID
	public TaxDto getTaxByPropertyId(String propertyId);
	
	// editing tax record details using tax_id
	public TaxDto editTax(TaxDto taxDto, String taxId);
	
	//deleting tax record by using tax_id
	public void deleteTax(String taxId);
	
	// calculating the tax for particular tax_id
	public double calculateTax(String taxId);
	
	//for calculating penalty based on tax amount, property_type and due date
	public double calculatePenalty(Double taxAmount, String[] propertyType, LocalDate dueDate);
	
	//for calculating interest based on unpaid amount, property_type and due date
    public double calculateInterest(Double unpaidAmount, String[] propertyType, LocalDate dueDate);
    
    //for getting updated tax record
    public TaxDto getUpdatedTaxById(String taxId);
}
