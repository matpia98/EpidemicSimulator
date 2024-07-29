package com.example.epidemicsimulator.domain.simulationcreator.dto;

import lombok.Builder;

@Builder
public record DailyDataDto(
         Long id,
         int day,
         int infected,
         int susceptible,
         int deceased,
         int recovered,
         Long simulationId
) {
}
