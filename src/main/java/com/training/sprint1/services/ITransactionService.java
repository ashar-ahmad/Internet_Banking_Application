package com.training.sprint1.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.training.sprint1.entities.Transaction;
import com.training.sprint1.exceptions.AccountDoesNotExistException;
import com.training.sprint1.exceptions.InvalidTransactionIDException;
import com.training.sprint1.exceptions.TransactionDoesNotExistException;


@Service
public interface ITransactionService {

	public Transaction createTransaction(Transaction transaction);
	public Transaction viewTransaction(Long transactionId);
	
	//May throw exception
	public Transaction findTransactionById(Long transactionId) throws InvalidTransactionIDException;
	
	public List<Transaction> listAllTransactions(Long accountID, LocalDateTime from, LocalDateTime to) throws AccountDoesNotExistException, TransactionDoesNotExistException;
	public List<Transaction> getAllMyAccTransactions(Long account_ID) throws AccountDoesNotExistException;
	public List<Transaction> getAllTransactions();
	
}
