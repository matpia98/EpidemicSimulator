package com.example.epidemicsimulator.infrastructure.simulationcreator.controller;

import com.example.epidemicsimulator.domain.simulationcreator.dto.SimulationDto;
import com.example.epidemicsimulator.infrastructure.simulationcreator.controller.dto.CreateSimulationDto;
import com.example.epidemicsimulator.infrastructure.simulationcreator.controller.dto.GetAllSimulationsResponseDto;

import java.util.List;

class SimulationControllerMapper {

    static CreateSimulationDto mapFromSimulationDtoToCreateSimulationDto(SimulationDto simulationDto) {
        return CreateSimulationDto.builder()
                .id(simulationDto.id())
                .populationSize(simulationDto.populationSize())
                .initialInfected(simulationDto.initialInfected())
                .infectionRate(simulationDto.infectionRate())
                .mortalityRate(simulationDto.mortalityRate())
                .infectionDuration(simulationDto.infectionDuration())
                .simulationDuration(simulationDto.simulationDuration())
                .build();
    }

    static GetAllSimulationsResponseDto mapFromSimulationToGetAllSimulationsResponseDto(List<SimulationDto> allSimulations) {
        return new GetAllSimulationsResponseDto(allSimulations);
    }
}
