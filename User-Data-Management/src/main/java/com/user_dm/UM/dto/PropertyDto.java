package com.user_dm.UM.dto;

import java.util.ArrayList;
import java.util.List;

import com.user_dm.UM.entities.Tax;

import jakarta.persistence.Transient;
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

	    private String propertyId;  // Unique Property ID (PIN)  -- we will be using this as primary/foreign key
	    
	    private String userId;		// Foreign key for user_id
	    
	    private String taxId;		// particular tax_id for that specific property
	    
	    private String ownerName;  // Owner's Name

	    private String contactNumber;  // Contact Number

	    private String address;  // Property Address

	    private String propertyType;  // Type of Property (e.g., Residential, Commercial, etc.)

	    private double area;  // Area of the Property

	    private double marketValue;  // Market Value of the Property

	    private boolean isDemandNoticeGenerated;  // Flag to indicate if demand notice is generated

	    private String baseMapImageUrl;  // URL to the base map image

	    private String leaseDetails;  // Lease information if applicable

	    private String buildingPermissionDetails;  // Building permission details if applicable

	    private String tradeLicenseDetails;  // Trade license details if applicable

	    private TaxDto taxesDto;	
	    
}
