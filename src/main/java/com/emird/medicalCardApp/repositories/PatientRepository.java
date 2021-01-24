package com.emird.medicalCardApp.repositories;


import com.emird.medicalCardApp.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
