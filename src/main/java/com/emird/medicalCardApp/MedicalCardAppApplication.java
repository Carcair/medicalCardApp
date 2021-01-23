package com.emird.medicalCardApp;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MedicalCardAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalCardAppApplication.class, args);

		// Flyway configuration
		Flyway flyway = Flyway
			.configure()
			.dataSource("jdbc:mysql://localhost:3306/medical_card_app?currentSchema=records", "dev", "password")
			.load();

		// Initiate migration
		flyway.migrate();
	}

}
