package com.example.epidemicsimulator.domain.simulationcreator.dto;

import lombok.Builder;

@Builder
public record SimulationDto(
        int id,
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
