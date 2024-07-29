package feature;

import com.example.epidemicsimulator.EpidemicSimulatorApplication;
import com.example.epidemicsimulator.domain.simulationcreator.dto.SimulationRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = EpidemicSimulatorApplication.class)
@Testcontainers
class SimulationRestControllerIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15-alpine")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @Autowired
    private WebTestClient webTestClient;

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @Test
    public void happyPath() {
        // 1. when I go to /api/simulations, I can see no simulations
        webTestClient.get().uri("/api/simulations")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.simulationsDto").isArray()
                .jsonPath("$.simulationsDto.length()").isEqualTo(0);

        // 2. when I post to /api/simulations, new simulation is returned with id: 1
        SimulationRequestDto simulationRequest1 = new SimulationRequestDto(
                "Simulation 1", 1000, 10, 0.5, 0.01, 14, 21, 30
        );

        webTestClient.post().uri("/api/simulations")
                .bodyValue(simulationRequest1)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.simulationName").isEqualTo("Simulation 1");

        // 3. when I post to /api/simulations, new simulation is returned with id: 2
        SimulationRequestDto simulationRequest2 = new SimulationRequestDto(
                "Simulation 2", 2000, 20, 0.7, 0.02, 10, 18, 45
        );

        webTestClient.post().uri("/api/simulations")
                .bodyValue(simulationRequest2)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(2)
                .jsonPath("$.simulationName").isEqualTo("Simulation 2");

        // 4. when I go to /api/simulations, I can see 2 simulations
        webTestClient.get().uri("/api/simulations")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.simulationsDto").isArray()
                .jsonPath("$.simulationsDto.length()").isEqualTo(2);

        // 5. when I go to /api/simulations/1, I can see simulation with id 1
        webTestClient.get().uri("/api/simulations/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.simulationName").isEqualTo("Simulation 1");

        // 6. when I update simulation with id 1
        SimulationRequestDto updateRequest = new SimulationRequestDto(
                "Updated Simulation 1", 1500, 15, 0.6, 0.015, 12, 20, 35
        );

        webTestClient.put().uri("/api/simulations/1")
                .bodyValue(updateRequest)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.simulationName").isEqualTo("Updated Simulation 1");

        // 7. when I delete simulation with id 2
        webTestClient.delete().uri("/api/simulations/2")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.message").isEqualTo("You deleted simulation with id: 2")
                .jsonPath("$.status").isEqualTo("OK");

        // 8. when I go to /api/simulations, I can see 1 simulation
        webTestClient.get().uri("/api/simulations")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.simulationsDto").isArray()
                .jsonPath("$.simulationsDto.length()").isEqualTo(1);

        // 9. when I go to /api/simulations/1/daily-data, I can see daily data for simulation 1
        webTestClient.get().uri("/api/simulations/1/daily-data")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$.length()").isEqualTo(35); // Assuming daily data for each day of simulation duration
    }
}

