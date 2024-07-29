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

        // Update the simulation entity with new values
        simulationToUpdate.setSimulationName(requestDto.simulationName());
        simulationToUpdate.setPopulationSize(requestDto.populationSize());
        simulationToUpdate.setInitialInfected(requestDto.initialInfected());
        simulationToUpdate.setInfectionRate(requestDto.infectionRate());
        simulationToUpdate.setMortalityRate(requestDto.mortalityRate());
        simulationToUpdate.setInfectionDuration(requestDto.infectionDuration());
        simulationToUpdate.setDeathDuration(requestDto.deathDuration());
        simulationToUpdate.setSimulationDuration(requestDto.simulationDuration());

        // Delete existing DailyData records for the simulation
        dailyDataRepository.deleteBySimulationId(simulationToUpdate.getId());

        // Save the updated simulation
        Simulation updatedSimulation = simulationRepository.save(simulationToUpdate);

        // Run the simulation again to create new DailyData records
        List<DailyData> dailyDataList = simulationAdder.runSimulation(updatedSimulation);
        dailyDataRepository.saveAll(dailyDataList);

        return SimulationMapper.mapFromSimulationToSimulationDto(updatedSimulation);
    }
}
