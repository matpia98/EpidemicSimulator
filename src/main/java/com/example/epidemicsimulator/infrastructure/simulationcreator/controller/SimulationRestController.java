package com.example.epidemicsimulator.infrastructure.simulationcreator.controller;

import com.example.epidemicsimulator.domain.simulationcreator.SimulationFacade;
import com.example.epidemicsimulator.domain.simulationcreator.dto.SimulationDto;
import com.example.epidemicsimulator.domain.simulationcreator.dto.SimulationRequestDto;
import com.example.epidemicsimulator.infrastructure.simulationcreator.controller.dto.CreateSimulationDto;
import com.example.epidemicsimulator.infrastructure.simulationcreator.controller.dto.GetAllSimulationsResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.epidemicsimulator.infrastructure.simulationcreator.controller.SimulationControllerMapper.mapFromSimulationDtoToCreateSimulationDto;
import static com.example.epidemicsimulator.infrastructure.simulationcreator.controller.SimulationControllerMapper.mapFromSimulationToGetAllSimulationsResponseDto;

@RestController
@RequestMapping("/simulations")
@AllArgsConstructor
class SimulationRestController {

    private final SimulationFacade simulationFacade;

    @PostMapping("/add")
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
}
