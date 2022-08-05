package com.training.sprint1.services;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.training.sprint1.entities.User;
import com.training.sprint1.exceptions.UserNotFoundException;
import com.training.sprint1.repo.IUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	IUserRepository iUserRepository;	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user1;
		try {
				user1=iUserRepository.findByUserName(username);
		}
		catch(Exception e)
		{
			throw new UsernameNotFoundException("user not found");
		}
		return new org.springframework.security.core.userdetails.User(user1.getUserName(), user1.getPassword(), new ArrayList<>());
	}

}
