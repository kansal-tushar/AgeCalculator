package com.soprasteria.agecalculator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soprasteria.agecalculator.dto.request.AgeCalculatorRequestDto;
import com.soprasteria.agecalculator.dto.response.AgeResponseDto;
import com.soprasteria.agecalculator.exception.InvalidDateTimeFormatException;
import com.soprasteria.agecalculator.service.AgeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/age")
public class AgeController {
	
	@Autowired
	private AgeService ageService;
	

	/**
	 * 
	 * Calculates the age by the given person's DOB
	 * @param ageCalculatorRequest request
	 * @return ResponseEntity
	 * @throws InvalidDateTimeFormatException if Date provide is not valid
	 */
	@PostMapping("/calculate")
	public ResponseEntity<?> calculateAge(@Valid @RequestBody AgeCalculatorRequestDto ageCalculatorRequest) throws InvalidDateTimeFormatException {

		AgeResponseDto response = ageService.calculateAge(ageCalculatorRequest);
		return ResponseEntity.ok(response);

	}

}
