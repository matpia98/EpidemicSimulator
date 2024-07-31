# Epidemic Simulator - Backend

## Overview

This project is an Epidemic Simulator backend application built with Spring Boot. It allows users to create, manage, and run simulations of epidemic scenarios, providing daily data on the progression of the disease within a population.

## Features

- Create new epidemic simulations with customizable parameters
- Retrieve all simulations or a specific simulation by ID
- Update existing simulations
- Delete simulations
- Get daily data for a specific simulation
- Input validation for simulation parameters
- Error handling for invalid requests and not found scenarios

## Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- Lombok
- Jakarta Validation
- RESTful API
- JUnit 5 for testing
- TestContainers for integration testing

## Project Structure

The project follows a hexagonal architecture approach with the following main packages:

- `domain.simulationcreator`: Contains core business logic, entities, and interfaces
- `infrastructure.simulationcreator.controller`: Handles HTTP requests and responses
- `infrastructure.apivalidation`: Provides global exception handling
- `infrastructure.simulationcreator.cors`: Configures CORS settings

## API Endpoints

- `POST /api/simulations`: Create a new simulation
- `GET /api/simulations`: Retrieve all simulations
- `GET /api/simulations/{id}`: Retrieve a specific simulation
- `PUT /api/simulations/{id}`: Update an existing simulation
- `DELETE /api/simulations/{id}`: Delete a simulation
- `GET /api/simulations/{id}/daily-data`: Get daily data for a specific simulation

## Data Models

### Simulation

- `id`: Long
- `simulationName`: String
- `populationSize`: int
- `initialInfected`: int
- `infectionRate`: double
- `mortalityRate`: double
- `infectionDuration`: int
- `deathDuration`: int
- `simulationDuration`: int

### DailyData

- `id`: Long
- `day`: int
- `infected`: int
- `susceptible`: int
- `deceased`: int
- `recovered`: int
- `simulation`: Simulation


## Database Setup

This project uses PostgreSQL as its database. A Docker Compose file is provided to easily set up the database environment.

### Prerequisites

- Docker and Docker Compose installed on your system

### Docker Compose Configuration

Create a file named `docker-compose.yml` in the root directory of the project with the following content:

```yaml
version: '3.8'

services:
  db:
    image: postgres:latest
    container_name: postgres-db
    environment:
      POSTGRES_DB: epidemic_simulation
      POSTGRES_USER: user
      POSTGRES_PASSWORD: user123
    ports:
      - "5433:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data

volumes:
  postgres_data:
```

## Starting the Database
To start the PostgreSQL database, run the following command in the directory containing the docker-compose.yml file:
```text
docker-compose up -d
```

## Setup and Running

1. Ensure you have Java JDK 21 or later installed
2. Clone the repository
3. Navigate to the project directory
4. Start the PostgreSQL database using Docker Compose as described above
5. Run `./mvnw spring-boot:run` (for Unix-based systems) or `mvnw.cmd spring-boot:run` (for Windows)
6. The application will start on `http://localhost:8080`

## Testing

The project includes both unit tests and integration tests:

### Unit Tests

Unit tests are implemented using JUnit 5 and cover the core functionality of the `SimulationFacade`. They use in-memory repositories to isolate the tests from external dependencies.

To run unit tests:

```
./mvnw test
```
### Integration Tests

Integration tests are implemented using Spring Boot Test and TestContainers. They test the entire flow of the application, including database interactions.

To run integration tests:

```
./mvnw verify
```

Note: Docker must be installed and running to execute integration tests with TestContainers.

## Frontend Integration

The application is configured to allow CORS for a frontend running on `http://localhost:4200`. Modify the `WebConfig` class if your frontend is running on a different URL.

## Error Handling

The application includes global exception handling for validation errors and simulation not found scenarios. Errors are returned with appropriate HTTP status codes and descriptive messages.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/SomeFeature`)
3. Commit your changes (`git commit -m 'Add some SomeFeature'`)
4. Push to the branch (`git push origin feature/SomeFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE.md) file for details.

## Contact

- Developer: [Mateusz Piasecki](https://github.com/matpia98)
- Project Link: https://github.com/matpia98/EpidemicSimulator-backend
