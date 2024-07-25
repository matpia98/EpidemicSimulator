package com.example.epidemicsimulator.domain.simulationcreator;

import com.example.epidemicsimulator.domain.simulationcreator.dto.SimulationDto;
import com.example.epidemicsimulator.domain.simulationcreator.dto.SimulationRequestDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SimulationFacade {

    private final SimulationAdder simulationAdder;

    public SimulationDto addSimulation(SimulationRequestDto requestDto) {
        return simulationAdder.addSimulation(requestDto);
    }
}
