package com.example.epidemicsimulator.infrastructure.simulationcreator.controller;

import org.springframework.http.HttpStatus;

public record DeleteSimulationResponseDto(
        String message,
        HttpStatus status
) {
}
