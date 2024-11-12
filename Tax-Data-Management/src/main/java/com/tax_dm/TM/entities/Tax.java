package com.tax_dm.TM.entities;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tax")
public class Tax {

	
	@Id
	@Column(unique = true, nullable = false)
    private String taxId;  // Primary key for Tax Ledger entry

    @Column(nullable = false)
    private String propertyId;  // Foreign key from Property service

    @Column(nullable = false)
    private String userId;  // Foreign key from User service

    @Column(nullable = false)
    private double taxAmount;  // Amount of tax demanded

    @Column(nullable = false)
    private double amountPaid;  // Amount paid by the user

    @Column(nullable = false)
    private LocalDate paymentDate;  // Date when the payment was made

    @Column(nullable = false)
    private String paymentMethod;  // Payment method (Net banking, card, UPI, etc.)

    
    private String receiptNumber;  // Auto-generated Random receipt number, cannot be changed

    @Column(nullable = false)
    private LocalDate dueDate;  // Due date for the tax payment

    @Column(nullable = false)
    private double penalty;  // Penalty for late payment(5 to 20 %)

    @Column(nullable = false)
    private double interest;  // Interest on the unpaid tax amount(Interest is 1% per month)

    @Column(nullable = false)
    private double totalDue;  // Total due amount (tax + penalty)

    @Column(nullable = false)
    private boolean isPaid;  // Status of the payment

    @Column(nullable = false)
    private LocalDate ledgerUpdateDate;  // Date when the ledger was last updated
}
