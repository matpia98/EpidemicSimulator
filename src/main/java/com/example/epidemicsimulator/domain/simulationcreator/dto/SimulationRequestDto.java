package com.example.epidemicsimulator.domain.simulationcreator.dto;

public record SimulationRequestDto(
        int populationSize,
        int initialInfected,
        int infectionRate,
        int mortalityRate,
        int infectionDuration,
        int simulationDuration
) {
}
