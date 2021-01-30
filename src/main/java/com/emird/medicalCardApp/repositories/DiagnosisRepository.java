package com.emird.medicalCardApp.repositories;

import com.emird.medicalCardApp.models.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {
	// We are want all diagnosis for a certain patient
	public List<Diagnosis> findByPatientId(Long patientId);

	// Delete all diagnosis by patientId
	// Gets called when we delete a patient
	public void deleteAllByPatientId(Long patientId);
}
