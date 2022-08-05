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
import com.training.sprint1.entities.Transaction;
import com.training.sprint1.entities.TransactionStatus;
import com.training.sprint1.entities.TransactionType;
import com.training.sprint1.entities.User;
import com.training.sprint1.exceptions.AccountDoesNotExistException;
import com.training.sprint1.exceptions.InvalidTransactionIDException;
import com.training.sprint1.exceptions.TransactionDoesNotExistException;
import com.training.sprint1.repo.IAccountRepository;
import com.training.sprint1.repo.ITransactionRepository;
import com.training.sprint1.services.AccountServiceImpl;
import com.training.sprint1.services.TransactionServiceImpl;

class TransactionTests {
	
	@Mock
	ITransactionRepository repo;
	
//	@Mock
//	IAccountRepository  accRepo;
	
	@InjectMocks
	TransactionServiceImpl service;
	
//	@InjectMocks
//	AccountServiceImpl accService;
	
	Transaction c1,c2,c3,c4;
	Account a;
	List<Transaction> customers = new ArrayList<Transaction>();
	List<Transaction> rcustomers = new ArrayList<Transaction>();
	List<Transaction> temp = new ArrayList<Transaction>();
	Set<Account> accounts = new HashSet<Account>();
	
	
	@BeforeEach
	public void setaddTransaction()
	{
		MockitoAnnotations.initMocks(this);
		a = new Account(200L,2.0,10000.0, LocalDateTime.of(2021, 9, 12, 12, 0));
		accounts.add(a);
		c1 = new Transaction(12345678L,1000.0,TransactionType.DEPOSIT, LocalDateTime.of(2021, 9, 12, 12, 0),
				TransactionStatus.FAILED, "Your transaction has failed");

	}
	
	
	@BeforeEach
	public void setListTransaction()
	{
		MockitoAnnotations.initMocks(this);
		c1 = new Transaction(12345678L,1000.0,TransactionType.DEPOSIT, LocalDateTime.of(2021, 9, 12, 12, 0),TransactionStatus.FAILED, "Your transaction has failed");
		c2 = new Transaction(11243453L,1000.0,TransactionType.WITHDRAW, LocalDateTime.of(2021, 9, 12, 12, 0),TransactionStatus.SUCCESSFUL, "Your transaction is successful");
		c3 = new Transaction(12443445L,1000.0,TransactionType.DEPOSIT, LocalDateTime.of(2021, 9, 12, 12, 0),TransactionStatus.FAILED, "Your transaction has failed");
		c4 = new Transaction(12345650L,1000.0,TransactionType.TRANSFER, LocalDateTime.of(2021, 9, 12, 12, 0),TransactionStatus.FAILED, "Your transaction has failed");
		c1.setBankAccount(a);
		c2.setBankAccount(a);
		c3.setBankAccount(a);
		c4.setBankAccount(a);
		customers.add(c1);
		customers.add(c2);
		customers.add(c3);
		customers.add(c4);
	}

	@Test
	void testCreateTransaction() {
		when(repo.save(c1)).thenReturn(c1);
		Assertions.assertEquals(c1,service.createTransaction(c1));
	}
	
	@Test
	void testfindTransactionById() throws InvalidTransactionIDException {
		when(repo.findById(c1.getTransactionId())).thenReturn(Optional.of(c1));
		Assertions.assertEquals(c1,service.findTransactionById(c1.getTransactionId()));
	}
	
	
	// doubt
	@Test
	void testListAllTransactions() throws AccountDoesNotExistException, TransactionDoesNotExistException
	{
		
        when(repo.findByTransactionDateTimeBetweenAndBankAccount(a.getDateOfOpening(),LocalDateTime.now(),a)).thenReturn(new ArrayList<Transaction>(customers));
        Assertions.assertEquals(4,customers.size());
	}
	
	@Test
	void testgetAllMyAccTransactions() throws AccountDoesNotExistException {
		//when(accRepo.findByAccountId(a.getAccountId()).get().getTransactions()).thenReturn(new HashSet<Transaction>(customers));
		//Assertions.assertEquals(4,service.getAllMyAccTransactions(a.getAccountId()).size());		
		when(a.getTransactions()).thenReturn(new ArrayList<Transaction>(customers));
        Assertions.assertEquals(4,customers.size());
	}
	

}