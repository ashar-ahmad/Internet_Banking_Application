package com.training.sprint1;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.training.sprint1.entities.Gender;
import com.training.sprint1.entities.Role;
import com.training.sprint1.entities.User;
import com.training.sprint1.exceptions.UserNotFoundException;
import com.training.sprint1.repo.ICustomerRepository;
import com.training.sprint1.repo.IUserRepository;
import com.training.sprint1.services.CustomerServiceImpl;
import com.training.sprint1.services.UserServiceImpl;

class UserTests {
	
	@Mock
	IUserRepository repo;
	
	@InjectMocks
	UserServiceImpl service;
	
	User user1, user2;
	
	@BeforeEach
	public void setAddNewUser() {
		MockitoAnnotations.initMocks(this);
		user1 = new User(111L, "Prashant", "8493483594", "prashant@gmail.com", "12345", 21, Gender.MALE,new HashSet<Role>());		
		user2 = new User(111L, "Shubham", "8493483594", "prashant@gmail.com", "12345", 21, Gender.FEMALE,new HashSet<Role>());	
	}

	@Test
	void testAddNewUser() {
		when(repo.save(user1)).thenReturn(user1);
		Assertions.assertEquals(user1, service.addNewUser(user1));
	}
	
	@Test
	void testUpdateUserInfo() throws UserNotFoundException {
		when(repo.findById(user1.getUserId())).thenReturn(Optional.of(user1));
		when(repo.save(user1)).thenReturn(user2);
		Assertions.assertEquals(user2, service.updateUserInfo(user2, user1.getUserId()));
	}
	
	@Test
	void testDeleteUserInfo() throws UserNotFoundException {
		when(repo.findById(user1.getUserId())).thenReturn(Optional.of(user1));
		Assertions.assertEquals(true, service.deleteUserInfo(user1.getUserId()));
	}

}
