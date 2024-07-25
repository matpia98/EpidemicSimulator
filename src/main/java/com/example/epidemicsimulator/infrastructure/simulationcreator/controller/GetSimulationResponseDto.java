package com.example.epidemicsimulator.infrastructure.simulationcreator.controller;

import lombok.Builder;

@Builder
public record GetSimulationResponseDto(
        int id,
        int populationSize,
        int initialInfected,
        int infectionRate,
        int mortalityRate,
        int infectionDuration,
        int simulationDuration
) {
}
