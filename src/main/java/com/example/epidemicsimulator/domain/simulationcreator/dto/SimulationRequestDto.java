package com.example.epidemicsimulator.domain.simulationcreator.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record SimulationRequestDto(

        @NotBlank(message = "Simulation name cannot be blank")
        String simulationName,

        @NotNull(message = "Population size cannot be null")
        @Positive(message = "Population size must be positive")
        Integer populationSize,

        @NotNull(message = "Initial infected cannot be null")
        @PositiveOrZero(message = "Initial infected must be zero or positive")
        Integer initialInfected,

        @NotNull(message = "Infection rate cannot be null")
        @PositiveOrZero(message = "Infection rate must be zero or positive")
        Double infectionRate,

        @NotNull(message = "Mortality rate cannot be null")
        @PositiveOrZero(message = "Mortality rate must be zero or positive")
        Double mortalityRate,

        @NotNull(message = "Infection duration cannot be null")
        @Positive(message = "Infection duration must be positive")
        Integer infectionDuration,

        @NotNull(message = "Death duration cannot be null")
        @Positive(message = "Death duration must be positive")
        Integer deathDuration,

        @NotNull(message = "Simulation duration cannot be null")
        @Positive(message = "Simulation duration must be positive")
        Integer simulationDuration
) {
}