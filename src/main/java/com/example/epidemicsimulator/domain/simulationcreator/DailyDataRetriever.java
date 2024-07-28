package com.example.epidemicsimulator.domain.simulationcreator;

import com.example.epidemicsimulator.domain.simulationcreator.dto.DailyDataDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class DailyDataRetriever {

    private final DailyDataRepository dailyDataRepository;

    List<DailyDataDto> findBySimulationId(Long id) {
        List<DailyData> dataBySimulationId = dailyDataRepository.findBySimulationId(id);
        return dataBySimulationId.stream()
                .map(dailyData -> DailyDataDto.builder()
                        .id(dailyData.getId())
                        .day(dailyData.getDay())
                        .infected(dailyData.getInfected())
                        .susceptible(dailyData.getSusceptible())
                        .deceased(dailyData.getDeceased())
                        .recovered(dailyData.getRecovered())
                        .simulationId(dailyData.getSimulation().getId())
                        .build())
                .collect(Collectors.toList());
    }
}
