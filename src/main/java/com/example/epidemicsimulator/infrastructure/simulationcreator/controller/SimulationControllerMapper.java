package com.example.epidemicsimulator.infrastructure.simulationcreator.controller;

import com.example.epidemicsimulator.domain.simulationcreator.dto.SimulationDto;
import com.example.epidemicsimulator.infrastructure.simulationcreator.controller.dto.CreateSimulationDto;
import com.example.epidemicsimulator.infrastructure.simulationcreator.controller.dto.GetAllSimulationsResponseDto;
import com.example.epidemicsimulator.infrastructure.simulationcreator.controller.dto.GetSimulationResponseDto;
import com.example.epidemicsimulator.infrastructure.simulationcreator.controller.dto.UpdateSimulationResponseDto;

import java.util.List;

class SimulationControllerMapper {

    static CreateSimulationDto mapFromSimulationDtoToCreateSimulationDto(SimulationDto simulationDto) {
        return CreateSimulationDto.builder()
                .id(simulationDto.id())
                .simulationName(simulationDto.simulationName())
                .populationSize(simulationDto.populationSize())
                .initialInfected(simulationDto.initialInfected())
                .infectionRate(simulationDto.infectionRate())
                .mortalityRate(simulationDto.mortalityRate())
                .infectionDuration(simulationDto.infectionDuration())
                .deathDuration(simulationDto.deathDuration())
                .simulationDuration(simulationDto.simulationDuration())
                .build();
    }

    static GetAllSimulationsResponseDto mapFromSimulationToGetAllSimulationsResponseDto(List<SimulationDto> allSimulations) {
        return new GetAllSimulationsResponseDto(allSimulations);
    }

    static GetSimulationResponseDto mapFromSimulationDtotoGetSimulationResponseDto(SimulationDto simulationDto) {
        return GetSimulationResponseDto.builder()
                .id(simulationDto.id())
                .simulationName(simulationDto.simulationName())
                .populationSize(simulationDto.populationSize())
                .initialInfected(simulationDto.initialInfected())
                .infectionRate(simulationDto.infectionRate())
                .mortalityRate(simulationDto.mortalityRate())
                .infectionDuration(simulationDto.infectionDuration())
                .deathDuration(simulationDto.deathDuration())
                .simulationDuration(simulationDto.simulationDuration())
                .build();
    }

    static UpdateSimulationResponseDto mapFromSimulationDtotoUpdateSimulationResponseDto(SimulationDto simulationDto) {
        return UpdateSimulationResponseDto.builder()
                .id(simulationDto.id())
                .simulationName(simulationDto.simulationName())
                .populationSize(simulationDto.populationSize())
                .initialInfected(simulationDto.initialInfected())
                .infectionRate(simulationDto.infectionRate())
                .mortalityRate(simulationDto.mortalityRate())
                .infectionDuration(simulationDto.infectionDuration())
                .deathDuration(simulationDto.deathDuration())
                .simulationDuration(simulationDto.simulationDuration())
                .build();
    }
}
