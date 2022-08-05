package com.training.sprint1.controllers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.training.sprint1.entities.Account;
import com.training.sprint1.entities.AccountType;
import com.training.sprint1.entities.Admin;
import com.training.sprint1.entities.Customer;
import com.training.sprint1.entities.SavingAccount;
import com.training.sprint1.entities.TermAccount;
import com.training.sprint1.entities.Transaction;
import com.training.sprint1.exceptions.AccountDoesNotExistException;
import com.training.sprint1.exceptions.AdminNotFoundException;
import com.training.sprint1.exceptions.CustomerDoesNotExistException;
import com.training.sprint1.exceptions.CustomerNotFoundException;
import com.training.sprint1.exceptions.InvalidTransactionIDException;
import com.training.sprint1.exceptions.LessBalanceException;
import com.training.sprint1.exceptions.TermPeriodNotFinishedException;
import com.training.sprint1.exceptions.TransactionDoesNotExistException;
import com.training.sprint1.services.AccountServiceImpl;
import com.training.sprint1.services.AdminServiceImpl;
import com.training.sprint1.services.CustomerServiceImpl;
import com.training.sprint1.services.TransactionServiceImpl;
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600,allowedHeaders={"Authorization","Access-Control-Request-Headers","Content-Type","Access-Control-Allow-Origin","Access-Control-Allow-Credentials","Access-Control-Allow-Headers"})
@RestController
@RequestMapping("/api/admin")
public class AdminController {		
	  
	@Autowired
	private AdminServiceImpl adminService;
	
	@Autowired
	private CustomerServiceImpl customerService;	
	
	@Autowired
	private AccountServiceImpl accountServiceImpl;
	

	@Autowired
	private TransactionServiceImpl transService;
	
	
	@PostMapping("/addSavingAcc")
	public ResponseEntity<SavingAccount> addSavingAccount(@RequestBody SavingAccount sAcc)
	{
		return new ResponseEntity<SavingAccount>(accountServiceImpl.addSavingsAccount(sAcc),HttpStatus.OK);
	}
	
	@PostMapping("/addTermAcc")
	public ResponseEntity<TermAccount> addTermAccount(@RequestBody TermAccount tAcc)
	{
		return new ResponseEntity<TermAccount>(accountServiceImpl.addTermAccount(tAcc),HttpStatus.OK);
	}
	
	@GetMapping("/listOfAccounts/{id}")
	public ResponseEntity<Set<Account>> viewAccounts(@PathVariable Long customerId) throws CustomerDoesNotExistException
	{
		return new ResponseEntity<Set<Account>>(accountServiceImpl.viewAccounts(customerId),HttpStatus.OK);
	}
	
	@GetMapping("/listOfSavingAccounts/{id}")	
	public ResponseEntity<Set<SavingAccount>> viewSavingsAcc(@PathVariable Long customerId)
	{
		return new ResponseEntity<Set<SavingAccount>>(accountServiceImpl.viewSavingsAcc(customerId),HttpStatus.OK);
	}
	
	@GetMapping("/listOfTermAccounts/{id}")	
	public ResponseEntity<Set<TermAccount>> viewTermAcc(@PathVariable Long customerId)
	{
		return new ResponseEntity<Set<TermAccount>>(accountServiceImpl.viewTermAcc(customerId),HttpStatus.OK);
	}
	
	@GetMapping("/accountbyid/{id}")
	public ResponseEntity<Account> findAccountById(@PathVariable Long id) throws AccountDoesNotExistException
	{
		return new ResponseEntity<Account>(accountServiceImpl.findAccountById(id),HttpStatus.OK);
	}
	
	@DeleteMapping("/closeSavingAccount/{id}")
	public ResponseEntity<Boolean> closeSavingsAccount(@PathVariable Long id) throws AccountDoesNotExistException
	{
		return new ResponseEntity<Boolean>(accountServiceImpl.closeSavingsAccount(id),HttpStatus.OK);
	}
	
	@DeleteMapping("/closeTermAccount/{id}")
	public ResponseEntity<Boolean> closeTermAccount(@PathVariable Long id) throws AccountDoesNotExistException
	{
		return new ResponseEntity<Boolean>(accountServiceImpl.closeTermAccount(id),HttpStatus.OK);
	}
	
	@PutMapping("/updateSavingAccount/{id}")
	public ResponseEntity<SavingAccount> updateSavingsAccount(@PathVariable Long id,@RequestBody SavingAccount sAcc) throws AccountDoesNotExistException
	{
		return new ResponseEntity<SavingAccount>(accountServiceImpl.updateSavingsAccount(id,sAcc),HttpStatus.OK);
	}	
	
	@PutMapping("/updateTermAccount/{id}")
	public ResponseEntity<TermAccount> updateTermAccount(@PathVariable Long id,@RequestBody TermAccount tAcc) throws AccountDoesNotExistException
	{
		return new ResponseEntity<TermAccount>(accountServiceImpl.updateTermAccount(id,tAcc),HttpStatus.OK);
	}	
	
	//----------------------------------------------------------------	
	
	@GetMapping("/listAdmins")
	public ResponseEntity<Set<Admin>> listAllAdmins()
	{
		return new ResponseEntity<Set<Admin>>(adminService.listAllAdmins(),HttpStatus.OK);
	}
	
	@GetMapping("/findAdminById/{id}")
	public ResponseEntity<Admin> findAdminById(@PathVariable Long Id) throws AdminNotFoundException
	{
		return new ResponseEntity<Admin>(adminService.findAdminById(Id),HttpStatus.OK);
	}
	
	@PutMapping("/updateAdmin/{id}")
	public ResponseEntity<Admin> updateAdmin(@RequestBody Admin ad,@PathVariable Long id) throws AdminNotFoundException
	{
		return new ResponseEntity<Admin>(adminService.updateAdmin(ad, id),HttpStatus.OK);
	}	
	
	@DeleteMapping("/removeAdmin/{id}")
	public ResponseEntity<Boolean> removeAdmin(@PathVariable Long id) throws AdminNotFoundException
	{
		return new ResponseEntity<Boolean>(adminService.removeAdmin(id),HttpStatus.OK);
	}
	//---------------------------------------------------------------------------------------
	//api functions that provide functionalities to an admin to add/delete/update/find/list customers
	
	

	//api to list all customers
	@GetMapping("/listCustomers")
	public ResponseEntity<List<Customer>> listAllCustomers()
	{
		return new ResponseEntity<List<Customer>>(customerService.listAllCustomer(),HttpStatus.OK);
	}
	
	//api to find by id
	@GetMapping("/findCustomerById/{id}")
	public ResponseEntity<Customer> findCustomerById(@PathVariable Long id) throws CustomerNotFoundException
	{
		return new ResponseEntity<Customer>(customerService.findCustomerById(id),HttpStatus.OK);
	}
	
	//api to update details of customer by taking an id 
	@PutMapping("/updateCustomer/{id}")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer c,@PathVariable Long id) throws CustomerNotFoundException
	{
		return new ResponseEntity<Customer>(customerService.updateCustomer(c, id),HttpStatus.OK);
	}	
	
	//api to delete a customer by taking an id
	@DeleteMapping("/deleteCustomer/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable Long id) throws CustomerNotFoundException
	{
		return new ResponseEntity<String>(customerService.deleteCustomer(id),HttpStatus.OK);
	}
	//-----------------------------------------------------------------------------------------------
	
//	@PostMapping("/createTransaction")
//	public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction c)
//	{
//		Transaction createTrans = transService.createTransaction(c);
//		return new ResponseEntity<Transaction>(createTrans,HttpStatus.OK);
//	}
	
	@GetMapping("/findTransaction/{id}")
	public ResponseEntity<Transaction> findTransactionById(@PathVariable Long Id) throws InvalidTransactionIDException
	{
		return new ResponseEntity<Transaction>(transService.findTransactionById(Id),HttpStatus.OK);
	}
	
	@GetMapping("/AllAccTrans/{id}")
	public ResponseEntity<List<Transaction>> getAllMyAccTransactions(@PathVariable Long Id) throws AccountDoesNotExistException
	{
		return new ResponseEntity<List<Transaction>>(transService.getAllMyAccTransactions(Id),HttpStatus.OK);
	}
	
	@GetMapping("/AllTransBetweenDates/{id}")
	public ResponseEntity<List<Transaction>> listAllTransactions(@PathVariable Long Id, LocalDateTime from, LocalDateTime to) throws AccountDoesNotExistException, TransactionDoesNotExistException
	{
		return new ResponseEntity<List<Transaction>>(transService.listAllTransactions(Id,from,to),HttpStatus.OK);
	}
	
	//------------------------------------------------------------------------------------
	//validator controller
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
	
}
