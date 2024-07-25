package com.example.epidemicsimulator.domain.simulationcreator;

import com.example.epidemicsimulator.domain.simulationcreator.dto.SimulationDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class SimulationRetriever {

    private final SimulationRepository simulationRepository;

    List<SimulationDto> findAll() {
        List<Simulation> simulations = simulationRepository.findAll();
        return simulations.stream()
                .map(SimulationMapper::mapFromSimulationToSimulationDto)
                .collect(Collectors.toList());
    }

    SimulationDto findById(Long id) {
        Simulation simulation = simulationRepository.findById(id)
                .orElseThrow(() -> new SimulationNotFoundException("Simulation not found"));
        return SimulationMapper.mapFromSimulationToSimulationDto(simulation);
    }
}
