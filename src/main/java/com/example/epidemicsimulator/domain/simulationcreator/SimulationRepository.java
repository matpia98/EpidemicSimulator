package com.example.epidemicsimulator.domain.simulationcreator;

import org.springframework.data.jpa.repository.JpaRepository;

interface SimulationRepository extends JpaRepository<Simulation, Long> {
}
