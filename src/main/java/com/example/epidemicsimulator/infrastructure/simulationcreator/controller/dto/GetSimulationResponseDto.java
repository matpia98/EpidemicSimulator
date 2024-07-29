package com.example.epidemicsimulator.infrastructure.simulationcreator.controller.dto;

import lombok.Builder;

@Builder
public record GetSimulationResponseDto(
        Long id,
        String simulationName,
        int populationSize,
        int initialInfected,
        double infectionRate,
        double mortalityRate,
        int infectionDuration,
        int deathDuration,
        int simulationDuration
) {
}
