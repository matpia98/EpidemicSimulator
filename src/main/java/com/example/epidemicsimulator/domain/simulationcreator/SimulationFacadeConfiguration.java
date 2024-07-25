package com.example.epidemicsimulator.domain.simulationcreator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SimulationFacadeConfiguration {

    @Bean
    SimulationFacade simulationFacade(SimulationRepository simulationRepository, DailyDataRepository dailyDataRepository) {
        SimulationAdder simulationAdder = new SimulationAdder(simulationRepository, dailyDataRepository);
        SimulationRetriever simulationRetriever = new SimulationRetriever(simulationRepository);
        return new SimulationFacade(simulationAdder, simulationRetriever);
    }

}
