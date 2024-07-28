package com.example.epidemicsimulator.domain.simulationcreator;

import com.example.epidemicsimulator.domain.simulationcreator.dto.DailyDataDto;
import com.example.epidemicsimulator.domain.simulationcreator.dto.SimulationDto;
import com.example.epidemicsimulator.domain.simulationcreator.dto.SimulationRequestDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class SimulationFacade {

    private final SimulationAdder simulationAdder;
    private final SimulationRetriever simulationRetriever;
    private final SimulationUpdater simulationUpdater;
    private final DailyDataRetriever dailyDataRetriever;
    private final SimulationDeleter simulationDeleter;

    public SimulationDto addSimulation(SimulationRequestDto requestDto) {
        return simulationAdder.addSimulation(requestDto);
    }

    public List<SimulationDto> getAllSimulations() {
        return simulationRetriever.findAll();
    }

    public SimulationDto getSimulationById(Long id) {
        return simulationRetriever.findById(id);
    }

    @Transactional
    public SimulationDto updateSimulation(Long id, SimulationRequestDto requestDto) {
        return simulationUpdater.updateSimulation(id, requestDto);
    }


    public List<DailyDataDto> findBySimulationId(Long id) {
        return dailyDataRetriever.findBySimulationId(id);
    }

    public void deleteSimulationById(Long id) {
        simulationDeleter.deleteSimulationById(id);
    }
}
