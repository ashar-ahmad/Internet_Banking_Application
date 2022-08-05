package com.training.sprint1.services;

import java.util.Set;


import org.springframework.stereotype.Service;

import com.training.sprint1.entities.Account;
import com.training.sprint1.entities.AccountType;
import com.training.sprint1.entities.SavingAccount;
import com.training.sprint1.entities.TermAccount;
import com.training.sprint1.entities.Transaction;
import com.training.sprint1.exceptions.AccountDoesNotExistException;
import com.training.sprint1.exceptions.CustomerDoesNotExistException;
import com.training.sprint1.exceptions.InvalidCredentialsException;
import com.training.sprint1.exceptions.LessBalanceException;
import com.training.sprint1.exceptions.TermPeriodNotFinishedException;
@Service
public interface IAccountService {

	public Transaction  transferMoney(Long senderAccountId ,Long receiverAccountId,double amount) throws AccountDoesNotExistException, LessBalanceException, TermPeriodNotFinishedException;
	public Transaction withdraw(Long accountId,AccountType accountType,Double amount) throws AccountDoesNotExistException, LessBalanceException, TermPeriodNotFinishedException;
	public Transaction deposit(Long accountId,double amount) throws AccountDoesNotExistException;
	public SavingAccount addSavingsAccount(SavingAccount saving);
	public TermAccount addTermAccount( TermAccount saving);
	public SavingAccount updateSavingsAccount(Long accountId,SavingAccount saving) throws AccountDoesNotExistException;
	public TermAccount updateTermAccount(Long accountId,TermAccount saving) throws AccountDoesNotExistException;
	public boolean closeSavingsAccount(Long accountId) throws AccountDoesNotExistException;
	public boolean closeTermAccount(Long accountId) throws AccountDoesNotExistException;
	public Account findAccountById(Long account_id) throws AccountDoesNotExistException;
	public Set<Account> viewAccounts(Long customerId) throws CustomerDoesNotExistException;
	public Set<SavingAccount> viewSavingsAcc(Long customerId);
	public Set<TermAccount> viewTermAcc(Long customerId);
	    
}
