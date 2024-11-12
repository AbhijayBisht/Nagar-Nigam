package com.tax_dm.TM.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TaxDto {
	
	@NotEmpty
    private String taxId;  

	@NotEmpty
    private String propertyId;  

	@NotEmpty
    private String userId; 

    private double taxAmount;  

    private double amountPaid;  

    private LocalDate paymentDate;  

    private String paymentMethod; 

    private String receiptNumber;  

    private LocalDate dueDate;  

    private double penalty;  

    private double interest;  

    private double totalDue;

    private boolean isPaid;

    private LocalDate ledgerUpdateDate; 
}
