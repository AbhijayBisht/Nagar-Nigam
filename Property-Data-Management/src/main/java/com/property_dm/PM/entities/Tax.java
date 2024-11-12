package com.property_dm.PM.entities;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tax {

	
    private String taxId;  // Primary key for Tax Ledger entry

    private String propertyId;  // Foreign key from Property service

    private String userId;  // Foreign key from User service

    private double taxAmount;  // Amount of tax demanded

    private double amountPaid;  // Amount paid by the user

    private LocalDate paymentDate;  // Date when the payment was made

    private String paymentMethod;  // Payment method (Net banking, card, UPI, etc.)
   
    private String receiptNumber;  // Auto-generated Random receipt number, cannot be changed

    private LocalDate dueDate;  // Due date for the tax payment

    private double penalty;  // Penalty for late payment

    private double interest;  // Interest on the unpaid tax amount

    private double totalDue;  // Total due amount (tax + penalty + interest)

    private boolean isPaid;  // Status of the payment

    private LocalDate ledgerUpdateDate;  // Date when the ledger was last updated
}
