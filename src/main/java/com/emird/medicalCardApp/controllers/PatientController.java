package com.emird.medicalCardApp.controllers;

import com.emird.medicalCardApp.models.Patient;
import com.emird.medicalCardApp.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patient")
public class PatientController {
	@Autowired
	private PatientService patientService;

	// Get all patients
	@GetMapping
	public List<Patient> getAllPatients() {
		return patientService.getAllPatients();
	}

	// Get one patient
	@GetMapping("/{patientId}")
	public Patient getOnePatient(@PathVariable long patientId) {
		return patientService.getOnePatient(patientId).get();
	}

	// Post new patient
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public void addNewPatient(@RequestBody Patient patient) {
		patientService.addNewPatient(patient);
	}

	// Edit patient info
	// Endpoint doesn't need to know what patient will be updated
	// It recognizes it from the id provided in patient object sent with request
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public void updatePatient(@RequestBody Patient patient) {
		patientService.updatePatient(patient);
	}

	// Delete patient
	@DeleteMapping("/{patientId}")
	public void deleteOnePatient(@PathVariable long patientId) {
		patientService.deleteOnePatient(patientId);
	}
}
