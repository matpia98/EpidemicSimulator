package com.example.epidemicsimulator.domain.simulationcreator;

import com.example.epidemicsimulator.domain.simulationcreator.dto.SimulationDto;
import com.example.epidemicsimulator.domain.simulationcreator.dto.SimulationRequestDto;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class SimulationFacade {

    private final SimulationAdder simulationAdder;
    private final SimulationRetriever simulationRetriever;

    public SimulationDto addSimulation(SimulationRequestDto requestDto) {
        return simulationAdder.addSimulation(requestDto);
    }

    public List<SimulationDto> getAllSimulations() {
        return simulationRetriever.findAll();
    }

    public SimulationDto getSimulationById(Long id) {
        return simulationRetriever.findById(id);
    }
}
