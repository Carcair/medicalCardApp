package com.emird.medicalCardApp.util;

import org.springframework.stereotype.Service;
import springfox.documentation.service.ApiInfo;

import java.util.Collections;

@Service
public class SpringfoxUtil {
	public ApiInfo apiDetails() {
		return new ApiInfo(
			"Medical Card APP",
			"Api Documentation for CRUD operations on Patients visited and their Diagnosis.",
			"0.0.1",
			"Needed credentials to use successful queries (Default, username: 'user', password: 'user')",
			new springfox.documentation.service.Contact("Delić Emir", "", "delic.emir90@gmail.com"),
			"",
			"",
			Collections.emptyList()
		);
	}
}
