package com.example.epidemicsimulator.domain;

import org.springframework.data.jpa.repository.JpaRepository;

interface SimulationRepository extends JpaRepository<Simulation, Long> {
}
