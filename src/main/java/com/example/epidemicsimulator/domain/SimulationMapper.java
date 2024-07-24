package com.example.epidemicsimulator.domain;

import com.example.epidemicsimulator.domain.dto.SimulationDto;
import com.example.epidemicsimulator.domain.dto.SimulationRequestDto;

class SimulationMapper {

    static Simulation mapFromSimulationRequestDtoToSimulation(SimulationRequestDto requestDto) {
        return Simulation.builder()
                .populationSize(requestDto.populationSize())
                .initialInfected(requestDto.initialInfected())
                .infectionRate(requestDto.infectionRate())
                .mortalityRate(requestDto.mortalityRate())
                .infectionDuration(requestDto.infectionDuration())
                .simulationDuration(requestDto.simulationDuration())
                .build();
    }

    static SimulationDto mapFromSimulationToSimulationDto(Simulation simulation) {
        return SimulationDto.builder()
                .id(simulation.getId())
                .populationSize(simulation.getPopulationSize())
                .initialInfected(simulation.getInitialInfected())
                .infectionRate(simulation.getInfectionRate())
                .mortalityRate(simulation.getMortalityRate())
                .infectionDuration(simulation.getInfectionDuration())
                .simulationDuration(simulation.getSimulationDuration())
                .build();
    }

}
