package com.user_dm.UM.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.user_dm.UM.payload.ApiResponse;


@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException ex){
		String message = ex.getMessage();
		ApiResponse apiResponse = ApiResponse.builder().message(message).success(true).httpStatus(HttpStatus.NOT_FOUND).build();
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
		Map<String, String> errorResp = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(err -> {
			String fieldName = ((FieldError)err).getField();
			String message = err.getDefaultMessage();
			errorResp.put(fieldName, message);
			
		});
		return new ResponseEntity<Map<String,String>>(errorResp, HttpStatus.BAD_REQUEST);
		
	}
}
