package com.property_dm.PM.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
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
public class PropertyDto {

	
	    //@GeneratedValue(strategy = GenerationType.IDENTITY)		-- error becoz if used @GeneratedValue then compulsory to declare @ID with it
	    //@Column(name = "id", unique = true, nullable = false,
	    //		columnDefinition = "BIGINT DEFAULT nextval('property_id_seq')")
	    //private Long id;  // Unique identifier for the record (auto-generated)
	    
	    @Id
	    @Column(unique = true, nullable = false)
	    private String propertyId;  // Unique Property ID (PIN)  -- we will be using this as primary/foreign key
	    
	    private String userId;		// Foreign key for user_id
	    
	    private String taxId;		// particular tax_id for that specific property
	    
	    private String ownerName;  // Owner's Name

	    private String contactNumber;  // Contact Number

	    private String address;  // Property Address

	    private String[] propertyType;  // Type of Property (e.g., Residential, Commercial and Agricultural)

	    private double area;  // Area of the Property

	    private double marketValue;  // Market Value of the Property

	    private boolean isDemandNoticeGenerated;  // Flag to indicate if demand notice is generated

	    private String baseMapImageUrl;  // URL to the base map image

	    private String leaseDetails;  // Lease information if applicable

	    private String buildingPermissionDetails;  // Building permission details if applicable

	    private String tradeLicenseDetails;  // Trade license details if applicable

	    private TaxDto taxesDto;
	    
}
