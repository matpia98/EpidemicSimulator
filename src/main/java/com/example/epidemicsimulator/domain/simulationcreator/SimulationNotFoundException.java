package com.example.epidemicsimulator.domain.simulationcreator;

public class SimulationNotFoundException extends RuntimeException {
    public SimulationNotFoundException(String message) {
        super(message);
    }
}
