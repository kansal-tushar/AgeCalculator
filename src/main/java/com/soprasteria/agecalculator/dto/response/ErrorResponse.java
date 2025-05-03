package com.soprasteria.agecalculator.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String errorKey;
    private String message;
    private LocalDateTime timestamp;
}
