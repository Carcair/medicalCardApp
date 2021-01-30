package com.emird.medicalCardApp.repositories;

import com.emird.medicalCardApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	// Users don't have number ID, but unique usernames
	public User findByUsername(String username);
}
