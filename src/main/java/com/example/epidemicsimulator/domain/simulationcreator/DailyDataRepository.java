package com.example.epidemicsimulator.domain.simulationcreator;

import org.springframework.data.jpa.repository.JpaRepository;

interface DailyDataRepository extends JpaRepository<DailyData, Long> {
    void deleteBySimulationId(int id);
}
