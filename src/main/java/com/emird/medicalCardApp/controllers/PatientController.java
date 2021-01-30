package com.emird.medicalCardApp.controllers;

import com.emird.medicalCardApp.models.Patient;
import com.emird.medicalCardApp.service.PatientService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patient")
@CrossOrigin(origins = "*")
public class PatientController {
	@Autowired
	private PatientService patientService;

	// Get all patients
	@GetMapping
	@ApiOperation(
		value = "Return all patients",
		notes = "Token Needed"
	)
	public List<Patient> getAllPatients() {
		return patientService.getAllPatients();
	}

	// Get one patient
	@GetMapping("/{patientId}")
	@ApiOperation(
		value = "Return a patient by its patientId",
		notes = "Token Needed"
	)
	public Patient getOnePatient(
		@ApiParam( value = "patientId value for wanted patient", required = true)
		@PathVariable long patientId
	) {
		return patientService.getOnePatient(patientId).get();
	}

	// Post new patient
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(
		value = "Insert new patient into DB",
		notes = "Token Needed"
	)
	public void addNewPatient(@ApiParam @RequestBody Patient patient) {
		patientService.addNewPatient(patient);
	}

	// Edit patient info
	// Endpoint doesn't need to know what patient will be updated
	// It recognizes it from the id provided in patient object sent with request
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(
		value = "Edit existing patient info",
		notes = "Token Needed"
	)
	public void updatePatient(@RequestBody Patient patient) {
		patientService.updatePatient(patient);
	}

	// Delete patient
	@DeleteMapping("/{patientId}")
	@ApiOperation(
		value = "Delete a patient and his diagnosis",
		notes = "Token Needed"
	)
	public void deleteOnePatient(
		@ApiParam( value = "patientId value for wanted patient", required = true)
		@PathVariable Long patientId
	) {
//		long tempId = Long.valueOf(patientId);
		patientService.deleteOnePatient(patientId);
	}
}
