package com.example.epidemicsimulator.domain.simulationcreator;

import com.example.epidemicsimulator.domain.simulationcreator.dto.SimulationDto;
import com.example.epidemicsimulator.domain.simulationcreator.dto.SimulationRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
class SimulationUpdater {

    private final SimulationRepository simulationRepository;
    private final DailyDataRepository dailyDataRepository;
    private final SimulationAdder simulationAdder;

    SimulationDto updateSimulation(Long id, SimulationRequestDto requestDto) {
        Simulation simulationToUpdate = simulationRepository.findById(id)
                .orElseThrow(() -> new SimulationNotFoundException("Simulation with id " + id + " not found"));

        simulationToUpdate.setSimulationName(requestDto.simulationName());
        simulationToUpdate.setPopulationSize(requestDto.populationSize());
        simulationToUpdate.setInitialInfected(requestDto.initialInfected());
        simulationToUpdate.setInfectionRate(requestDto.infectionRate());
        simulationToUpdate.setMortalityRate(requestDto.mortalityRate());
        simulationToUpdate.setInfectionDuration(requestDto.infectionDuration());
        simulationToUpdate.setDeathDuration(requestDto.deathDuration());
        simulationToUpdate.setSimulationDuration(requestDto.simulationDuration());

        dailyDataRepository.deleteBySimulationId(simulationToUpdate.getId());

        Simulation updatedSimulation = simulationRepository.save(simulationToUpdate);

        List<DailyData> dailyDataList = simulationAdder.runSimulation(updatedSimulation);
        dailyDataRepository.saveAll(dailyDataList);

        return SimulationMapper.mapFromSimulationToSimulationDto(updatedSimulation);
    }
}
