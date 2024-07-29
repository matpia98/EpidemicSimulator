package com.example.epidemicsimulator.domain.simulationcreator;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class SimulationDeleter {

    private final SimulationRepository simulationRepository;

    void deleteSimulationById(Long id) {
        simulationRepository.findById(id)
                .orElseThrow(() -> new SimulationNotFoundException("Simulation with id " + id + " not found"));
        simulationRepository.deleteById(id);
    }
}
