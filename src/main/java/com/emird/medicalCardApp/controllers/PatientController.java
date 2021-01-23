package com.emird.medicalCardApp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/patient")
public class PatientController {

	@GetMapping
	public String getPatients() {
		return "This will return JSON obj of all patients";
	}
}
