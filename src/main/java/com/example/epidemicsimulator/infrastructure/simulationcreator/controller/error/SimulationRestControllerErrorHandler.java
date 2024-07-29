package com.example.epidemicsimulator.infrastructure.simulationcreator.controller.error;

import com.example.epidemicsimulator.domain.simulationcreator.SimulationNotFoundException;
import com.example.epidemicsimulator.infrastructure.simulationcreator.controller.SimulationRestController;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice(basePackageClasses = SimulationRestController.class)
@Log4j2
public class SimulationRestControllerErrorHandler {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @ExceptionHandler(SimulationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorSimulationResponseDto> handleSimulationNotFoundException(SimulationNotFoundException ex) {
        String exceptionDate = LocalDateTime.now().format(FORMATTER);
        ErrorSimulationResponseDto errorResponse = new ErrorSimulationResponseDto(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                exceptionDate
        );
        log.error(errorResponse.message());

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value())
                .body(errorResponse);
    }
}