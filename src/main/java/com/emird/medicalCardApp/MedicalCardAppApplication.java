package com.emird.medicalCardApp;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
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

	// Configure cors
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("/**");
			}
		};
	}
}
