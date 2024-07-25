package com.example.epidemicsimulator.infrastructure.simulationcreator.controller.dto;

import com.example.epidemicsimulator.domain.simulationcreator.dto.SimulationDto;

import java.util.List;

public record GetAllSimulationsResponseDto(List<SimulationDto> simulationsDto) {
}
