# Medical Card Application - Backend Service

## Project description
Demo project for interview test, which encapsuletes necessary technologies.

Application is a demonstration of possible overview of patients, patient card creation, diagnosis creation by their doctors.

Project emphasis is on the required technologies.

## Tools and Technologies

List of required tools and technologies:
- Java,
- Maven,
- Spring Framwork (SpringBoot, SpringData, SpringSecurity),
    - used SpringSecurity JWT solution
- Angular (Angular Material, and Angular Flex-Layout),
- MySQL (Flyway for migrations),
- Swagger,
- Unit tests (used jUnit).

## Installation and APP start
Project doesn't have standalone MySQL database. To properly test, create in your 
MySQL database "medical_card_app", 
and user (username: "dev", password: "password") with access to it.

Clean and install project:
```
mvn clean install
```

Test project (runs a jUnit5 test, using Surefire plugin):
```
mvn test
```

Run application with:
```
mvn exec:java -Dexec.mainClass="com.emird.medicalCardApp.MedicalCardAppApplication"
```

Endpoint and route testing can be done with Postman. API documentation at http://localhost:8080/swagger-ui.html#/
