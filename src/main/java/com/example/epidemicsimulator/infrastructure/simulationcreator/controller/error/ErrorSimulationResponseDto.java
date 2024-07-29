package com.example.epidemicsimulator.infrastructure.simulationcreator.controller.error;

public record ErrorSimulationResponseDto(
        String message,
        int statusCode,
        String timestamp
) {}