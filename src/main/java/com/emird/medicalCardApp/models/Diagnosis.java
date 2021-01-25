package com.emird.medicalCardApp.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "diagnosis_info")
public class Diagnosis {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long diagnosisId;
	private long patientId;
	private String diagnosis;
	private String medicine;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
	private LocalDateTime date;

	public Diagnosis() {
		this.date = LocalDateTime.now();
	}

	// User for testing post requests

	public Diagnosis(long patientId, String diagnosis, String medicine) {
		this.patientId = patientId;
		this.diagnosis = diagnosis;
		this.medicine = medicine;
	}

	// Used for testing get requests
	public Diagnosis(long patientId, String diagnosis, String medicine, LocalDateTime date) {
		this.patientId = patientId;
		this.diagnosis = diagnosis;
		this.medicine = medicine;
		this.date = date;
	}

	public long getDiagnosisId() {
		return diagnosisId;
	}

	public void setDiagnosisId(long diagnosisId) {
		this.diagnosisId = diagnosisId;
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getMedicine() {
		return medicine;
	}

	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
}
