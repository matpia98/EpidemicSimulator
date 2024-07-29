package com.example.epidemicsimulator.domain.simulationcreator;

import com.example.epidemicsimulator.domain.simulationcreator.dto.SimulationDto;
import com.example.epidemicsimulator.domain.simulationcreator.dto.SimulationRequestDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SimulationFacadeTest {

    @Test
    public void should_add_simulation_to_database() {
        // given
        SimulationFacade simulationFacade = new SimulationFacadeConfiguration().simulationFacade(
                new InMemorySimulationRepository(),
                new InMemoryDailyDataRepository());
        SimulationRequestDto simulationRequestDto = new SimulationRequestDto(
                "covid-19",
                1000,
                10,
                1.5,
                0.01,
                10,
                14,
                30);

        // when
        SimulationDto simulationDto = simulationFacade.addSimulation(simulationRequestDto);

        // then
        assertThat(simulationDto.id()).isEqualTo(1L);
        assertThat(simulationDto.simulationName()).isEqualTo("covid-19");
        assertThat(simulationDto.populationSize()).isEqualTo(1000);
        assertThat(simulationDto.initialInfected()).isEqualTo(10);
        assertThat(simulationDto.infectionRate()).isEqualTo(1.5);
        assertThat(simulationDto.mortalityRate()).isEqualTo(0.01);
        assertThat(simulationDto.infectionDuration()).isEqualTo(10);
        assertThat(simulationDto.deathDuration()).isEqualTo(14);
        assertThat(simulationDto.simulationDuration()).isEqualTo(30);
        assertThat(simulationDto.simulationDuration()).isEqualTo(30);
    }

    @Test
    public void should_return_simulation_by_id_from_database() {
        // given
        SimulationFacade simulationFacade = new SimulationFacadeConfiguration().simulationFacade(
                new InMemorySimulationRepository(),
                new InMemoryDailyDataRepository());
        SimulationRequestDto simulationRequestDto = new SimulationRequestDto(
                "covid-19",
                1000,
                10,
                1.5,
                0.01,
                10,
                14,
                30);
        SimulationDto addedSimulationDto = simulationFacade.addSimulation(simulationRequestDto);

        // when
        Long id = addedSimulationDto.id();
        SimulationDto simulationDto = simulationFacade.getSimulationById(id);

        // then
        assertThat(id).isEqualTo(1L);
        assertThat(simulationDto.simulationName()).isEqualTo("covid-19");
        assertThat(simulationDto.populationSize()).isEqualTo(1000);
        assertThat(simulationDto.initialInfected()).isEqualTo(10);
        assertThat(simulationDto.infectionRate()).isEqualTo(1.5);
        assertThat(simulationDto.mortalityRate()).isEqualTo(0.01);
        assertThat(simulationDto.infectionDuration()).isEqualTo(10);
        assertThat(simulationDto.deathDuration()).isEqualTo(14);
        assertThat(simulationDto.simulationDuration()).isEqualTo(30);
        assertThat(simulationDto.simulationDuration()).isEqualTo(30);
    }





}