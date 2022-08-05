package com.training.sprint1;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.training.sprint1.entities.Account;
import com.training.sprint1.entities.Admin;
import com.training.sprint1.entities.Gender;
import com.training.sprint1.entities.Role;
import com.training.sprint1.exceptions.AdminNotFoundException;
import com.training.sprint1.repo.IAdminRepository;
import com.training.sprint1.services.AdminServiceImpl;

class AdminTests {

	//@Test
	//void test() {fail("Not yet implemented");}

	
	@Mock
	IAdminRepository admRepo;
	
	@InjectMocks
	AdminServiceImpl admService;
	
	Admin ad1,ad2,ad3,ad4;
	Account a;
	List<Admin> admins = new ArrayList<Admin>();
	List<Admin> radmins = new ArrayList<Admin>();
	List<Admin> temp = new ArrayList<Admin>();
	Set<Account> accounts = new HashSet<Account>();
	
	
	@BeforeEach
	public void setAddAdmin()
	{
		MockitoAnnotations.initMocks(this);

		a = new Account(200L,2.0,10000.0,LocalDateTime.of(2021, 9, 12, 12, 0));	
		accounts.add(a);
		ad1 = new Admin(111L,"Jaya","9912345678","js@gmail.com","112000",22,Gender.FEMALE,new HashSet<Role>());
		ad2 = new Admin(111L,"Jaya","9912340008","js@gmail.com","112000",22,Gender.FEMALE,new HashSet<Role>());

	}
	
	
	@BeforeEach
	public void setListAllCustomer()
	{
		MockitoAnnotations.initMocks(this);
		ad1 = new Admin(1011L,"Jaya","99123456788","js@gmail.com","112000",22,Gender.FEMALE,new HashSet<Role>());
		ad2 = new Admin(1011L,"seema","9912345228","jsw@gmail.com","11y000",32,Gender.FEMALE,new HashSet<Role>());
		ad3 = new Admin(1013L,"Alove","9912345338","jsf@gmail.com","11j000",28,Gender.MALE,new HashSet<Role>());
		ad4 = new Admin(1014L,"shri","9123456550","jsg@gmail.com","11k000",53,Gender.FEMALE,new HashSet<Role>());
		 admins.add(ad1);
		 admins.add(ad2);
		 admins.add(ad3);
		 admins.add(ad4);
	}

	@Test
	void testaddAdmin() {
		when(admRepo.save(ad1)).thenReturn(ad1);
		Assertions.assertEquals(ad1,admService.addAdmin(ad1));
	}
	
	
	// doubt
	@Test
	void testFindAdminById() throws AdminNotFoundException
	{
		
        when(admRepo.findById(ad1.getUserId())).thenReturn(Optional.of(ad1));
        Assertions.assertEquals(ad1,admService.findAdminById(ad1.getUserId()));
	}
	
	@Test
	void testListAllAdmin() throws AdminNotFoundException
	{
		when(admRepo.findAll()).thenReturn(admins);
		Assertions.assertEquals(4,admins.size());		
	
	}
	
	@Test
	void updateAdmin() throws AdminNotFoundException
	{
		when(admRepo.findById(ad1.getUserId())).thenReturn(Optional.of(ad1));
		when(admRepo.save(ad1)).thenReturn(ad2);
		System.out.println(ad1);	
		System.out.println(ad2);	
		Assertions.assertEquals(ad2,admService.updateAdmin(ad2, ad1.getUserId()));
			}
		
	@Test
	void testremoveAdmin() throws AdminNotFoundException
	{
		when(admRepo.findById(ad1.getUserId())).thenReturn(Optional.of(ad1));
		Assertions.assertEquals(true,admService.removeAdmin(ad1.getUserId()));
	}



}
