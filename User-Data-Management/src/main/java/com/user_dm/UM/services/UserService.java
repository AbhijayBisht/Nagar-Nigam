package com.user_dm.UM.services;

import java.util.List;

import com.user_dm.UM.dto.UserDto;

public interface UserService {

	// for creating User data with unique ID generation ( create new User )
	public UserDto createUser(UserDto userDto);
	
	// list all users data ------------ for admin and super-admin
	public List<UserDto> getUser();

	// user data by user_id/name ------------ for admin and super-admin
	public List<UserDto> getUserByNameOrUserId(String name, String userId); 
	
	// user data by adhaarCard/PanCard ------------ for admin and super-admin
	public UserDto getUserByAdhaarCardOrPanCard(String adhaarCard, String panCard);
	
	// editing property details for user_id
	public UserDto editUser(UserDto dto, String userId);
	
	//deleting property details by user_id
	public void deleteUser(String userId);
}
