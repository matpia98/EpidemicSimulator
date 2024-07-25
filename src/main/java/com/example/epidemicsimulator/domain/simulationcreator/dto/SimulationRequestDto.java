package com.example.epidemicsimulator.domain.simulationcreator.dto;

public record SimulationRequestDto(
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
