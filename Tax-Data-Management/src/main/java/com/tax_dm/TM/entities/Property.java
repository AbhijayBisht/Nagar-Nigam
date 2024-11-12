package com.tax_dm.TM.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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
@Table(name = "property")
public class Property {

	
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "id", unique = true, nullable = false, 
    //		columnDefinition = "BIGINT DEFAULT nextval('property_id_seq')")
    //private Long id;  // Unique identifier for the record (auto-generated)
    
    @Id
    @Column(unique = true, nullable = false)
    private String propertyId;  // Unique Property ID (PIN)  , will be our primary key for this table
    
    @Column(nullable = false)
    private String userId;
    
    @Column(unique = true, nullable = false)
    private String taxId;		// particular tax_id for that specific property
    
    @Column(nullable = false)
    private String ownerName;  // Owner's Name

    @Column(nullable = false, length = 10,
    		columnDefinition = "VARCHAR(10) CHECK (contact_number ~ '^\\d{10}$')")
    private String contactNumber;  // Contact Number

    @Column(nullable = false)
    private String address;  // Property Address
    
//    @ElementCollection
//	@Enumerated(EnumType.STRING)
    @Column(name = "property_type")
    private String[] propertyType;  // Type of Property (e.g., Residential, Commercial and Agricultural)

    private double area;  // Area of the Property

    private double marketValue;  // Market Value of the Property

    @Column(nullable = false)
    private boolean isDemandNoticeGenerated;  // Flag to indicate if demand notice is generated

    private String baseMapImageUrl;  // URL to the base map image

    private String leaseDetails;  // Lease information if applicable

    private String buildingPermissionDetails;  // Building permission details if applicable

    private String tradeLicenseDetails;  // Trade license details if applicable
    
    @Transient
    private Tax taxes;
	
}
