package com.training.sprint1.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.sprint1.entities.Account;
import com.training.sprint1.entities.Transaction;
import com.training.sprint1.exceptions.AccountDoesNotExistException;
import com.training.sprint1.exceptions.InvalidTransactionIDException;
import com.training.sprint1.exceptions.TransactionDoesNotExistException;
import com.training.sprint1.repo.IAccountRepository;
import com.training.sprint1.repo.ITransactionRepository;

@Service
@Transactional
public class TransactionServiceImpl implements ITransactionService{
	
	@Autowired
	ITransactionRepository iTransactionRepository;
	
	@Autowired
	IAccountRepository iAccountRepository;
	
	@Override
	public Transaction createTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		
		iTransactionRepository.save(transaction);
		return iTransactionRepository.save(transaction);
	}

	
	// To be decided
	@Override
	public Transaction viewTransaction(Long transactionId) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public Transaction findTransactionById(Long transactionId) throws InvalidTransactionIDException{
		// TODO Auto-generated method stub
		Transaction transaction=null;
		try {
			transaction= iTransactionRepository.findById(transactionId).get();
			if(transactionId==null) {
				throw new InvalidTransactionIDException("INVALID TRANSACTION ID");
			}
		}
		catch (InvalidTransactionIDException ite) {
			
			ite.printStackTrace();
		}
		return transaction;
	}

	@Override
	public List<Transaction> listAllTransactions(Long accountID, LocalDateTime from, LocalDateTime to) throws AccountDoesNotExistException,TransactionDoesNotExistException{
		// TODO Auto-generated method stub
		List<Transaction> trans= null;
		Account retAccount=null;
		try {
			retAccount=iAccountRepository.findById(accountID).get();
			if(retAccount==null)
				throw new AccountDoesNotExistException();
			trans=iTransactionRepository.findByTransactionDateTimeBetweenAndBankAccount(from,to,retAccount);
			if(trans==null)
				throw new TransactionDoesNotExistException();
		}
		catch (AccountDoesNotExistException ae) {
			ae.printStackTrace();
		}
		catch(TransactionDoesNotExistException tde)
		{
			tde.printStackTrace();
		}
		return trans;
	}


	@Override
	public List<Transaction> getAllMyAccTransactions(Long account_Id) throws AccountDoesNotExistException{
		
		// TODO Auto-generated method stub	
		
		List<Transaction> transaction = null; 
		try {
			//transaction = iTransactionRepository.findByAccountID(account_ID);
			transaction=iAccountRepository.findById(account_Id).get().getTransactions();
			if(transaction==null) {
				throw new AccountDoesNotExistException();
			}
		}
		catch(AccountDoesNotExistException ae)
		{
			ae.printStackTrace();
			
		}
		return transaction;
	}
	
	@Override
	public List<Transaction> getAllTransactions()
	{
		List<Transaction> transactions=null;
		transactions=  iTransactionRepository.findAll();
		
		return transactions;
		
	}
	
	

}