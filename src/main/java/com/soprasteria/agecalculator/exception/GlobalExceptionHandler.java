package com.soprasteria.agecalculator.exception;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soprasteria.agecalculator.dto.response.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(InvalidDateTimeFormatException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleInvalidDateTimeFormatException(InvalidDateTimeFormatException ex) {
		ErrorResponse error = new ErrorResponse("Invalid input",ex.getMessage(),LocalDateTime.now());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		String errorMessage = ex.getBindingResult().getFieldErrors().stream()
	            .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
	            .collect(Collectors.joining(", "));

	        ErrorResponse error = new ErrorResponse("Validation failed", errorMessage, LocalDateTime.now());
	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}


}
