package com.example.epidemicsimulator.domain.simulationcreator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SimulationFacadeConfiguration {

    @Bean
    SimulationFacade simulationFacade(SimulationAdder simulationAdder) {
        return new SimulationFacade(simulationAdder);
    }

}
