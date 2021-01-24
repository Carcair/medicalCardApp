package com.emird.medicalCardApp.repositories;

import com.emird.medicalCardApp.models.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {
	public List<Diagnosis> findByPatientId(Long patientId);
}
