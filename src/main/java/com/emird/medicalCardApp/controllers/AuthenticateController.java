package com.emird.medicalCardApp.controllers;

import com.emird.medicalCardApp.models.AuthenticationRequest;
import com.emird.medicalCardApp.models.AuthenticationResponse;
import com.emird.medicalCardApp.service.MyUserDetailsService;
import com.emird.medicalCardApp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authenticate")
public class AuthenticateController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;


	@GetMapping
	public String login() {
		return "Welcome to the authenticate page.";
	}

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
			authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}
