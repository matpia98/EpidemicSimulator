package com.example.epidemicsimulator.domain.simulationcreator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SimulationFacadeConfiguration {

    @Bean
    SimulationFacade simulationFacade(SimulationRepository simulationRepository, DailyDataRepository dailyDataRepository) {
        SimulationAdder simulationAdder = new SimulationAdder(simulationRepository, dailyDataRepository);
        SimulationRetriever simulationRetriever = new SimulationRetriever(simulationRepository);
        SimulationUpdater simulationUpdater = new SimulationUpdater(simulationRepository, dailyDataRepository, simulationAdder);
        DailyDataRetriever dailyDataRetriever = new DailyDataRetriever(dailyDataRepository);
        return new SimulationFacade(simulationAdder, simulationRetriever, simulationUpdater, dailyDataRetriever);
    }

}
