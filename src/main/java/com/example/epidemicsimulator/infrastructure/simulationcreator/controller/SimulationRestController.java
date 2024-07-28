package com.example.epidemicsimulator.infrastructure.simulationcreator.controller;

import com.example.epidemicsimulator.domain.simulationcreator.SimulationFacade;
import com.example.epidemicsimulator.domain.simulationcreator.dto.SimulationDto;
import com.example.epidemicsimulator.domain.simulationcreator.dto.SimulationRequestDto;
import com.example.epidemicsimulator.infrastructure.simulationcreator.controller.dto.CreateSimulationDto;
import com.example.epidemicsimulator.domain.simulationcreator.dto.DailyDataDto;
import com.example.epidemicsimulator.infrastructure.simulationcreator.controller.dto.GetAllSimulationsResponseDto;
import com.example.epidemicsimulator.infrastructure.simulationcreator.controller.dto.GetSimulationResponseDto;
import com.example.epidemicsimulator.infrastructure.simulationcreator.controller.dto.UpdateSimulationResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.epidemicsimulator.infrastructure.simulationcreator.controller.SimulationControllerMapper.*;

@RestController
@RequestMapping("/api/simulations")
@AllArgsConstructor
class SimulationRestController {

    private final SimulationFacade simulationFacade;

    @PostMapping
    public ResponseEntity<CreateSimulationDto> addSimulation(@RequestBody SimulationRequestDto requestDto) {
        SimulationDto simulationDto = simulationFacade.addSimulation(requestDto);
        CreateSimulationDto createSimulationDto = mapFromSimulationDtoToCreateSimulationDto(simulationDto);
        return ResponseEntity.ok(createSimulationDto);
    }

    @GetMapping
    private ResponseEntity<GetAllSimulationsResponseDto> getAllSimulations() {
        List<SimulationDto> allSimulations = simulationFacade.getAllSimulations();
        GetAllSimulationsResponseDto response = mapFromSimulationToGetAllSimulationsResponseDto(allSimulations);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetSimulationResponseDto> getSimulation(@PathVariable Long id) {
        SimulationDto simulationById = simulationFacade.getSimulationById(id);
        GetSimulationResponseDto response = mapFromSimulationDtotoGetSimulationResponseDto(simulationById);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateSimulationResponseDto> updateSimulation(@PathVariable Long id, @RequestBody SimulationRequestDto requestDto) {
        SimulationDto updatedSimulation = simulationFacade.updateSimulation(id, requestDto);
        UpdateSimulationResponseDto response = mapFromSimulationDtotoUpdateSimulationResponseDto(updatedSimulation);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/daily-data")
    public ResponseEntity<List<DailyDataDto>> getDailyData(@PathVariable Long id) {
        List<DailyDataDto> dailyDataList = simulationFacade.findBySimulationId(id);
        return ResponseEntity.ok(dailyDataList);
    }


}
