package com.example.epidemicsimulator.domain.dto;

public record SimulationRequestDto(
        int populationSize,
        int initialInfected,
        int infectionRate,
        int mortalityRate,
        int infectionDuration,
        int simulationDuration
) {
}
