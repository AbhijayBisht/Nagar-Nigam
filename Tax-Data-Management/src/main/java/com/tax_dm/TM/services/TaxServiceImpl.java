package com.tax_dm.TM.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tax_dm.TM.dto.PropertyDto;
import com.tax_dm.TM.dto.TaxDto;
import com.tax_dm.TM.entities.Tax;
import com.tax_dm.TM.exceptions.ResourceNotFoundException;
import com.tax_dm.TM.externalService.Property_Management_Service;
import com.tax_dm.TM.repositories.TaxRepository;

@Service
public class TaxServiceImpl implements TaxService {

	@Autowired
	private TaxRepository taxRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private Property_Management_Service property_Management_Service; 
	
	@Override
	public TaxDto createTax(TaxDto taxDto) {
		
		String receiptNumber;
		boolean exist;
		do {
			receiptNumber = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 12);
			exist = this.taxRepository.existsByReceiptNumber(receiptNumber);
			
		} while (exist);
		
		taxDto.setReceiptNumber(receiptNumber);			// set Receipt NUmber only after checking that it does not exist in database
		
		Tax tax = this.taxDtoToTax(taxDto);
		Tax saveTax = this.taxRepository.save(tax);
		
		return this.taxTotaxDto(saveTax);
	}

	@Override
	public List<TaxDto> getUser() {
		
		List<Tax> tax = this.taxRepository.findAll();
		List<TaxDto> taxDtos = tax.stream().map(p -> this.taxTotaxDto(p)).collect(Collectors.toList());
		
//		List<TaxDto> taxDtos2 = findPropertyTypeAllTaxes(tax);
		
		return taxDtos;
	}
	

	@Override
	public TaxDto getTaxById(String taxId) {
		
		Tax tax = this.taxRepository.findByTaxId(taxId).orElseThrow(
												() -> new ResourceNotFoundException("Tax ID not found with ", "ID: ", taxId));
		TaxDto dto = this.taxTotaxDto(tax);
		
		return dto;
	}

	@Override
	public TaxDto editTax(TaxDto taxDto, String taxId) {
		
		Tax tax = this.taxRepository.findByTaxId(taxId).orElseThrow(
												() -> new ResourceNotFoundException("Tax ID not found with ", "ID: ", taxId));
		
		tax.setAmountPaid(taxDto.getAmountPaid());
		tax.setDueDate(taxDto.getDueDate());
		tax.setInterest(taxDto.getInterest());
		tax.setLedgerUpdateDate(taxDto.getLedgerUpdateDate());
		tax.setPaid(taxDto.isPaid());
		tax.setPaymentDate(taxDto.getPaymentDate());
		tax.setPaymentMethod(taxDto.getPaymentMethod());
		tax.setPenalty(taxDto.getPenalty());
		tax.setTaxAmount(taxDto.getTaxAmount());
		tax.setTotalDue(taxDto.getTotalDue());
		
		Tax updatedTax = this.taxRepository.save(tax);
		TaxDto dto = this.taxTotaxDto(updatedTax);
		
		return dto;
	}

	@Override
	public void deleteTax(String taxId) {
		
		Tax tax = this.taxRepository.findByTaxId(taxId).orElseThrow(
				() -> new ResourceNotFoundException("Tax ID not found with ", "ID: ", taxId));
		
		this.taxRepository.delete(tax);
		
		System.out.println("Tax with tax_id "+taxId+" deleted successfully");

	}

	@Override
	public TaxDto getTaxByPropertyId(String propertyId) {
		
		Tax tax = this.taxRepository.findByPropertyId(propertyId).orElseThrow(
				() -> new ResourceNotFoundException("No tax detail found for Property with ", "ID: ", propertyId));
		
		return this.taxTotaxDto(tax);
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	//for converting taxDto modal to tax entity
	public Tax taxDtoToTax(TaxDto taxDto) {
		Tax tax = this.modelMapper.map(taxDto, Tax.class);
		return tax;
	}
	
	
	//for converting tax entity to taxDto modal
	public TaxDto taxTotaxDto(Tax tax) {
		TaxDto taxDto = this.modelMapper.map(tax, TaxDto.class);
		return taxDto;
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public TaxDto getUpdatedTaxById(String taxId) {
		
		this.calculateTax(taxId);
		System.out.println(this.calculateTax(taxId));
		
		Tax tax = this.taxRepository.findByTaxId(taxId).orElseThrow(
												() -> new ResourceNotFoundException("Tax ID not found with ", "ID: ", taxId));
		TaxDto dto = this.taxTotaxDto(tax);
		
		return dto;
	}
	
	
	
	//for calculating Tax based on tax_id
	public double calculateTax(String taxId){
		
		PropertyDto propertyDto = this.property_Management_Service.getPropertyByTaxId(taxId);

		Tax tax = this.taxRepository.findByTaxId(taxId).orElseThrow(
				() -> new ResourceNotFoundException("Tax ID not found with ", "ID: ", taxId));
		
		
		double area = propertyDto.getArea();		// getting area in double becoz it store as double in property table
		String[] propertyType = propertyDto.getPropertyType();		//storing the property in set of propertyType
		LocalDate dueDate = tax.getDueDate();
		double amountPaid = tax.getAmountPaid();
		
		double taxAmount = 0.0;
		
//		if (propertyType == null || propertyType.isEmpty()) {
//	        throw new IllegalStateException("Property type is missing or invalid for tax calculation.");
//	    }
		
		
		if ( Arrays.asList(propertyType).contains("RESIDENTIAL") ) {
			System.out.println(propertyType);
			taxAmount = taxAmount + (area * 10);  // Add residential tax
			System.out.println(taxAmount);
		}
		if ( Arrays.asList(propertyType).contains("COMMERCIAL") ) {
			taxAmount += area * 20;  // Add commercial tax
		}
		if ( Arrays.asList(propertyType).contains("AGRICULTURAL") ) {
			taxAmount += area * 5;   // Add commercial tax
		}
		
		Double penalty = calculatePenalty(taxAmount, propertyType, dueDate);
		
		double totalDue = taxAmount + penalty;
		tax.setTaxAmount(taxAmount);
		tax.setTotalDue(totalDue);
		
		BigDecimal bd = BigDecimal.valueOf(penalty).setScale(2, RoundingMode.HALF_UP);
		penalty = bd.doubleValue();
		
		System.out.println("penalty: " + penalty);
		tax.setPenalty(penalty);
		
		double unpaidAmount = totalDue - amountPaid;
        Double interest = calculateInterest(unpaidAmount, propertyType, dueDate);
        tax.setInterest(interest);
		
        
        this.taxRepository.save(tax);
        
        //return taxAmount;
        //return propertyType;
        return area;
        
	}
	
	//for calculating penalty based on tax amount, property_type and due date
	public double calculatePenalty(Double taxAmount, String[] propertyType, LocalDate dueDate) {
		
		Double penaltyRate = 0.0;
	    long monthsLate = ChronoUnit.MONTHS.between(dueDate, LocalDate.now());

	    // Apply penalty rates based on property type
	    if ( Arrays.asList(propertyType).contains("RESIDENTIAL") ) {
	        penaltyRate += 0.05; // 5% for residential
	    }
	    if ( Arrays.asList(propertyType).contains("COMMERCIAL") ) {
	        penaltyRate += 0.10; // 10% for commercial
	    }
	    if ( Arrays.asList(propertyType).contains("AGRICULTURAL") ) {
	        penaltyRate += 0.03; // 3% for commercial
	    }
	    
	    
	    monthsLate = Math.abs(monthsLate);				// for making sure the months is nor - or +
	    System.out.println("monthsLate: " + monthsLate);
	    // Ensure penalty rate is capped at 20%
	    penaltyRate = Math.min(penaltyRate * monthsLate, 0.20);  // Capping the total penalty rate at 20% per month

	    // Calculate and return the penalty
	    return taxAmount * penaltyRate;
    }
	
	
	//for calculating interest based on unpaid amount, property_type and due date
    public double calculateInterest(Double unpaidAmount, String[] propertyType, LocalDate dueDate) {

    	Double interestRate = 0.01;  // 1% base interest rate per month
        long monthsLate = ChronoUnit.MONTHS.between(dueDate, LocalDate.now());

        // Apply different interest rates based on property type if needed (all set to 1% for now)
        if ( Arrays.asList(propertyType).contains("RESIDENTIAL") ) {
            interestRate = 0.01;  // 1% interest for residential
        }
        if ( Arrays.asList(propertyType).contains("COMMERCIAL") ) {
            interestRate = 0.01;  // 1% interest for commercial (you can change these rates)
        }

        // Calculate total interest: interest rate * monthsLate * unpaidAmount
        Double interest = unpaidAmount * interestRate * monthsLate;

        return interest;
    }
	
	
	
}
