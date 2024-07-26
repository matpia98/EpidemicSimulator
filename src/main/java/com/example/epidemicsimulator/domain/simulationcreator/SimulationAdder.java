package com.example.epidemicsimulator.domain.simulationcreator;

import com.example.epidemicsimulator.domain.simulationcreator.dto.SimulationDto;
import com.example.epidemicsimulator.domain.simulationcreator.dto.SimulationRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Service
@AllArgsConstructor
class SimulationAdder {

    private final SimulationRepository simulationRepository;
    private final DailyDataRepository dailyDataRepository;

    SimulationDto addSimulation(SimulationRequestDto requestDto) {
        Simulation simulationToSave = SimulationMapper.mapFromSimulationRequestDtoToSimulation(requestDto);
        Simulation savedSimulation = simulationRepository.save(simulationToSave);

        List<DailyData> dailyDataList = runSimulation(savedSimulation);
        dailyDataRepository.saveAll(dailyDataList);

        return SimulationMapper.mapFromSimulationToSimulationDto(savedSimulation);
    }

    private List<DailyData> runSimulation(Simulation simulation) {
        List<DailyData> dailyDataList = new ArrayList<>();

        int susceptible = simulation.getPopulationSize() - simulation.getInitialInfected();
        int infected = simulation.getInitialInfected();
        int deceased = 0;
        int recovered = 0;

        int Tm = simulation.getDeathDuration();
        int Ti = simulation.getInfectionDuration();
        double R = simulation.getInfectionRate();
        int days = simulation.getSimulationDuration();

        Queue<Integer> infectionQueue = new LinkedList<>();
        Queue<Integer> deathQueue = new LinkedList<>();

        for (int day = 1; day <= days; day++) {
            if (day >= Tm) {
                int newlyDeceased = deathQueue.poll();
                newlyDeceased = Math.min(newlyDeceased, infected);
                infected -= newlyDeceased;
                deceased += newlyDeceased;
            }

            if (day >= Ti) {
                int newlyRecovered;
                if (day == Ti) {
                    newlyRecovered = infectionQueue.poll() + simulation.getInitialInfected();
                } else {
                    newlyRecovered = infectionQueue.poll();
                }
                    newlyRecovered = Math.min(newlyRecovered, infected);
                    infected -= newlyRecovered;
                    recovered += newlyRecovered;
            }

            int newlyInfected = (int) (infected * R);
            if (newlyInfected > susceptible) {
                newlyInfected = susceptible;
            }
            susceptible -= newlyInfected;
            infected += newlyInfected;

            infectionQueue.add(newlyInfected);
            deathQueue.add((int) (newlyInfected * simulation.getMortalityRate()));

            infected = Math.max(infected, 0);
            susceptible = Math.max(susceptible, 0);
            deceased = Math.max(deceased, 0);
            recovered = Math.max(recovered, 0);

            DailyData dailyData = new DailyData(
                    null,
                    day,
                    infected,
                    susceptible,
                    deceased,
                    recovered,
                    simulation
            );

            dailyDataList.add(dailyData);
        }

        return dailyDataList;
    }
}
