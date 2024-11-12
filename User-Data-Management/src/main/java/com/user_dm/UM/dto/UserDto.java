package com.user_dm.UM.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class UserDto {

	@NotEmpty
	private String userId;
	
	@NotEmpty(message = "Required feild cannot be blank")
	@Size(min=6, max=15, message = "Minimum 6 and Maximum 15 character should be present")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\\\S+$).{6,15}$")
	private String password;
	
	@NotEmpty
	private String propertyId;
	
	@NotEmpty
	private String taxId;
	
	@Size(max=25, message = "Maximun size 25 allowed")
	@NotEmpty(message = "Required feild cannot be blank")
	private String name;
	
	private String guardianName;
	
	@NotEmpty(message = "Required feild cannot be blank")
	@Size(max = 12, min = 12)
	private String adhaarCard;
	
	@NotEmpty(message = "Required feild cannot be blank")
	private String panCard;
	
	
	private LocalDate dob;
	
	@NotEmpty(message = "Required feild cannot be blank")
	private Integer age;
	
	@NotEmpty(message = "Required feild cannot be blank")
	private String address;
	
	@NotEmpty
	private String role;
	
	private List<PropertyDto> propertiesDto = new ArrayList();

}
