package com.example.epidemicsimulator.domain.simulationcreator;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface DailyDataRepository extends JpaRepository<DailyData, Long> {
    void deleteBySimulationId(int id);

    List<DailyData> findBySimulationId(Long id);
}
