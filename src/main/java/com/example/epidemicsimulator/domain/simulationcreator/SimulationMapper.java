package com.example.epidemicsimulator.domain.simulationcreator;

import com.example.epidemicsimulator.domain.simulationcreator.dto.SimulationDto;
import com.example.epidemicsimulator.domain.simulationcreator.dto.SimulationRequestDto;

class SimulationMapper {

    static Simulation mapFromSimulationRequestDtoToSimulation(SimulationRequestDto requestDto) {
        return Simulation.builder()
                .simulationName(requestDto.simulationName())
                .populationSize(requestDto.populationSize())
                .initialInfected(requestDto.initialInfected())
                .infectionRate(requestDto.infectionRate())
                .mortalityRate(requestDto.mortalityRate())
                .infectionDuration(requestDto.infectionDuration())
                .deathDuration(requestDto.deathDuration())
                .simulationDuration(requestDto.simulationDuration())
                .build();
    }

    static SimulationDto mapFromSimulationToSimulationDto(Simulation simulation) {
        return SimulationDto.builder()
                .id(simulation.getId())
                .simulationName(simulation.getSimulationName())
                .populationSize(simulation.getPopulationSize())
                .initialInfected(simulation.getInitialInfected())
                .infectionRate(simulation.getInfectionRate())
                .mortalityRate(simulation.getMortalityRate())
                .infectionDuration(simulation.getInfectionDuration())
                .deathDuration(simulation.getDeathDuration())
                .simulationDuration(simulation.getSimulationDuration())
                .build();
    }

}
