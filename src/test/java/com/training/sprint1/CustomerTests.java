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
import com.training.sprint1.entities.Customer;
import com.training.sprint1.entities.Gender;
import com.training.sprint1.entities.SavingAccount;
import com.training.sprint1.entities.User;
import com.training.sprint1.exceptions.CustomerNotFoundException;
import com.training.sprint1.repo.ICustomerRepository;
import com.training.sprint1.services.CustomerServiceImpl;

class CustomerTests {
	
	@Mock
	ICustomerRepository repo;
	
	@InjectMocks
	CustomerServiceImpl service;
	
	Customer c1,c2,c3,c4;
	SavingAccount a;
	List<Customer> customers = new ArrayList<Customer>();
	Set<SavingAccount> accounts = new HashSet<SavingAccount>();
	
	
	
	@BeforeEach
	public void setaddCustomer()
	{
		MockitoAnnotations.initMocks(this);
		a = new SavingAccount(200L,2.0,10000.0,LocalDateTime.of(2021, 9, 12, 12, 0));
		accounts.add(a);
		c1 = new Customer(111L,"Ram","9971221221","ramss@gmail.com","abcdef",22,Gender.MALE,accounts,"Dee1212");

	}
	
	
	@BeforeEach
	public void setListAllCustomer()
	{
		MockitoAnnotations.initMocks(this);
		c1 = new Customer(111L,"Ram","9971221221","ramss@gmail.com","abcdef",22,Gender.MALE,accounts,"1213d");
		c2 = new Customer(111L,"Shaam","9861221221","shyy123@gmail.com","theqe21",30,Gender.MALE,accounts,"debv1");
		c3 = new Customer(113L,"Suresh","9094228221","suresh112@gmail.com","abcdef",29,Gender.MALE,accounts,"sqe");
		c4 = new Customer(114L,"Seema","9173451221","seemasingh1@gmail.com","abcdef",22,Gender.FEMALE,accounts,"eqwqe");
		customers.add(c1);
		customers.add(c2);
		customers.add(c3);
	}

	@Test
	void testaddCustomer() {
		when(repo.save(c1)).thenReturn(c1);
		Assertions.assertEquals(c1,service.addCustomer(c1));
	}
	
	
	@Test
	void testListAllCustomers()
	{
		
        when(repo.findAll()).thenReturn(customers);
        Assertions.assertEquals(3,customers.size());
	}
	
	@Test
	void testUpdateCustomer() throws CustomerNotFoundException
	{
		when(repo.findById(c1.getUserId())).thenReturn(Optional.of(c1));
		when(repo.save(c1)).thenReturn(c2);
		Assertions.assertEquals(c2, service.updateCustomer(c2, c1.getUserId()));
	}
	
	@Test
	void testGetCustomerbyId() throws CustomerNotFoundException
	{
		when(repo.findById(c1.getUserId())).thenReturn(Optional.of(c1));
		Assertions.assertEquals(c1,service.findCustomerById(c1.getUserId()));
	}

	@Test
	void testDeleteCustomer() throws CustomerNotFoundException
	{
		when(repo.findById(c1.getUserId())).thenReturn(Optional.of(c1));
		Assertions.assertEquals(true,service.deleteCustomer(c1.getUserId()));
	}
}
