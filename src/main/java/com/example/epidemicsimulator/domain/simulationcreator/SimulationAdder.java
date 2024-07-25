package com.example.epidemicsimulator.domain.simulationcreator;

import com.example.epidemicsimulator.domain.simulationcreator.dto.SimulationDto;
import com.example.epidemicsimulator.domain.simulationcreator.dto.SimulationRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class SimulationAdder {

    private final SimulationRepository simulationRepository;

    SimulationDto addSimulation(SimulationRequestDto requestDto) {
        Simulation simulationToSave = SimulationMapper.mapFromSimulationRequestDtoToSimulation(requestDto);
        Simulation savedSimulation = simulationRepository.save(simulationToSave);
        return SimulationMapper.mapFromSimulationToSimulationDto(savedSimulation);
    }
}
