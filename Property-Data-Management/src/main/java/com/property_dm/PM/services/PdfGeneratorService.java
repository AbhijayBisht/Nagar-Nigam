package com.property_dm.PM.services;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.property_dm.PM.dto.TaxDto;
import com.property_dm.PM.entities.Property;
import com.property_dm.PM.exceptions.ResourceNotFoundException;
import com.property_dm.PM.externalService.Tax_Management_Service;
import com.property_dm.PM.repositories.PropertyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class PdfGeneratorService {
	
	@Autowired
	private PropertyRepository repository;
	
	@Autowired
	private Tax_Management_Service tax_management_service;

	
	    public byte[] generatePropertyTaxPdf(String propertyId) throws DocumentException, IOException {
	    	
	    	Property property = this.repository.findByPropertyId(propertyId)
					.orElseThrow(
							() -> new ResourceNotFoundException("Property Owner name","property id", propertyId));
	    	
	    	
	    	TaxDto taxDto = this.tax_management_service.getTaxByPropertyId(propertyId);
	    	
	    	
	    	String propertyOwner = property.getOwnerName();
	    	String propertyAddress = property.getAddress();
	    	String contactNum = property.getContactNumber();
	    	
	    	
	    	String receiptNumber = taxDto.getReceiptNumber(); 
	    	double taxAmount = taxDto.getTaxAmount();
	    	double amountPaid = taxDto.getAmountPaid();
	    	String paymentMethod = taxDto.getPaymentMethod();
	    	LocalDate dueDate = taxDto.getDueDate();
	    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
	    	String formattedString = dueDate.format(formatter);
	    	
	    	
	        Document document = new Document();
	        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	        PdfWriter.getInstance(document, byteArrayOutputStream);

	        document.open();
	        Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
	        Paragraph title = new Paragraph("Property Tax Report", titleFont);
	        title.setAlignment(Element.ALIGN_CENTER);
	        document.add(title);

	        document.add(new Paragraph("Receipt Number: " + receiptNumber));
	        document.add(new Paragraph("Property Owner: " +  propertyOwner));
	        document.add(new Paragraph("Contact Number: " +  contactNum));
	        document.add(new Paragraph("Address: " +  propertyAddress));
	        document.add(new Paragraph(" "));

	        PdfPTable table = new PdfPTable(2);
	        table.setWidthPercentage(100);
	        PdfPCell cell = new PdfPCell(new Phrase("Property Tax Details"));
	        cell.setColspan(2);
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell.setPadding(10);
	        table.addCell(cell);
	        
	        table.addCell("Tax Amount");
	        table.addCell("Rs. " + taxAmount);
	        table.addCell("Amount Paid");
	        table.addCell("Rs. " + amountPaid);
	        table.addCell("Payment Method");
	        table.addCell(paymentMethod);
	        table.addCell("Due Date");
	        table.addCell(formattedString);

	        document.add(table);
	        document.add(new Paragraph("!!Here is your Property-Taxes on Info!!"));

	        document.close();
	        return byteArrayOutputStream.toByteArray();
	    }
}
