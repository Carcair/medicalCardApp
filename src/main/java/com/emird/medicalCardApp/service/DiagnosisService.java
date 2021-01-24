package com.emird.medicalCardApp.service;

import com.emird.medicalCardApp.models.Diagnosis;
import com.emird.medicalCardApp.repositories.DiagnosisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosisService {
	@Autowired
	private DiagnosisRepository diagnosisRepository;

	// Get all diagnosis by patientId
	public List<Diagnosis> getAllDiagnosisByPatientId(long patientId) {
		return diagnosisRepository.findByPatientId(patientId);
	}


	// Create new diagnosis for patient
	public void  addNewDiagnosis(Diagnosis diagnosis) {
		diagnosisRepository.save(diagnosis);
	}
}
