package com.example.epidemicsimulator.domain;

import com.example.epidemicsimulator.domain.dto.SimulationDto;
import com.example.epidemicsimulator.domain.dto.SimulationRequestDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SimulationFacade {

    private final SimulationAdder simulationAdder;

    public SimulationDto addSimulation(SimulationRequestDto requestDto) {
        return simulationAdder.addSimulation(requestDto);
    }
}
