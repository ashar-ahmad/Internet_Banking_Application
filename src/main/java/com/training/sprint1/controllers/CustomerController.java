package com.training.sprint1.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.sprint1.entities.Account;
import com.training.sprint1.entities.AccountType;
import com.training.sprint1.entities.Beneficiary;
import com.training.sprint1.entities.Customer;
import com.training.sprint1.entities.Nominee;
import com.training.sprint1.entities.SavingAccount;
import com.training.sprint1.entities.TermAccount;
import com.training.sprint1.entities.Transaction;
import com.training.sprint1.entities.User;
import com.training.sprint1.exceptions.AccountDoesNotExistException;
import com.training.sprint1.exceptions.BeneficiaryNotFoundException;
import com.training.sprint1.exceptions.CustomerDoesNotExistException;
import com.training.sprint1.exceptions.CustomerNotFoundException;
import com.training.sprint1.exceptions.InvalidTransactionIDException;
import com.training.sprint1.exceptions.LessBalanceException;
import com.training.sprint1.exceptions.NomineeNotFoundException;
import com.training.sprint1.exceptions.TermPeriodNotFinishedException;
import com.training.sprint1.exceptions.TransactionDoesNotExistException;
import com.training.sprint1.repo.ICustomerRepository;
import com.training.sprint1.repo.IUserRepository;
import com.training.sprint1.services.AccountServiceImpl;
import com.training.sprint1.services.BeneficiaryServiceImpl;
import com.training.sprint1.services.CustomerServiceImpl;
import com.training.sprint1.services.NomineeServiceImpl;
import com.training.sprint1.services.TransactionServiceImpl;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600,allowedHeaders={"Authorization","Access-Control-Request-Headers","Content-Type","Access-Control-Allow-Origin","Access-Control-Allow-Credentials","Access-Control-Allow-Headers"})
@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	
	@Autowired
	private AccountServiceImpl accountServiceImpl;
	
	@Autowired
	private BeneficiaryServiceImpl beneficiaryService;
	
	@Autowired
	private NomineeServiceImpl nomineeService;	
	

	@Autowired
	private TransactionServiceImpl transService;
	
	@Autowired
	private IUserRepository iUserRepository;
	
	@Autowired
	private ICustomerRepository iCustomerRepository;
	
	
	@RequestMapping("/welcome")
	public String welcome()
	{
		return "welcome";
	}
	
	@PatchMapping("/deposit/{id}")
	public ResponseEntity<Transaction> deposit(@PathVariable Long id,@RequestBody Transaction transaction) throws AccountDoesNotExistException
	{
		double amount=transaction.getAmount();		
		return new ResponseEntity<Transaction>(accountServiceImpl.deposit(id, amount),HttpStatus.OK);
	}
	
	@PatchMapping("/withdraw/{id}/{accountType}")
	public ResponseEntity<Transaction> withdraw(@PathVariable Long id,@PathVariable AccountType accountType,@RequestBody Transaction transaction) throws AccountDoesNotExistException, LessBalanceException, TermPeriodNotFinishedException
	{
		double amount=transaction.getAmount();	
		return new ResponseEntity<Transaction>(accountServiceImpl.withdraw(id, accountType, amount),HttpStatus.OK);
	}
	
	@PatchMapping("/transfer/{sendId}/{recId}")
	public ResponseEntity<Transaction> transfer(@PathVariable Long sendId,@PathVariable Long recId,@RequestBody Transaction transaction) throws AccountDoesNotExistException, LessBalanceException, TermPeriodNotFinishedException
	{
		double amount=transaction.getAmount();	
		return new ResponseEntity<Transaction>(accountServiceImpl.transferMoney(sendId, recId, amount),HttpStatus.OK);
	}
	
	@GetMapping("/accountDetails/{id}")
	public ResponseEntity<Account> findAccountById(@PathVariable Long id) throws AccountDoesNotExistException
	{
		return new ResponseEntity<Account>(accountServiceImpl.findAccountById(id),HttpStatus.OK);
	}
	
	//-------------------------------------------------------------------------------------------
	

	@PostMapping("/addBeneficiary")
	public ResponseEntity<Beneficiary> addBeneficiary(@RequestBody Beneficiary b)
	{
		Beneficiary addedBeneficiary = beneficiaryService.addBeneficiary(b);
		return new ResponseEntity<Beneficiary>(addedBeneficiary,HttpStatus.OK);
	}
	
	@GetMapping("/listBeneficiaries")
	public ResponseEntity<Set<Beneficiary>> listAllBeneficiaries()
	{
		
		return new ResponseEntity<Set<Beneficiary>>(beneficiaryService.listAllBeneficiaries(),HttpStatus.OK);
	}
	@PutMapping("/updateBeneficiaries/{id}")
	public ResponseEntity<Beneficiary> updateBeneficiary(@PathVariable Long id,@RequestBody Beneficiary b) throws BeneficiaryNotFoundException
	{
		
		return new ResponseEntity <Beneficiary>(beneficiaryService.updateBeneficiary(b,id),HttpStatus.OK);
	}
	@GetMapping("/findBeneficiariesByid/{id}")
	public ResponseEntity<Beneficiary> findBeneficiaryById(@PathVariable Long id) throws BeneficiaryNotFoundException
	{
		
		return new ResponseEntity <Beneficiary>(beneficiaryService.findBeneficiaryById(id),HttpStatus.OK);
	}
	@DeleteMapping("/deleteBeneficiaries/{id}")
	public ResponseEntity<Boolean> deleteBeneficiary(@PathVariable Long id) throws BeneficiaryNotFoundException
	{
		
		return new ResponseEntity<Boolean>(beneficiaryService.deleteBeneficiary(id),HttpStatus.OK);
	}
	
	//---------------------------------------------------------------------------------------------------
	
	@PostMapping("/addNominee")
	public ResponseEntity<Nominee> addNominee(@RequestBody Nominee nominee) throws NomineeNotFoundException
	{
		Nominee addedNominee = nomineeService.addNominee(nominee);
		return new ResponseEntity<Nominee>(addedNominee,HttpStatus.OK); 
	}
	
	@GetMapping("/listNominees/{id}")
	public ResponseEntity<Set<Nominee>> listNominees(@PathVariable Long id) throws NomineeNotFoundException,AccountDoesNotExistException
	{
		return new ResponseEntity<Set<Nominee>>(nomineeService.listAllNominees(id),HttpStatus.OK);
	}
	
	@PutMapping("/updateNominee/{id}")
	public ResponseEntity<Nominee> updateNominee(@RequestBody Nominee nominee,Long id) throws NomineeNotFoundException
	{
		return new ResponseEntity<Nominee>(nomineeService.updateNominee(nominee, id),HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteNominee/{id}")
	public ResponseEntity<Boolean> deleteNominee(@PathVariable Long nomineeId) throws NomineeNotFoundException
	{
		return new ResponseEntity<Boolean>(nomineeService.deleteNominee(nomineeId),HttpStatus.OK);
	}
	@GetMapping("/findNomineeByid/{id}")
	public ResponseEntity<Nominee> findNomineeById(@PathVariable Long nomineeId) throws NomineeNotFoundException
	{
		return new ResponseEntity<Nominee>(nomineeService.findNomineeById(nomineeId),HttpStatus.OK);
	}
	
	//-------------------------------------------------------------------------------------
	
	@PostMapping("/createTransaction")
	public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction c)
	{
		Transaction createTrans = transService.createTransaction(c);
		return new ResponseEntity<Transaction>(createTrans,HttpStatus.OK);
	}
	
	@GetMapping("/findTransaction/{Id}")
	public ResponseEntity<Transaction> findTransactionById(@PathVariable Long Id) throws InvalidTransactionIDException
	{
		return new ResponseEntity<Transaction>(transService.findTransactionById(Id),HttpStatus.OK);
	}
	
	@GetMapping("/AllAccTrans")
	public ResponseEntity<List<Transaction>> getAllAccTransactions() throws AccountDoesNotExistException
	{
		return new ResponseEntity<List<Transaction>>(transService.getAllTransactions(),HttpStatus.OK);
	}
	
	@GetMapping("/AllTransBetweenDates/{Id}")
	public ResponseEntity<List<Transaction>> listAllTransactions(@PathVariable Long Id, LocalDateTime from, LocalDateTime to) throws AccountDoesNotExistException, TransactionDoesNotExistException
	{
		return new ResponseEntity<List<Transaction>>(transService.listAllTransactions(Id,from,to),HttpStatus.OK);
	}
	
	@GetMapping("/getCustomerByName/{userName}")
	public ResponseEntity<Customer> getCustomerByName(@PathVariable String userName)
	{
		Customer cust=null;
		try {
			cust = iCustomerRepository.findByUserName(userName);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("username not found");
		}
		return new ResponseEntity<Customer>(cust,HttpStatus.OK);
	}

}

	

