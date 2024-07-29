package com.example.epidemicsimulator.infrastructure.simulationcreator.controller.dto;

import org.springframework.http.HttpStatus;

public record DeleteSimulationResponseDto(
        String message,
        HttpStatus status
) {
}
