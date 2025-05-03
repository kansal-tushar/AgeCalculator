package com.soprasteria.agecalculator.service;


import com.soprasteria.agecalculator.dto.request.AgeCalculatorRequestDto;
import com.soprasteria.agecalculator.dto.response.AgeResponseDto;
import com.soprasteria.agecalculator.exception.InvalidDateTimeFormatException;

public interface AgeService {
	
    AgeResponseDto calculateAge (AgeCalculatorRequestDto ageCalculatorRequest) throws InvalidDateTimeFormatException;
	
}
