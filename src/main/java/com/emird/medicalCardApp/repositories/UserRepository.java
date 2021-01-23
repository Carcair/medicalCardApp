package com.emird.medicalCardApp.repositories;

import com.emird.medicalCardApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	public User findByUsername(String username);
}
