package com.soprasteria.agecalculator.util;

import com.soprasteria.agecalculator.exception.InvalidDateTimeFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UtilityTest {

    private Utility utility;

    @BeforeEach
    void setUp() {
        utility = new Utility();
    }

    @Test
    void testParseDate_LocalDateTimePattern_Success_ddMMyyyyHHmm() throws InvalidDateTimeFormatException {
        String input = "03-05-2024 14:30"; // Matches "dd-MM-yyyy HH:mm"
        LocalDate expected = LocalDate.of(2024, 5, 3);

        LocalDate result = utility.parseDate(input);
        assertEquals(expected, result);
    }

    @Test
    void testParseDate_LocalDateTimePattern_Success_ddMMyyyyhhmma() throws InvalidDateTimeFormatException {
        String input = "03-05-2024 02:30 PM"; // Matches "dd-MM-yyyy hh:mm a"
        LocalDate expected = LocalDate.of(2024, 5, 3);

        LocalDate result = utility.parseDate(input);
        assertEquals(expected, result);
    }

    @Test
    void testParseDate_LocalDatePattern_Success() throws InvalidDateTimeFormatException {
        String input = "03-05-2024"; // Matches "dd-MM-yyyy", goes to LocalDate.parse
        LocalDate expected = LocalDate.of(2024, 5, 3);

        LocalDate result = utility.parseDate(input);
        assertEquals(expected, result);
    }

    @Test
    void testParseDate_InvalidFormat_ThrowsException() {
        String input = "2024/05/03 14:30";

        InvalidDateTimeFormatException ex = assertThrows(
            InvalidDateTimeFormatException.class,
            () -> utility.parseDate(input)
        );

        assertTrue(ex.getMessage().contains("Invalid date format"));
    }
}
