package com.emird.medicalCardApp.service;

import com.emird.medicalCardApp.models.Diagnosis;
import com.emird.medicalCardApp.repositories.DiagnosisRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DiagnosisServiceTest {

	@Autowired
	private DiagnosisService diagnosisService;

	@MockBean
	private DiagnosisRepository diagnosisRepository;

	@Test
	@DisplayName("Test getAllDiagnosisByPatientId | Success")
	void getAllDiagnosisByPatientIdTest() {
		// Setup mock repository
		Diagnosis diagnosis1 = new Diagnosis(
			1l,
			"Glavobolja",
			"Aspirin",
			LocalDateTime.now()
		);
		Diagnosis diagnosis2 = new Diagnosis(
			1l,
			"Stomakobolja",
			"Čaj",
			LocalDateTime.now()
		);
		Mockito
			.doReturn(Arrays.asList(diagnosis1, diagnosis2))
			.when(diagnosisRepository).findByPatientId(1l);

		// Execute service method
		List<Diagnosis> returnedDiagnosis = diagnosisService.getAllDiagnosisByPatientId(1l);

		// Assert response
		Assertions.assertFalse(
			returnedDiagnosis.isEmpty(),
			"Diagnosis was not found"
		);
		Assertions.assertEquals(
			2,
			returnedDiagnosis.size(),
			"Find all should return 2 diagnosis"
		);
	}

	@Test
	@DisplayName("Test getAllDiagnosisByPatientId | Not Found")
	void getAllDIagnosisByPatientIdNotFoundTest() {
		// Set mock repository
		Mockito.doReturn(Arrays.asList()).when(diagnosisRepository).findByPatientId(1l);

		// Execute service method
		List<Diagnosis> returnedDiagnosis = diagnosisService.getAllDiagnosisByPatientId(1l);

		// Assert Response
		Assertions.assertTrue(
			returnedDiagnosis.isEmpty(),
			"Diagnosis should not be found."
		);
	}

	// addNewDiagnosis() has no return value
	// so we won't test it
}