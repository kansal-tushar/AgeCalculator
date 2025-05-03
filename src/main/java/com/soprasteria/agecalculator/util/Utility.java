package com.soprasteria.agecalculator.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.stereotype.Component;

import com.soprasteria.agecalculator.constant.Constants;
import com.soprasteria.agecalculator.exception.InvalidDateTimeFormatException;

@Component
public class Utility {


	/**
	 * 
	 * converts String date to Local Date
	 * @param input input date
	 * @return LocalDate
	 * @throws InvalidDateTimeFormatException if date provided is not in valid format
	 */
	public LocalDate parseDate(String input) throws InvalidDateTimeFormatException {
		for (DateTimeFormatter formatter : Constants.acceptedDateFormats) {
			try {
				return LocalDateTime.parse(input, formatter).toLocalDate();
			} catch (DateTimeParseException ignored) {
				try {
					return LocalDate.parse(input, formatter);
				} catch (DateTimeParseException ignored2) {}
			}
		}
		throw new InvalidDateTimeFormatException("Invalid date format. Accepted formats: "+Constants.acceptedDatePatterns);


	}

}
