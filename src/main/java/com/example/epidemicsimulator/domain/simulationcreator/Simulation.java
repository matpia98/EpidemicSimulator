package com.example.epidemicsimulator.domain.simulationcreator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
class Simulation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int populationSize;
    private int initialInfected;
    private int infectionRate;
    private int mortalityRate;
    private int infectionDuration;
    private int simulationDuration;

    @OneToMany(mappedBy = "simulation", cascade = CascadeType.ALL)
    private List<DailyData> dailyDataList;
}
