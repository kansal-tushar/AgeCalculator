package com.soprasteria.agecalculator.service.impl;

import com.soprasteria.agecalculator.dto.request.AgeCalculatorRequestDto;
import com.soprasteria.agecalculator.dto.response.AgeResponseDto;
import com.soprasteria.agecalculator.exception.InvalidDateTimeFormatException;
import com.soprasteria.agecalculator.util.Utility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AgeServiceImplTest {

    @Mock
    private Utility utility;

    @InjectMocks
    private AgeServiceImpl ageService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculateAge_ValidDate_ReturnsCorrectResponse() throws InvalidDateTimeFormatException {
        // Arrange
        String birthDateStr = "1990-01-01";
        LocalDate birthDate = LocalDate.of(1990, 1, 1);
        LocalDate now = LocalDate.now();
        Period period = Period.between(birthDate, now);

        when(utility.parseDate(birthDateStr)).thenReturn(birthDate);

        AgeCalculatorRequestDto request = new AgeCalculatorRequestDto(birthDateStr);

        // Act
        AgeResponseDto response = ageService.calculateAge(request);

        // Assert
        assertNotNull(response);
        assertEquals(ChronoUnit.DAYS.between(birthDate, now), response.getDays());
        assertEquals(ChronoUnit.WEEKS.between(birthDate, now), response.getWeeks());
        assertEquals(ChronoUnit.MONTHS.between(birthDate, now), response.getMonths());
        assertEquals(ChronoUnit.YEARS.between(birthDate, now), response.getYears());
        assertEquals(
            String.format("%d years, %d months, %d days", period.getYears(), period.getMonths(), period.getDays()),
            response.getAge()
        );

        verify(utility, times(1)).parseDate(birthDateStr);
    }

    @Test
    void testCalculateAge_InvalidDate_ThrowsException() throws InvalidDateTimeFormatException {
        // Arrange
        String invalidDate = "invalid-date";
        when(utility.parseDate(invalidDate)).thenThrow(new InvalidDateTimeFormatException("Invalid format"));

        AgeCalculatorRequestDto request = new AgeCalculatorRequestDto(invalidDate);

        // Act & Assert
        InvalidDateTimeFormatException thrown = assertThrows(InvalidDateTimeFormatException.class,
            () -> ageService.calculateAge(request));

        assertEquals("Invalid format", thrown.getMessage());
        verify(utility, times(1)).parseDate(invalidDate);
    }
}
