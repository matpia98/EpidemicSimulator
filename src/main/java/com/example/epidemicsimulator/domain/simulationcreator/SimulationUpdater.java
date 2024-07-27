package com.example.epidemicsimulator.domain.simulationcreator;

import com.example.epidemicsimulator.domain.simulationcreator.dto.SimulationDto;
import com.example.epidemicsimulator.domain.simulationcreator.dto.SimulationRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class SimulationUpdater {

    private final SimulationRepository simulationRepository;

    SimulationDto updateSimulation(Long id, SimulationRequestDto requestDto) {
        Simulation simulationToUpdate = simulationRepository.findById(id)
                .orElseThrow(() -> new SimulationNotFoundException("Simulation not found"));

        simulationToUpdate.setSimulationName(requestDto.simulationName());
        simulationToUpdate.setPopulationSize(requestDto.populationSize());
        simulationToUpdate.setInitialInfected(requestDto.initialInfected());
        simulationToUpdate.setInfectionRate(requestDto.infectionRate());
        simulationToUpdate.setMortalityRate(requestDto.mortalityRate());
        simulationToUpdate.setInfectionDuration(requestDto.infectionDuration());
        simulationToUpdate.setDeathDuration(requestDto.deathDuration());
        simulationToUpdate.setSimulationDuration(requestDto.simulationDuration());

        Simulation updatedSimulation = simulationRepository.save(simulationToUpdate);

        return SimulationMapper.mapFromSimulationToSimulationDto(updatedSimulation);
    }
}
