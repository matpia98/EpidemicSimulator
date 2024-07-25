package com.example.epidemicsimulator.infrastructure.simulationcreator.controller;

import com.example.epidemicsimulator.domain.simulationcreator.dto.SimulationDto;
import com.example.epidemicsimulator.infrastructure.simulationcreator.controller.dto.CreateSimulationDto;

class SongControllerMapper {

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
}
