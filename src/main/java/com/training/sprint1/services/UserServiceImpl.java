package com.training.sprint1.services;

import javax.transaction.Transactional;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.sprint1.entities.User;
import com.training.sprint1.exceptions.UserNotFoundException;
import com.training.sprint1.repo.IUserRepository;

@Service
@Transactional
public class UserServiceImpl implements IUserService{
	
	@Autowired
	public IUserRepository iUserRepository;
	
	@Override
	public User addNewUser(User user) {
		
		return iUserRepository.save(user);
	}

	@Override
	public User signIn(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User signOut(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUserInfo(User user, Long userId) throws UserNotFoundException {
		
		User retrievedUser = null;
		
		try {
			retrievedUser = iUserRepository.findById(userId).orElseThrow(UserNotFoundException::new);
		}
		catch(UserNotFoundException unfe) {
			unfe.printStackTrace();
		}
		
		BeanUtils.copyProperties(user, retrievedUser, "accountId");
		
		return iUserRepository.save(retrievedUser);
	}

	@Override
	public boolean deleteUserInfo(Long userId) throws UserNotFoundException{
		User fetchedUser = iUserRepository.findById(userId).orElseThrow(UserNotFoundException::new);
		
		if(fetchedUser == null) {
			return false;
		}
		else {
			iUserRepository.delete(fetchedUser);
			return true;
		}
	}

}
