package com.emird.medicalCardApp.controllers;

import com.emird.medicalCardApp.models.Diagnosis;
import com.emird.medicalCardApp.service.DiagnosisService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DiagnosisControllerTest {
	@MockBean
	private DiagnosisService diagnosisService;

	@Autowired
	private MockMvc mockMvc;

	@Nested
	@DisplayName("Get method test")
	class getAllDiagnosisTests {

		// Route is blocked with failed JWT authentication
		@Test
		@DisplayName("Testing get Diagnosis route | Blocked")
		void getAllDiagnosis() throws Exception {
			// Set mock service
			Diagnosis diagnosis = new Diagnosis(
				1l,
				"Glavobolja",
				"Aspirin",
				LocalDateTime.now()
			);
			Mockito
				.doReturn(Arrays.asList(diagnosis))
				.when(diagnosisService).getAllDiagnosisByPatientId(1l);

			// Execute get request
			mockMvc.perform(get("/api/v1/diagnosis/{patientId}", 1L))
				.andExpect(status().isForbidden());
		}

		// We create mock user to avoid authentication block
		@Test
		@DisplayName("Testing get Diagnosis route | Success")
		@WithMockUser
		void getAllDiagnosisSuccess() throws Exception {
			// Set mock service
			Diagnosis diagnosis = new Diagnosis(
				1l,
				"Glavobolja",
				"Aspirin",
				LocalDateTime.now()
			);
			Mockito
				.doReturn(Arrays.asList())
				.when(diagnosisService).getAllDiagnosisByPatientId(1l);

			// Execute get request
			mockMvc.perform(get("/api/v1/diagnosis/{patientId}", 1L))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		}
	}

	@Nested
	@DisplayName("Post method test")
	class addNewDiagnosisClass {

		// Blocked access
		@Test
		@DisplayName("Testing post Diagnosis route | Blocked")
		void addNewDiagnosis() throws Exception {
			// Set mock service
			Diagnosis sentDiagnosis = new Diagnosis(
				1,
				"Glavobolja",
				"Aspirin",
				LocalDateTime.now()
			);

			// Execute POST request
			mockMvc
				.perform(
					post("/api/v1/diagnosis")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(sentDiagnosis))
				)
				.andExpect(status().isForbidden());
			;
		}

		// Allowed access
		@Test
		@DisplayName("Testing post Diagnosis route | Success")
		@WithMockUser
		void addNewDiagnosisSuccess() throws Exception {
			// Set mock service
			Diagnosis sentDiagnosis = new Diagnosis(
				1,
				"Glavobolja",
				"Aspirin"
			);

			// Execute POST request
			mockMvc
				.perform(
					post("/api/v1/diagnosis")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(sentDiagnosis))
				)
				.andExpect(status().isOk());
			;
		}
	}
}