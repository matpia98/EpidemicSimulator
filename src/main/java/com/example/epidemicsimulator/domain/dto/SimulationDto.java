package com.example.epidemicsimulator.domain.dto;

import lombok.Builder;

@Builder
public record SimulationDto(
        int id,
        int populationSize,
        int initialInfected,
        int infectionRate,
        int mortalityRate,
        int infectionDuration,
        int simulationDuration
) {
}
