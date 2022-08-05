//package com.training.sprint1.controllers;
//
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.jayway.jsonpath.internal.Path;
//import com.training.sprint1.entities.Account;
//import com.training.sprint1.entities.AccountType;
//import com.training.sprint1.entities.SavingAccount;
//import com.training.sprint1.entities.TermAccount;
//import com.training.sprint1.entities.Transaction;
//import com.training.sprint1.exceptions.AccountDoesNotExistException;
//import com.training.sprint1.exceptions.CustomerDoesNotExistException;
//import com.training.sprint1.exceptions.InvalidCredentialsException;
//import com.training.sprint1.exceptions.LessBalanceException;
//import com.training.sprint1.exceptions.TermPeriodNotFinishedException;
//import com.training.sprint1.services.AccountServiceImpl;
//
//@RestController
//@RequestMapping("/api/admin/account")
//public class AccountController{
//	
//	@Autowired
//	AccountServiceImpl accountServiceImpl;
//	
//	@PostMapping("/saving")
//	public ResponseEntity<SavingAccount> addSavingAccount(@RequestBody SavingAccount sAcc)
//	{
//		return new ResponseEntity<SavingAccount>(accountServiceImpl.addSavingsAccount(sAcc),HttpStatus.OK);
//	}
//	
//	@PostMapping("/term")
//	public ResponseEntity<TermAccount> addTermAccount(@RequestBody TermAccount tAcc)
//	{
//		return new ResponseEntity<TermAccount>(accountServiceImpl.addTermAccount(tAcc),HttpStatus.OK);
//	}
//	
//	@GetMapping("/listOfAccounts/{id}")
//	public ResponseEntity<Set<Account>> viewAccounts(@PathVariable Long customerId) throws CustomerDoesNotExistException
//	{
//		return new ResponseEntity<Set<Account>>(accountServiceImpl.viewAccounts(customerId),HttpStatus.OK);
//	}
//	
//	@GetMapping("/listOfSavingAccounts/{id}")	
//	public ResponseEntity<Set<Account>> viewSavingsAcc(@PathVariable Long customerId)
//	{
//		return new ResponseEntity<Set<Account>>(accountServiceImpl.viewSavingsAcc(customerId),HttpStatus.OK);
//	}
//	
//	@GetMapping("/listOfTermAccounts/{id}")	
//	public ResponseEntity<Set<Account>> viewTermAcc(@PathVariable Long customerId)
//	{
//		return new ResponseEntity<Set<Account>>(accountServiceImpl.viewTermAcc(customerId),HttpStatus.OK);
//	}
//	
//	@GetMapping("/accountbyid/{id}")
//	public ResponseEntity<Account> findAccountById(@PathVariable Long id) throws AccountDoesNotExistException
//	{
//		return new ResponseEntity<Account>(accountServiceImpl.findAccountById(id),HttpStatus.OK);
//	}
//	
//	@DeleteMapping("closeSavingAccount/{id}")
//	public ResponseEntity<Boolean> closeSavingsAccount(@PathVariable Long id) throws AccountDoesNotExistException
//	{
//		return new ResponseEntity<Boolean>(accountServiceImpl.closeSavingsAccount(id),HttpStatus.OK);
//	}
//	
//	@DeleteMapping("closeTermAccount/{id}")
//	public ResponseEntity<Boolean> closeTermAccount(@PathVariable Long id) throws AccountDoesNotExistException
//	{
//		return new ResponseEntity<Boolean>(accountServiceImpl.closeTermAccount(id),HttpStatus.OK);
//	}
//	
//	@PutMapping("/updateSavingAccount/{id}")
//	public ResponseEntity<SavingAccount> updateSavingsAccount(@PathVariable Long id,@RequestBody SavingAccount sAcc) throws AccountDoesNotExistException
//	{
//		return new ResponseEntity<SavingAccount>(accountServiceImpl.updateSavingsAccount(id,sAcc),HttpStatus.OK);
//	}	
//	
//	@PutMapping("/updateTermAccount/{id}")
//	public ResponseEntity<TermAccount> updateTermAccount(@PathVariable Long id,@RequestBody TermAccount sAcc) throws AccountDoesNotExistException
//	{
//		return new ResponseEntity<TermAccount>(accountServiceImpl.updateTermAccount(id,sAcc),HttpStatus.OK);
//	}	
//	
//	@PatchMapping("/deposit/{id}{amount}")
//	public ResponseEntity<Transaction> deposit(@PathVariable Long id,@PathVariable Double amount) throws AccountDoesNotExistException
//	{
//		return new ResponseEntity<Transaction>(accountServiceImpl.deposit(id, amount),HttpStatus.OK);
//	}
//	
//	@PatchMapping("/widhraw/{id}{accountType}{amount}")
//	public ResponseEntity<Transaction> withdraw(@PathVariable Long id,@PathVariable AccountType accountType,@PathVariable Double amount) throws AccountDoesNotExistException, LessBalanceException, TermPeriodNotFinishedException
//	{
//		return new ResponseEntity<Transaction>(accountServiceImpl.withdraw(id, accountType, amount),HttpStatus.OK);
//	}
//	
//	@PatchMapping("/transfer/{sendId}{recId}{amount}")
//	public ResponseEntity<Transaction> transfer(@PathVariable Long sendId,@PathVariable Long recId,@PathVariable Double amount) throws AccountDoesNotExistException, LessBalanceException
//	{
//		return new ResponseEntity<Transaction>(accountServiceImpl.transferMoney(sendId, recId, amount),HttpStatus.OK);
//	}
//}
