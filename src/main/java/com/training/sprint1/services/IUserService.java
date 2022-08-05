package com.training.sprint1.services;

import org.springframework.stereotype.Service;

import com.training.sprint1.entities.User;
import com.training.sprint1.exceptions.UserNotFoundException;

@Service
public interface IUserService {
	public User addNewUser(User user);
	
	public User signIn(User user);
	
	public User signOut(User user);
	
	public User updateUserInfo(User user, Long userId) throws UserNotFoundException;
	
	public boolean deleteUserInfo(Long userId) throws UserNotFoundException;
}
