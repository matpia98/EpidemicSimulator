package com.example.epidemicsimulator.domain;

import com.example.epidemicsimulator.domain.dto.SimulationDto;
import com.example.epidemicsimulator.domain.dto.SimulationRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class SimulationAdder {

    private final SimulationRepository simulationRepository;

    SimulationDto addSimulation(SimulationRequestDto requestDto) {
        Simulation simulationToSave = Simulation.builder()
                .populationSize(requestDto.populationSize())
                .initialInfected(requestDto.initialInfected())
                .infectionRate(requestDto.infectionRate())
                .mortalityRate(requestDto.mortalityRate())
                .infectionDuration(requestDto.infectionDuration())
                .simulationDuration(requestDto.simulationDuration())
                .build();
        Simulation savedSimulation = simulationRepository.save(simulationToSave);
        return SimulationDto.builder()
                .id(savedSimulation.getId())
                .populationSize(savedSimulation.getPopulationSize())
                .initialInfected(savedSimulation.getInitialInfected())
                .infectionRate(savedSimulation.getInfectionRate())
                .mortalityRate(savedSimulation.getMortalityRate())
                .infectionDuration(savedSimulation.getInfectionDuration())
                .simulationDuration(savedSimulation.getSimulationDuration())
                .build();
    }
}
