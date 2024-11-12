package com.user_dm.UM.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "users")
public class Users {

	@Id
	@Column(unique = true, nullable = false)
	private String userId;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String propertyId;	// single user can have multiple property
	
	@Column(nullable = false, unique = true)
	private String taxId;									// particular tax_id for that specific property, i.e single tax_id for single property
	
	@Column(nullable = false)
	private String name;
	
	private String guardianName;
	
	@Column(nullable = false, unique = true)
	private String adhaarCard;
	
	@Column(nullable = false, unique = true)
	private String panCard;
	
	@DateTimeFormat(pattern = "MM-dd-yy")
	private LocalDate dob;
	
	@Column(nullable = false)
	private Integer age;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = false)
	private String role;
	
	@Transient
    private List<Property> properties =new ArrayList();
	

}
