package com.emird.medicalCardApp.service;

import com.emird.medicalCardApp.models.User;
import com.emird.medicalCardApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);

		// I need spring securities User class, didn't have foresight to name it differently
		return new org.springframework.security.core.userdetails.User(
			user.getUsername(),
			user.getPassword(),
			new ArrayList<>()
		);
	}
}
