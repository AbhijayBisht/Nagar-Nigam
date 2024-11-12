package com.auth_am.AM.entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "userInfo")
public class UserInfo {
	
	@Id
	@Column(unique = true, nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String password;
	
//	@ElementCollection
//	@Enumerated(EnumType.STRING)
//	@CollectionTable(name = "user_info_roles", joinColumns = @JoinColumn(name = "name"))
	@Column(name = "roles")
	@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private String[] roles;	// Type of User (e.g., SuperAdmin, Admin and User)
}
