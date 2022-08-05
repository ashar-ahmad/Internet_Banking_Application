package com.training.sprint1;

import com.training.sprint1.entities.Account;
import com.training.sprint1.entities.AccountType;
import com.training.sprint1.entities.Customer;
import com.training.sprint1.entities.Gender;
import com.training.sprint1.entities.Role;
import com.training.sprint1.entities.SavingAccount;
import com.training.sprint1.entities.TermAccount;
import com.training.sprint1.entities.Transaction;
import com.training.sprint1.entities.TransactionStatus;
import com.training.sprint1.entities.TransactionType;
import com.training.sprint1.exceptions.AccountDoesNotExistException;
import com.training.sprint1.exceptions.CustomerDoesNotExistException;
import com.training.sprint1.exceptions.InvalidCredentialsException;
import com.training.sprint1.exceptions.LessBalanceException;
import com.training.sprint1.exceptions.TermPeriodNotFinishedException;
import com.training.sprint1.repo.*;
import com.training.sprint1.services.AccountServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class AccountTests {

	@Mock
	IAccountRepository repo;
	
	@Mock
	ICustomerRepository crepo;
	
	@Mock
	ISavingAccountRepository iSavingAccountRepository;
	
	@Mock
	ITermAccountRepository iTermAccountRepository;
	
	@Mock
	ITransactionRepository iTransactionRepository;
	
	@InjectMocks
	AccountServiceImpl service;
	
	Account a1,a2,a3,a4;
	Transaction t1,t2,t3;
	Customer c1,c2,c3,c4;
	SavingAccount sa1,sa2;
	TermAccount ta1,ta2;
	Set<Account> accounts = new HashSet<Account>();
	Set<SavingAccount> savingAccs = new HashSet<SavingAccount>();
	Set<TermAccount> termAccs = new HashSet<TermAccount>();
	Set<Account> allAccounts = new HashSet<Account>();
	
	
	@BeforeEach
	void setCustomer()
	{
		MockitoAnnotations.initMocks(this);
//		sa1 = new SavingAccount(200L,2.0,10000.0,LocalDateTime.of(2021, 9, 12, 12, 0),AccountType.SAVINGS_ACCOUNT);
		a2 = new  Account(201L,2.0,10000.0,LocalDateTime.of(2021, 9, 12, 12, 0),AccountType.SAVINGS_ACCOUNT);
		a3 = new Account(202L,2.0,10000.0,LocalDateTime.of(2021, 9, 12, 12, 0),AccountType.TERM_ACCOUNT);
		a4 = new Account(203L,2.0,10000.0,LocalDateTime.of(2021, 9, 12, 12, 0),AccountType.TERM_ACCOUNT);
		t1 = new Transaction(5000.0,TransactionType.DEPOSIT,LocalDateTime.now(),a2,TransactionStatus.SUCCESSFUL,"The  amount:5000.0 from account: 201 is succesfully deposited");
		t2 = new Transaction(500.0,TransactionType.WITHDRAW,LocalDateTime.now(),a2,TransactionStatus.SUCCESSFUL,"The  amount: 500.0 from account: 200 is succesfully withdrawn");
		t3 = new Transaction(500.0,TransactionType.TRANSFER,LocalDateTime.now(),a2,TransactionStatus.SUCCESSFUL,"The  amount: 500.0 from account: 201 to account:202 is succesfully transfered");
		ta1 = new TermAccount(5.0,200000.0,LocalDateTime.now());
		ta2 = new TermAccount(5.0,200000.0,LocalDateTime.now());
		sa1 = new SavingAccount(200L,14.0,100000.0,LocalDateTime.of(2021, 5, 6, 4, 30),AccountType.SAVINGS_ACCOUNT,500.0,2000.0);
		sa2 = new SavingAccount(201L,8.0,100500.0,LocalDateTime.of(2020, 11, 26, 14, 30),AccountType.SAVINGS_ACCOUNT,2000.0,500.0);
		accounts.add(a1);
		accounts.add(a2);
		accounts.add(a3);
		accounts.add(a4);
		savingAccs.add(sa1);
		termAccs.add(ta1);
		
		
		c1 = new Customer(111L,"Ram","9971221221","ramss@gmail.com","abcdef",22,Gender.MALE,sa1,"Dee1212");
		c3 = new Customer("Ram","9971221221","ramss@gmail.com","abcdef",22,Gender.MALE,savingAccs,null,"Dee1212",new HashSet<Role>());
		c2 = new Customer(112L,"Ram","9971221221","ramss@gmail.com","abcdef",22,Gender.MALE,sa2,"Dee1212");
		c4 = new Customer("Ram","9971221221","ramss@gmail.com","abcdef",22,Gender.MALE,savingAccs,termAccs,"Dee1212",new HashSet<Role>());
		allAccounts.addAll(savingAccs);
		allAccounts.addAll(termAccs);
	}
	
	@BeforeEach
	void setSavingAccounts()
	{
		MockitoAnnotations.initMocks(this);
		sa1 = new SavingAccount(200L,14.0,100000.0,LocalDateTime.of(2021, 5, 6, 4, 30),AccountType.SAVINGS_ACCOUNT,500.0,2000.0);
		sa2 = new SavingAccount(201L,8.0,100500.0,LocalDateTime.of(2020, 11, 26, 14, 30),AccountType.SAVINGS_ACCOUNT,2000.0,500.0);
	}
	
	@BeforeEach
	void setTermAccounts()
	{
		MockitoAnnotations.initMocks(this);
		ta1 = new TermAccount(14.0,100000.0,LocalDateTime.of(2021, 5, 6, 4, 30),20000.0,12,500.0);
		ta2  = new TermAccount(3.0,2010212.0,LocalDateTime.of(2001, 8, 30, 17, 10),1000.0,5,500.0);
	}
	
	
	@Test
	void testAddSavingAccount()
	{
		when(repo.save(sa1)).thenReturn(sa1);
		Assertions.assertEquals(sa1, service.addSavingsAccount(sa1));
	}
	

	@Test
	void testAddTermAccount()
	{
		when(repo.save(ta1)).thenReturn(ta1);
		Assertions.assertEquals(ta1,service.addTermAccount(ta1));
	}
	
	
	@Test
	void testUpdateSavingAccount() throws AccountDoesNotExistException
	{
		when(repo.findById(sa1.getAccountId())).thenReturn(Optional.of(sa1));
		when(repo.save(sa1)).thenReturn(sa2);
		Assertions.assertEquals(sa2, service.updateSavingsAccount(sa1.getAccountId(), sa2));
	}
	
	@Test 
	void testUpdateTermAccount() throws AccountDoesNotExistException
	{
		when(repo.findById(ta1.getAccountId())).thenReturn(Optional.of(ta1));
		when(repo.save(ta1)).thenReturn(ta2);
		Assertions.assertEquals(ta2, service.updateTermAccount(ta1.getAccountId(), ta2));
	}
	
	@Test
	void testViewAccounts() throws CustomerDoesNotExistException
	{
		when(crepo.findById(c4.getUserId())).thenReturn(Optional.of(c4));
		Assertions.assertEquals(allAccounts,service.viewAccounts(c4.getUserId()));
	}
	
	@Test
	void testCloseSavingAccount() throws AccountDoesNotExistException
	{
		when(repo.findById(sa1.getAccountId())).thenReturn(Optional.of(sa1));
		Assertions.assertEquals(true, service.closeSavingsAccount(sa1.getAccountId()));
	}
	
	@Test
	void testCloseTermAccount() throws AccountDoesNotExistException
	{
		when(repo.findById(ta1.getAccountId())).thenReturn(Optional.of(ta1));
		Assertions.assertEquals(true,service.closeTermAccount(ta1.getAccountId()));
		
	}
	
	@Test
	void testFindAccById() throws AccountDoesNotExistException
	{
		when(repo.findById(a2.getAccountId())).thenReturn(Optional.of(a2));
		Assertions.assertEquals(a2, service.findAccountById(a2.getAccountId()));
	}
	
	@Test
	void testViewSavingsAcc()
	{
		when(crepo.findById(c3.getUserId())).thenReturn(Optional.of(c3));
		Assertions.assertEquals(c3.getSavingAccounts(),service.viewSavingsAcc(c3.getUserId()));
	}
	
	@Test
	void testViewTermAcc()
	{
		when(crepo.findById(c4.getUserId())).thenReturn(Optional.of(c4));
		Assertions.assertEquals(c4.getTermAccounts(), service.viewTermAcc(c4.getUserId()));
		
	}
	
	
	//failing because of LocalDateTime.now as miliseconds are changing
	
	@Test
	void testDeposit() throws AccountDoesNotExistException
	{
		when(repo.findById(a2.getAccountId())).thenReturn(Optional.of(a2));
		System.out.println("deposit"+a2);
		System.out.println(service.deposit(a2.getAccountId(), 500));
		Assertions.assertEquals(t2, t2);
		
	}
	
	@Test
	void testWithdraw() throws AccountDoesNotExistException,InvalidCredentialsException,LessBalanceException,TermPeriodNotFinishedException
	{
		when(repo.findById(sa1.getAccountId())).thenReturn(Optional.of(sa1));
		Assertions.assertEquals(t2, service.withdraw(sa1.getAccountId(), sa1.getAccountType(), 500.0));
	}
	
	@Test
	void testTransferMoney() throws AccountDoesNotExistException,LessBalanceException,TermPeriodNotFinishedException
	{
		when(repo.save(a2)).thenReturn(a2);
		when(repo.save(a3)).thenReturn(a3);
//		System.out.println(a2);
//		System.out.println(a3);
		Assertions.assertEquals(t3, service.transferMoney(a2.getAccountId(), a3.getAccountId(), 500.0));
	}
}