package com.soprasteria.agecalculator.exception;

import com.soprasteria.agecalculator.dto.response.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

    @Test
    void testHandleInvalidDateTimeFormatException_ReturnsBadRequest() {
        String message = "Invalid date format: expected yyyy-MM-dd";
        InvalidDateTimeFormatException ex = new InvalidDateTimeFormatException(message);

        ResponseEntity<ErrorResponse> responseEntity = exceptionHandler.handleInvalidDateTimeFormatException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        ErrorResponse response = responseEntity.getBody();
        assertNotNull(response);
        assertEquals("Invalid input", response.getErrorKey());
        assertEquals(message, response.getMessage());
        assertNotNull(response.getTimestamp());
    }

    @Test
    void testHandleMethodArgumentNotValidException_SingleFieldError() {
        FieldError fieldError = new FieldError("dto", "birthDate", "Birth Date should not be blank");
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getFieldErrors()).thenReturn(Arrays.asList(fieldError));

        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        when(ex.getBindingResult()).thenReturn(bindingResult);

        ResponseEntity<ErrorResponse> responseEntity = exceptionHandler.handleMethodArgumentNotValidException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        ErrorResponse response = responseEntity.getBody();
        assertNotNull(response);
        assertEquals("Validation failed", response.getErrorKey());
        assertEquals("birthDate: Birth Date should not be blank", response.getMessage());
        assertNotNull(response.getTimestamp());
    }

    @Test
    void testHandleMethodArgumentNotValidException_MultipleFieldErrors() {
        FieldError error1 = new FieldError("dto", "birthDate", "must not be blank");
        FieldError error2 = new FieldError("dto", "name", "must not be null");
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getFieldErrors()).thenReturn(Arrays.asList(error1, error2));

        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        when(ex.getBindingResult()).thenReturn(bindingResult);

        ResponseEntity<ErrorResponse> responseEntity = exceptionHandler.handleMethodArgumentNotValidException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        ErrorResponse response = responseEntity.getBody();
        assertNotNull(response);
        assertEquals("Validation failed", response.getErrorKey());
        assertTrue(response.getMessage().contains("birthDate: must not be blank"));
        assertTrue(response.getMessage().contains("name: must not be null"));
        assertNotNull(response.getTimestamp());
    }
}
