package com.soprasteria.agecalculator.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgeResponseDto {
    private long days;
    private long weeks;
    private long months;
    private long years;
    private String age;

}
