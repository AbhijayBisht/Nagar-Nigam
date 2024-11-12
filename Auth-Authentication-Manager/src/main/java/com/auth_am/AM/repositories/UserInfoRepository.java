package com.auth_am.AM.repositories;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.auth_am.AM.entities.Role;
import com.auth_am.AM.entities.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, String>{

	Optional<UserInfo> findByName(String username);
	
	@Query("SELECT u.roles FROM UserInfo u WHERE u.name = :name")
	Optional<List<String>> findRolesByUsername(@Param("name") String username);				// returning set of roles for user

}
