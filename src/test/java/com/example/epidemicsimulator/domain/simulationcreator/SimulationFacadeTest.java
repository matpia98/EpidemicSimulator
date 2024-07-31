package com.example.epidemicsimulator.domain.simulationcreator;

import com.example.epidemicsimulator.domain.simulationcreator.dto.DailyDataDto;
import com.example.epidemicsimulator.domain.simulationcreator.dto.SimulationDto;
import com.example.epidemicsimulator.domain.simulationcreator.dto.SimulationRequestDto;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SimulationFacadeTest {

    @Test
    public void should_add_simulation() {
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
        assertThat(simulationFacade.getAllSimulations()).isEmpty();
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
    public void should_return_simulation_by_id() {
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

    @Test
    public void should_return_all_simulations() {
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
        SimulationRequestDto simulationRequestDto2 = new SimulationRequestDto(
                "flu",
                10000,
                100,
                1.2,
                0.02,
                7,
                8,
                25);
        simulationFacade.addSimulation(simulationRequestDto);
        simulationFacade.addSimulation(simulationRequestDto2);

        // when
        List<SimulationDto> allSimulations = simulationFacade.getAllSimulations();

        // then
        assertThat(allSimulations).hasSize(2);
        assertThat(allSimulations).extracting(SimulationDto::id).containsExactly(1L, 2L);
        assertThat(allSimulations).extracting(SimulationDto::simulationName).containsExactly("covid-19", "flu");
        assertThat(allSimulations).extracting(SimulationDto::populationSize).containsExactly(1000, 10000);
        assertThat(allSimulations).extracting(SimulationDto::initialInfected).containsExactly(10, 100);
        assertThat(allSimulations).extracting(SimulationDto::infectionRate).containsExactly(1.5, 1.2);
        assertThat(allSimulations).extracting(SimulationDto::mortalityRate).containsExactly(0.01, 0.02);
        assertThat(allSimulations).extracting(SimulationDto::infectionDuration).containsExactly(10, 7);
        assertThat(allSimulations).extracting(SimulationDto::deathDuration).containsExactly(14, 8);
        assertThat(allSimulations).extracting(SimulationDto::simulationDuration).containsExactly(30, 25);
    }

    @Test
    public void should_delete_simulation() {
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
        Long id = addedSimulationDto.id();

        // when
        simulationFacade.deleteSimulationById(id);

        // then
        assertThatThrownBy(() -> simulationFacade.getSimulationById(id))
                .isInstanceOf(SimulationNotFoundException.class)
                .hasMessageContaining("Simulation with id " + id + " not found");
    }

    @Test
    public void should_update_simulation() {
        // given
        SimulationFacade simulationFacade = new SimulationFacadeConfiguration().simulationFacade(
                new InMemorySimulationRepository(),
                new InMemoryDailyDataRepository());
        SimulationRequestDto originalSimulationRequestDto = new SimulationRequestDto(
                "covid-19",
                1000,
                10,
                1.5,
                0.01,
                10,
                14,
                30);
        SimulationDto addedSimulationDto = simulationFacade.addSimulation(originalSimulationRequestDto);
        Long id = addedSimulationDto.id();

        SimulationRequestDto updatedSimulationRequestDto = new SimulationRequestDto(
                "updated-covid-19",
                2000,
                20,
                2.0,
                0.02,
                15,
                21,
                45);

        // when
        SimulationDto updatedSimulationDto = simulationFacade.updateSimulation(id, updatedSimulationRequestDto);

        // then
        assertThat(updatedSimulationDto.id()).isEqualTo(id);
        assertThat(updatedSimulationDto.simulationName()).isEqualTo("updated-covid-19");
        assertThat(updatedSimulationDto.populationSize()).isEqualTo(2000);
        assertThat(updatedSimulationDto.initialInfected()).isEqualTo(20);
        assertThat(updatedSimulationDto.infectionRate()).isEqualTo(2.0);
        assertThat(updatedSimulationDto.mortalityRate()).isEqualTo(0.02);
        assertThat(updatedSimulationDto.infectionDuration()).isEqualTo(15);
        assertThat(updatedSimulationDto.deathDuration()).isEqualTo(21);
        assertThat(updatedSimulationDto.simulationDuration()).isEqualTo(45);
    }

    @Test
    public void should_create_correct_daily_data_when_adding_simulation() {
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
                30
        );

        // when
        SimulationDto addedSimulation = simulationFacade.addSimulation(simulationRequestDto);

        // then
        List<DailyDataDto> dailyDataList = simulationFacade.findBySimulationId(addedSimulation.id());

        assertThat(dailyDataList).hasSize(30);

        DailyDataDto firstDay = dailyDataList.get(0);
        assertThat(firstDay.day()).isEqualTo(1);
        assertThat(firstDay.infected()).isEqualTo(25);
        assertThat(firstDay.susceptible()).isEqualTo(975);
        assertThat(firstDay.deceased()).isEqualTo(0);
        assertThat(firstDay.recovered()).isEqualTo(0);

        DailyDataDto lastDay = dailyDataList.get(29);
        assertThat(lastDay.day()).isEqualTo(30);

        assertThat(dailyDataList).allSatisfy(dailyData -> {
            assertThat(dailyData.infected()).isGreaterThanOrEqualTo(0);
            assertThat(dailyData.susceptible()).isGreaterThanOrEqualTo(0);
            assertThat(dailyData.deceased()).isGreaterThanOrEqualTo(0);
            assertThat(dailyData.recovered()).isGreaterThanOrEqualTo(0);
            assertThat(dailyData.infected() + dailyData.susceptible() + dailyData.deceased() + dailyData.recovered())
                    .isEqualTo(1000);
        });

        IntStream.range(1, dailyDataList.size())
                .forEach(i -> {
                    DailyDataDto prev = dailyDataList.get(i - 1);
                    DailyDataDto curr = dailyDataList.get(i);
                    assertThat(curr.deceased()).isGreaterThanOrEqualTo(prev.deceased());
                    assertThat(curr.recovered()).isGreaterThanOrEqualTo(prev.recovered());
                    assertThat(curr.susceptible()).isLessThanOrEqualTo(prev.susceptible());
                    assertThat(curr.deceased()).isGreaterThanOrEqualTo(prev.deceased());
                });


    }

}