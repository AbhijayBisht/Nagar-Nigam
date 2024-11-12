package com.user_dm.UM.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Property {

	
    private String propertyId;  // Unique Property ID (PIN)  , will be our primary key for this table
    
    private String userId;		// for user_id, whose property it is
    
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
    
	@Transient
    private Tax taxes;	
}
