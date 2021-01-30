package com.emird.medicalCardApp.controllers;

import com.emird.medicalCardApp.models.Diagnosis;
import com.emird.medicalCardApp.service.DiagnosisService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/diagnosis")
@CrossOrigin(origins = "*")
public class DiagnosisController {
	@Autowired
	private DiagnosisService diagnosisService;

	// Get all diagnosis from one patient
	@GetMapping("/{patientId}")
	@ApiOperation(
		value = "Get all diagnosis from required patient",
		notes = "Token Needed"
	)
	public List<Diagnosis> getAllDiagnosis(
		@ApiParam( value = "patientId value for wanted patient", required = true)
		@PathVariable long patientId
	) {
		return diagnosisService.getAllDiagnosisByPatientId(patientId);
	}

	// Post new diagnosis for a certain patient
	@PostMapping
	@ApiOperation(
		value = "Add a new diagnosis",
		notes = "Token Needed"
	)
	public void addNewDiagnosis(@RequestBody Diagnosis diagnosis) {
		diagnosisService.addNewDiagnosis(diagnosis);
	}

	// Blocked options for editing and deleting diagnosis
	// We will filter diagnosis by date for a patient in user interface
	// Limit need for sending requests for data
}
