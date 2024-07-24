package com.example.epidemicsimulator.domain;

import org.springframework.data.jpa.repository.JpaRepository;

interface DailyDataRepository extends JpaRepository<DailyData, Long> {
}
