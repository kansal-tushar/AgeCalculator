package com.soprasteria.agecalculator.constant;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class Constants {

	public static final List<String> acceptedDatePatterns = List.of(
			"dd-MM-yyyy HH:mm",
			"dd-MM-yyyy H:mm",
			"dd-MM-yyyy h:mm a",
			"dd-MM-yyyy hh:mm a",
			"dd-MM-yyyy"
			);

	// ✅ Build DateTimeFormatters from pattern strings
	public static final List<DateTimeFormatter> acceptedDateFormats = acceptedDatePatterns.stream()
			.map(pattern -> {
				if (pattern.contains("a")) {
					return DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH);
				} else {
					return DateTimeFormatter.ofPattern(pattern);
				}
			})
			.toList();
}
