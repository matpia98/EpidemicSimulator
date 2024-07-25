package com.example.epidemicsimulator.infrastructure.simulationcreator.controller.dto;

import lombok.Builder;

@Builder
public record CreateSimulationDto(
        int id,
        int populationSize,
        int initialInfected,
        int infectionRate,
        int mortalityRate,
        int infectionDuration,
        int simulationDuration
) {
}
