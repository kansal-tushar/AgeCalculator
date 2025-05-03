package com.soprasteria.agecalculator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soprasteria.agecalculator.dto.request.AgeCalculatorRequestDto;
import com.soprasteria.agecalculator.dto.response.AgeResponseDto;
import com.soprasteria.agecalculator.service.AgeService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AgeController.class)
class AgeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
	@MockBean
    private AgeService ageService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCalculateAge_ValidRequest_ReturnsAgeResponse() throws Exception {
        AgeCalculatorRequestDto requestDto = new AgeCalculatorRequestDto("1990-01-01");
        AgeResponseDto responseDto = new AgeResponseDto(12345, 1763, 406, 34, "34 years, 0 months, 0 days");

        Mockito.when(ageService.calculateAge(Mockito.any(AgeCalculatorRequestDto.class))).thenReturn(responseDto);

        mockMvc.perform(post("/age/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.days", is(12345)))
            .andExpect(jsonPath("$.weeks", is(1763)))
            .andExpect(jsonPath("$.months", is(406)))
            .andExpect(jsonPath("$.years", is(34)))
            .andExpect(jsonPath("$.age", is("34 years, 0 months, 0 days")));
    }

    @Test
    void testCalculateAge_BlankBirthDate_ReturnsBadRequest() throws Exception {
        AgeCalculatorRequestDto requestDto = new AgeCalculatorRequestDto(""); // blank birthDate

        mockMvc.perform(post("/age/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
            .andExpect(status().isBadRequest());
    }
}
