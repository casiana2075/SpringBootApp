# SpringBootApp
 This app follows the typical layered architecture of a Spring Boot application, separating concerns between data persistence, business logic, and client interaction.


1. `Player.java` defines the database table structure as a model.
2. `PlayerRepository.java` is responsible for database interactions.
3. `PlayerService.java` contains business and application logic.
4. `PlayerController.java` acts as a REST API endpoint for managing player data.
