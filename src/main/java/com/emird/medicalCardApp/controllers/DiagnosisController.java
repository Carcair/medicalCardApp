package com.emird.medicalCardApp.controllers;

import com.emird.medicalCardApp.models.Diagnosis;
import com.emird.medicalCardApp.service.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/diagnosis")
public class DiagnosisController {
	@Autowired
	private DiagnosisService diagnosisService;

	// Get all diagnosis from one patient
	@GetMapping("/{patientId}")
	public List<Diagnosis> getAllDiagnosis(@PathVariable long patientId) {
		return diagnosisService.getAllDiagnosisByPatientId(patientId);
	}

	// Post new diagnosis for a certain patient
	@PostMapping
	public void addNewDiagnosis(@RequestBody Diagnosis diagnosis) {
		diagnosisService.addNewDiagnosis(diagnosis);
	}

	// Blocked options for editing and deleting diagnosis
	// We will filter diagnosis by date for a patient in user interface
	// Limit need for sending requests for data
}
