package com.user_dm.UM.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user_dm.UM.entities.Users;


public interface UserRepository extends JpaRepository<Users, String> {

	Optional<Users> findByUserId(String userId);

	Optional<List<Users>> findByNameOrUserId(String name, String userId);

	Optional<Users> findByAdhaarCardOrPanCard(String adhaarCard, String panCard);
}
