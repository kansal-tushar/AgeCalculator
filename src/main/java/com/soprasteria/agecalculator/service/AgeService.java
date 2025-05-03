package com.soprasteria.agecalculator.service;


import com.soprasteria.agecalculator.dto.request.AgeCalculatorRequestDto;
import com.soprasteria.agecalculator.dto.response.AgeResponseDto;
import com.soprasteria.agecalculator.exception.InvalidDateTimeFormatException;

public interface AgeService {

	/**
	 * 
	 * Calculates the age by given person's DOB
	 * @param ageCalculatorRequest request
	 * @return AgeResponseDto
	 * @throws InvalidDateTimeFormatException if date provided is not in valid format
	 */
	AgeResponseDto calculateAge (AgeCalculatorRequestDto ageCalculatorRequest) throws InvalidDateTimeFormatException;

}
