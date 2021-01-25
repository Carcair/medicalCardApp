# Medical Card Application

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