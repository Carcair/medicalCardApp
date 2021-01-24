package com.emird.medicalCardApp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/diagnosis")
public class DiagnosisController {

	// Get all diagnosis from one patient
	@GetMapping
	public String getDiagnosis() {
		return "This will return all diagnosis from certain patients.";
	}

	// Post new diagnosis for a certain patient

	// Blocked options for editing and deleting diagnosis
	// We will filter diagnosis by date for a patient in user interface if necessary
	// Limit need for sending requests for data
}
