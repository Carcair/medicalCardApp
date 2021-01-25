package com.emird.medicalCardApp.controllers;

import com.emird.medicalCardApp.models.Patient;
import com.emird.medicalCardApp.service.PatientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
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
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PatientControllerTest {
	@MockBean
	private PatientService patientService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("Get all patients | Success")
	@WithMockUser
	void getAllPatients() throws Exception {
		// Mock service
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
		Mockito.doReturn(Lists.newArrayList(patient1, patient2)).when(patientService).getAllPatients();

		// Get response
		mockMvc.perform(get("/api/v1/patient"))
			// Validate response code and content type
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	@DisplayName("Get one patient | Success")
	@WithMockUser
	void getOnePatient() throws Exception {
		// Mock service
		Patient patient = new Patient(
			1l,
			"Emir",
			"Delić",
			"0123452312",
			"Neka adresa",
			"0102020202",
			LocalDateTime.now()
		);
		Mockito.doReturn(Optional.of(patient)).when(patientService).getOnePatient(1l);

		// Get request
		mockMvc.perform(get("/api/v1/patient/{patientId}", 1L))
			// Validate response
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	@DisplayName("Post new patient | Success")
	@WithMockUser
	void addNewPatient() throws Exception {
		// Mock service
		Patient patient = new Patient(
			1l,
			"Emir",
			"Delić",
			"0123452312",
			"Neka adresa",
			"0102020202"
		);

		// Post request
		mockMvc.perform(post("/api/v1/patient")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(patient)))
			.andExpect(status().isOk());
	}

	@Test
	@DisplayName("Edit patient | Success")
	@WithMockUser
	void updatePatient() throws Exception {
		// Mock service
		Patient patient = new Patient(
			1l,
			"Emir",
			"Delić",
			"0123452312",
			"Neka adresa",
			"0102020202"
		);

		// Post request
		mockMvc.perform(put("/api/v1/patient")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(patient))
			)
			.andExpect(status().isOk());
	}
}