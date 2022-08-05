//package com.training.sprint1.controllers;
//
//import java.time.LocalDateTime;
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.training.sprint1.entities.Customer;
//import com.training.sprint1.entities.Transaction;
//import com.training.sprint1.exceptions.AccountDoesNotExistException;
//import com.training.sprint1.exceptions.CustomerNotFoundException;
//
//import com.training.sprint1.exceptions.InvalidTransactionIDException;
//import com.training.sprint1.exceptions.TransactionDoesNotExistException;
//import com.training.sprint1.services.BeneficiaryServiceImpl;
//import com.training.sprint1.services.TransactionServiceImpl;
//
//@RestController
//@RequestMapping("/api/admin/transaction")
//public class TransactionController {
//	
//	@Autowired
//	TransactionServiceImpl transService;	
//	
//	@PostMapping
//	public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction c)
//	{
//		Transaction createTrans = transService.createTransaction(c);
//		return new ResponseEntity<Transaction>(createTrans,HttpStatus.OK);
//	}
//	
//	@GetMapping("/{id}")
//	public ResponseEntity<Transaction> findTransactionById(@PathVariable Long Id) throws InvalidTransactionIDException
//	{
//		return new ResponseEntity<Transaction>(transService.findTransactionById(Id),HttpStatus.OK);
//	}
//	
//	@GetMapping("/AllAccTrans/{id}")
//	public ResponseEntity<Set<Transaction>> getAllMyAccTransactions(@PathVariable Long Id) throws AccountDoesNotExistException
//	{
//		return new ResponseEntity<Set<Transaction>>(transService.getAllMyAccTransactions(Id),HttpStatus.OK);
//	}
//	
//	@GetMapping("/AllTransBetweenDates/{id}")
//	public ResponseEntity<Set<Transaction>> listAllTransactions(@PathVariable Long Id, LocalDateTime from, LocalDateTime to) throws AccountDoesNotExistException, TransactionDoesNotExistException
//	{
//		return new ResponseEntity<Set<Transaction>>(transService.listAllTransactions(Id,from,to),HttpStatus.OK);
//	}
//}
