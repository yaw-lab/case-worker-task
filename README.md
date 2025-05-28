Case Worker Task Backend

This repository hosts the backend application for a Case Worker Task Management system, built with Spring Boot. It provides RESTful APIs for managing case-related tasks, integrating with a PostgreSQL database, and utilizing Docker for easy setup.


No problem! A good README.md is essential for any project, especially on GitHub. It tells potential users and contributors what your project is, how to set it up, and how to use it.

Here's a README.md tailored for your case-worker-task-backend project, incorporating details we've discussed (like Spring Boot, PostgreSQL, Docker, Actuator endpoints, and error handling).

Case Worker Task Backend

This repository hosts the backend application for a Case Worker Task Management system, built with Spring Boot. It provides RESTful APIs for managing case-related tasks, integrating with a PostgreSQL database, and utilizing Docker for easy setup.

Features
RESTful API: Exposes endpoints for managing Case entities.
Spring Boot: Leverages Spring Boot for rapid application development.
Spring Data JPA: Simplifies data access and persistence with a PostgreSQL database.
Docker & Docker Compose: Containerized setup for the PostgreSQL database, ensuring consistency across environments.
Lombok: Reduces boilerplate code (e.g., getters, setters, constructors).
Spring Actuator: Provides production-ready monitoring and management endpoints.
Global Exception Handling: Custom error responses for consistent API error messages instead of Spring Boot's default Whitelabel Error Page.
Profile-Based Configuration: Differentiates settings for development and production environments.
Initial Data Loading: Populates the database with sample Case data on startup.
Technologies Used
Java 21+
Spring Boot 3+
Maven
PostgreSQL
Docker
Lombok
JUnit 5
Mockito
Getting Started
Follow these steps to get the application up and running on your local machine.

Prerequisites
Java Development Kit (JDK) 21 or higher
Apache Maven 3.6+
Docker Desktop (for Windows/macOS) or Docker Engine (for Linux) installed and running.
1. Clone the Repository
   Bash

git clone https://github.com/yaw-lab/case-worker-task.git
cd case-worker-task
2. Start the PostgreSQL Database (using Docker Compose)
   Navigate to the project root directory where docker/docker-compose.yml is located:

Bash

docker-compose -f ./docker/docker-compose.yml up -d
This command will:

Download the PostgreSQL Docker image (if not already present).
Create and start a PostgreSQL container in the background.
The database will be accessible on localhost:5432. Check the docker/docker-compose.yml for exact service and database names.
3. Build the Application
   Build the Spring Boot application using Maven:

Bash

mvn clean install 

4. Run the Application
   You can run the application with different Spring Profiles for different environments ie. dev, prd.

Development Profile (Default)
The default profile will use an in-memory H2 database (if configured) or the settings from application.properties.

Bash

java -jar target/caseworkers-task-backend-0.0.1-SNAPSHOT.jar
./mvnw clean spring-boot:run -Dspring-boot.run.profiles=dev // for active dev profile 
# OR if using Maven wrapper:
./mvnw spring-boot:run
Production Profile
To run with production-specific settings (e.g., connecting to your Dockerized PostgreSQL, restricted Actuator endpoints):

Bash

java -jar target/caseworkers-task-backend-0.0.1-SNAPSHOT.jar --spring.profiles.active=production
# OR if using Maven wrapper:
./mvnw spring-boot:run -Dspring-boot.run.profiles=production
API Endpoints
Once the application is running, you can access the API at http://localhost:8080 (or http://localhost:9000 if using the production profile).

Core Endpoints (Examples)
GET /: Returns application properties (name, version, environment, welcome message).
Example Response:
JSON

{
"name": "Case Worker Task Backend",
"version": "1.0.0",
"environment": "development",
"welcomeMessage": "Welcome to the Case Worker Task Management Backend!"
}
GET /cases: Retrieve all cases.
GET /cases/{id}: Retrieve a specific case by ID.
POST /cases: Create a new case.
PUT /cases/{id}: Update an existing case by ID.
DELETE /cases/{id}: Delete a case by ID.
Management Endpoints (Spring Actuator)
Spring Actuator endpoints are exposed at /actuator.

Development Profile (--spring.profiles.active=development):

GET /actuator: Discover all exposed endpoints.
GET /actuator/health: Check application health.
GET /actuator/info: Get general application info.
All other Actuator endpoints (e.g., /actuator/beans, /actuator/env, /actuator/metrics) are accessible.
Production Profile (--spring.profiles.active=production):

GET /actuator/health: Check application health (with details).
GET /actuator/info: Get general application info.
Only health and info are exposed for security reasons. Other endpoints will return a 404 Not Found.
Error Handling
The application provides custom JSON error responses for better API client experience.

Example 404 Not Found (for non-existent paths or resources):
JSON

{
"timestamp": "2024-05-28T10:00:00.000000",
"status": 404,
"error": "Not Found",
"message": "Resource not found for path: /nonexistent-path",
"path": "/nonexistent-path"
}
Example for specific exceptions (e.g., ResourceNotFoundException):
JSON

{
"timestamp": "2024-05-28T10:05:00.000000",
"status": 404,
"error": "Not Found",
"message": "Case with ID 66 not found.",
"path": "/cases/66"
}
Running Tests
To run all unit and integration tests:

Bash

mvn test
Contributing
Feel free to fork the repository, create feature branches, and submit pull requests.

License
(Add your chosen license here, e.g., MIT, Apache 2.0)

I hope this README.md helps you document your project effectively! Let me know if you'd like any specific sections expanded or adjusted.