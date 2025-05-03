package com.soprasteria.agecalculator.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgeCalculatorRequestDto {
   
	@NotBlank(message="Birth Date should not be blank")
	private String birthDate;
}
