package com.soprasteria.agecalculator.service.impl;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soprasteria.agecalculator.dto.request.AgeCalculatorRequestDto;
import com.soprasteria.agecalculator.dto.response.AgeResponseDto;
import com.soprasteria.agecalculator.exception.InvalidDateTimeFormatException;
import com.soprasteria.agecalculator.service.AgeService;
import com.soprasteria.agecalculator.util.Utility;


@Service
public class AgeServiceImpl implements AgeService {

	@Autowired
	private Utility utility;


	/**
	 * 
	 * Calculates the age by given person's DOB
	 * @param ageCalculatorRequest request
	 * @return AgeResponseDto
	 * @throws InvalidDateTimeFormatException if date provided is not in valid format
	 */
	@Override
	public AgeResponseDto calculateAge(AgeCalculatorRequestDto ageCalculatorRequest) throws InvalidDateTimeFormatException {
		LocalDate birthDate = utility.parseDate(ageCalculatorRequest.getBirthDate());
		LocalDate now = LocalDate.now();

		Period period = Period.between(birthDate, now);

		AgeResponseDto response = new AgeResponseDto();
		response.setDays(ChronoUnit.DAYS.between(birthDate, now));
		response.setWeeks(ChronoUnit.WEEKS.between(birthDate, now));
		response.setMonths(ChronoUnit.MONTHS.between(birthDate, now));
		response.setYears(ChronoUnit.YEARS.between(birthDate, now));
		response.setAge(String.format("%d years, %d months, %d days", period.getYears(), period.getMonths(), period.getDays()));

		return response;
	}

}	



