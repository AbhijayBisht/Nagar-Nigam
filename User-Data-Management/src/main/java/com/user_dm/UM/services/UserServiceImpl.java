package com.user_dm.UM.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user_dm.UM.dto.PropertyDto;
import com.user_dm.UM.dto.TaxDto;
import com.user_dm.UM.dto.UserDto;
import com.user_dm.UM.entities.Users;
import com.user_dm.UM.exceptions.ResourceNotFoundException;
import com.user_dm.UM.externalService.Property_Management_Service;
import com.user_dm.UM.externalService.Tax_Management_Service;
import com.user_dm.UM.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modalMapper;
	
	@Autowired
	private Property_Management_Service property_service;
	
	@Autowired
	private Tax_Management_Service tax_service;
	
	private Logger logger = LoggerFactory.getLogger(UserService.class); 
	
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		Users users = this.userDtoToUsers(userDto);
		Users saveUsers = this.userRepository.save(users);

		return this.usersTouserDto(saveUsers);
	}
	

	@Override
	public List<UserDto> getUser() {
		
		List<Users> users = this.userRepository.findAll();
		
		//Hotel hotel = hotelService.getHotel(rating.getHotelId());

		List<UserDto> userDtos = getAllPropertyAndTaxInfo(users);
		
		return userDtos;
	}
	

	@Override
	public List<UserDto> getUserByNameOrUserId(String name, String userId) {
		
		List<Users> users = this.userRepository.findByNameOrUserId(name, userId)
								.orElseThrow(
								() -> new ResourceNotFoundException("User Not found by this ID", " User_Id ", userId));
		
		List<UserDto> dtos = getAllPropertyAndTaxInfo(users);
		
		return dtos;
	}


	@Override
	public UserDto getUserByAdhaarCardOrPanCard(String adhaarCard, String panCard) {
		
		Users users = this.userRepository.findByAdhaarCardOrPanCard(adhaarCard, panCard)
								.orElseThrow(
								() -> new ResourceNotFoundException("User Not found by this Adhaar Card or Pan Card", " Adhaar Card ", adhaarCard));
		
		return this.usersTouserDto(users);
		
	}

	
	@Override
	public UserDto editUser(UserDto dto, String userId) {
		
		Users users = this.userRepository.findByUserId(userId)
								.orElseThrow(
								() -> new ResourceNotFoundException("User Not found by this ID", " User_Id ", userId));
		
		users.setAddress(dto.getAddress());
		users.setAdhaarCard(dto.getAdhaarCard());
		users.setAge(dto.getAge());
		users.setDob(dto.getDob());
		users.setGuardianName(dto.getGuardianName());
		users.setName(dto.getName());
		users.setPanCard(dto.getPanCard());
		users.setRole(dto.getRole());
		
		Users updatedUser = this.userRepository.save(users);
		UserDto userDto = this.usersTouserDto(updatedUser);
		
		return userDto;
	
	}


	@Override
	public void deleteUser(String userId) {

		Users users = this.userRepository.findByUserId(userId)
								 .orElseThrow(
								() -> new ResourceNotFoundException("User Not found by this ID", " User_Id ", userId));
		
		this.userRepository.delete(users);
		System.out.println("!! User deleted successfully !!");
		
	}
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//for converting userDto modal to Users entity
	public Users userDtoToUsers(UserDto userDto) {
		Users users = this.modalMapper.map(userDto, Users.class);
		return users;
	}
	
	//for converting Users entity to userDto modal
	public UserDto usersTouserDto(Users users) {
		UserDto userDto = this.modalMapper.map(users, UserDto.class);
		return userDto;
	}

	//for getting info related to property and their respective tax
	public List<UserDto> getAllPropertyAndTaxInfo(List<Users> users){
		
		List<UserDto> userDtos = users.stream().map(user -> {
					UserDto userDto = this.usersTouserDto(user);
					
					// Fetching properties for this user using property_ID
						List<PropertyDto> propertyDtos = this.property_service.getPropertyByUserId(user.getUserId());

						List<PropertyDto> list = propertyDtos.stream().map(prop -> {
								
								TaxDto taxDto = this.tax_service.getTaxById(prop.getTaxId());
								prop.setTaxesDto(taxDto);
								
							return prop;	
						}).collect(Collectors.toList());
						
						logger.info("Info of Property ID : ", propertyDtos);
						userDto.setPropertiesDto(list);	// Set properties in UserDto

						
					// Fetch tax data for each property
//				        List<TaxDto> taxDtos = propertyDtos.stream().map(property -> {
//				            return this.tax_service.getTaxById(property.getTaxId());
//				        }).collect(Collectors.toList());
//
//				        logger.info("Info of Property ID : ", taxDtos);
//				        userDto.setTaxesDto(taxDtos);  // Set taxes in UserDto	
						
					
				    return userDto;				//returning the value
			}).collect(Collectors.toList());
		
		return userDtos;
	}
	
}
