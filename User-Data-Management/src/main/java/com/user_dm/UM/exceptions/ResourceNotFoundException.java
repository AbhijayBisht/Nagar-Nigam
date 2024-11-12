package com.user_dm.UM.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String resourceName;
	String fieldName;
	String fieldValue;
	
	public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
		super();
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	
}
