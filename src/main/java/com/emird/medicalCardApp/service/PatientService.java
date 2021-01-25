package com.emird.medicalCardApp.service;

import com.emird.medicalCardApp.models.Patient;
import com.emird.medicalCardApp.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
	@Autowired
	private PatientRepository patientRepository;

	// Get all patients
	public List<Patient> getAllPatients() {
		return patientRepository.findAll();
	}

	// Get one patient
	public Optional<Patient> getOnePatient(Long patientId) {
		return patientRepository.findById(patientId);
	}

	// Create a patient
	public void addNewPatient(Patient patient) {
		patientRepository.save(patient);
	}

	// Edit a patient
	public void updatePatient(Patient patient) {
		patientRepository.save(patient);
	}

	// Delete a patient
	public void deleteOnePatient(long patientId) {
		patientRepository.deleteById(patientId);
	}
}
