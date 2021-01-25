package com.emird.medicalCardApp.service;

import com.emird.medicalCardApp.models.Patient;
import com.emird.medicalCardApp.repositories.PatientRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PatientServiceTest {
	@Autowired
	private PatientService patientService;

	@MockBean
	private PatientRepository patientRepository;

	@Test
	@DisplayName("Testing getAllPatients service")
	void getAllPatients() {
		// Set mock repo
		Patient patient1 = new Patient(
			1l,
			"Emir",
			"Delić",
			"0123452312",
			"Neka adresa",
			"0102020202",
			LocalDateTime.now()
		);
		Patient patient2 = new Patient(
			2l,
			"Niko",
			"Nikoc",
			"0123452312",
			"Neka adresa",
			"0102020202",
			LocalDateTime.now()
		);
		Mockito.doReturn(Arrays.asList(patient1, patient2)).when(patientRepository).findAll();

		// Execute sevice method
		List<Patient> patients = patientService.getAllPatients();

		// Assert response
		assertEquals(2, patients.size(), "findAll should find and return 2 patients");
	}

	@Nested
	@DisplayName("Testing getOnePatientById")
	class getPatient {
		@Test
		@DisplayName("Get one patient | Success")
		void getOnePatient() {
			// Mock repo
			Patient patient = new Patient(
				1l,
				"Emir",
				"Delić",
				"0123452312",
				"Neka adresa",
				"0102020202",
				LocalDateTime.now()
			);
			Mockito.doReturn(Optional.of(patient)).when(patientRepository).findById(1l);

			// Service method
			Optional<Patient> returnedPatient = patientService.getOnePatient(1L);

			// Assert response
			assertEquals(returnedPatient, Optional.of(patient), "Patients are not the same");
		}

		@Test
		@DisplayName("Get one patient | Not found")
		void getOnePatientNotFound() {
			// Mock repo
			Mockito.doReturn(Optional.empty()).when(patientRepository).findById(1l);

			// Service method
			Optional<Patient> returnedPatient = patientService.getOnePatient(1l);

			// Assert response
			assertTrue(returnedPatient.isEmpty(), "This patient shouldn't exist");
		}
	}

	// Add, Update and Delete services don't have return values
	// and won't test them
}