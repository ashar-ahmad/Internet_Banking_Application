package com.training.sprint1.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.sprint1.entities.Account;
import com.training.sprint1.entities.AccountType;
import com.training.sprint1.entities.Customer;
import com.training.sprint1.entities.SavingAccount;
import com.training.sprint1.entities.TermAccount;
import com.training.sprint1.entities.Transaction;
import com.training.sprint1.entities.TransactionStatus;
import com.training.sprint1.entities.TransactionType;
import com.training.sprint1.entities.User;
import com.training.sprint1.exceptions.AccountDoesNotExistException;
import com.training.sprint1.exceptions.CustomerDoesNotExistException;
import com.training.sprint1.exceptions.InvalidCredentialsException;
import com.training.sprint1.exceptions.LessBalanceException;
import com.training.sprint1.exceptions.TermPeriodNotFinishedException;
import com.training.sprint1.repo.IAccountRepository;
import com.training.sprint1.repo.IAdminRepository;
import com.training.sprint1.repo.ICustomerRepository;
import com.training.sprint1.repo.ISavingAccountRepository;
import com.training.sprint1.repo.ITermAccountRepository;
import com.training.sprint1.repo.ITransactionRepository;

@Service
@Transactional
public class AccountServiceImpl implements IAccountService{

	@Autowired
	public IAccountRepository iAccountRepository;
	
	@Autowired
	public ICustomerRepository iCustomerRepository;
	
	@Autowired
	public ISavingAccountRepository iSavingAccountRepository;
	
	@Autowired
	public ITermAccountRepository iTermAccountRepository;
	
	@Autowired
	public ITransactionRepository iTransactionRepository;
	
	@Override
	public Transaction transferMoney(Long senderAccountId, Long receiverAccountId, double amount
			) throws AccountDoesNotExistException,LessBalanceException, TermPeriodNotFinishedException{
		// TODO Auto-generated method stub	
		Transaction transaction=new Transaction();	
		SavingAccount senderSavingAccount=null;
		Account retSenderAccount=null;		
		Account retreivedReceiverAccount=null;
		try {	
								
			
			
			iAccountRepository.findById(senderAccountId).orElseThrow(AccountDoesNotExistException::new);
			iAccountRepository.findById(receiverAccountId).orElseThrow(AccountDoesNotExistException::new);
								
			if(iAccountRepository.findById(senderAccountId).get().getAccountType()==AccountType.TERM_ACCOUNT)
				throw new TermPeriodNotFinishedException();
			
			retSenderAccount=iAccountRepository.findById(senderAccountId).get();
			senderSavingAccount=iSavingAccountRepository.findById(senderAccountId).get();
			if((retSenderAccount.getBalance()-amount)<senderSavingAccount.getMinBalance())
			{
				transaction.setTransactionStatus(TransactionStatus.FAILED);
				transaction.setTransactionRemarks("The transaction failed because of less balance");
				throw new LessBalanceException("You can not perform the transaction"+"\n"+
			"There is no sufficient balance in your account");
			}
			else
			{
				transaction.setAmount(amount);
				transaction.setBankAccount(iAccountRepository.findById(senderAccountId).get());
				transaction.setTransactionDateTime(LocalDateTime.now());
				transaction.setTransactionType(TransactionType.TRANSFER);
				retSenderAccount.setBalance(retSenderAccount.getBalance()-amount);
				iAccountRepository.save(retSenderAccount);
				retreivedReceiverAccount=iAccountRepository.findById(receiverAccountId).get();
				retreivedReceiverAccount.setBalance(retreivedReceiverAccount.getBalance()+amount);
				iAccountRepository.save(retreivedReceiverAccount);
				transaction.setTransactionStatus(TransactionStatus.SUCCESSFUL);
				transaction.setTransactionRemarks("The  amount: "
				+amount+" from account: "+senderAccountId+
				" to account: "+receiverAccountId+" is succesfully transfered");
			}							
										
										
		}
		catch(AccountDoesNotExistException ae)
		{
			transaction.setTransactionStatus(TransactionStatus.FAILED);
			transaction.setTransactionRemarks("No such account exists");
		}		
		catch(LessBalanceException lbe)
		{
			transaction.setTransactionStatus(TransactionStatus.FAILED);		
			if(retSenderAccount.getBalance()-senderSavingAccount.getFine()>0)
			{
				retSenderAccount.setBalance(retSenderAccount.getBalance()-senderSavingAccount.getFine());
				transaction.setTransactionRemarks("The transaction failed because of less balance and "
						+ " a fine of certain amount as per bank policy[Bank charges fine for transaction attempt below minimum balance] has been deducted from your account");
				iAccountRepository.save(retSenderAccount);
			}
			else
			{
				transaction.setTransactionStatus(TransactionStatus.FAILED);		
				this.closeSavingsAccount(retSenderAccount.getAccountId());
				transaction.setTransactionRemarks("The transaction failed because of less balance and "
						+ " since the balance does not meet bank policy requirements , your account has been closed by bank");
			}
		}
		catch (TermPeriodNotFinishedException te) {
			
			transaction.setTransactionStatus(TransactionStatus.FAILED);
			transaction.setTransactionRemarks("Transfer from term account is prohibited");
		}
		return iTransactionRepository.save(transaction);
		
	}	
	@Override
	public Transaction withdraw(Long accountId,AccountType accountType,Double amount) throws AccountDoesNotExistException,LessBalanceException,TermPeriodNotFinishedException{
		// TODO Auto-generated method stub	
		Transaction transaction=new Transaction();		

		SavingAccount retSavingAccount=null;		
		TermAccount retTermAccount=null;
		//Account retreivedReceiverAccount=null;	
		try {	
			transaction.setAmount(amount);
			transaction.setBankAccount(iAccountRepository.findById(accountId).get());

			transaction.setTransactionDateTime(LocalDateTime.now());
			transaction.setTransactionType(TransactionType.TRANSFER);

			transaction.setTransactionDateTime(LocalDateTime.now());
			transaction.setTransactionType(TransactionType.WITHDRAW);
			if(iAccountRepository.findById(accountId)!=null)
			{
				if(accountType==AccountType.SAVINGS_ACCOUNT)
				{
					retSavingAccount=iSavingAccountRepository.findById(accountId).get();
					if((retSavingAccount.getBalance()-amount)<retSavingAccount.getMinBalance())
					{						
						throw new LessBalanceException("You can not perform the transaction"+"\n"+
					"There is no sufficient balance in your account");						
					}
					else
					{

						retSavingAccount.setBalance(retSavingAccount.getBalance()-amount);
						iAccountRepository.save(retSavingAccount);

						transaction.setTransactionStatus(TransactionStatus.SUCCESSFUL);
						transaction.setTransactionRemarks("The  amount: "
								+amount+" from account: "+accountId+
								" is succesfully withdrawn");
					}
				}
				else if(accountType==AccountType.TERM_ACCOUNT)
				{
					TermAccount retreivedTermAccount=iTermAccountRepository.findById(accountId).get();
					retTermAccount= retreivedTermAccount;
					Period period=Period.between(retreivedTermAccount.getDateOfOpening().toLocalDate(),LocalDateTime.now().toLocalDate());
					if(period.getMonths()>retreivedTermAccount.getMonths())
					{
						retreivedTermAccount.setBalance(retreivedTermAccount.getBalance()-amount);
						iAccountRepository.save(retreivedTermAccount);
						transaction.setTransactionStatus(TransactionStatus.SUCCESSFUL);
						transaction.setTransactionRemarks("The  amount: "
								+amount+" from account: "+accountId+
								" is succesfully withdrawn");
					}
					else
					{
						throw new TermPeriodNotFinishedException("Your term Period is not finished yet");
					}
				}
			}
			else
			{				
				throw new AccountDoesNotExistException();
			}			
	}
	catch(AccountDoesNotExistException ae)
	{
		transaction.setTransactionStatus(TransactionStatus.FAILED);
		transaction.setTransactionRemarks("The transaction is failed becouse there is no such account exist");
	}		
	catch(LessBalanceException lbe)
	{
		transaction.setTransactionStatus(TransactionStatus.FAILED);		
		if(retSavingAccount.getBalance()-retSavingAccount.getFine()>0)
		{
			retSavingAccount.setBalance(retSavingAccount.getBalance()-retSavingAccount.getFine());
			transaction.setTransactionRemarks("The transaction failed because of less balance and "
					+ "and a fine of certain rupees as per bank policy has been deducted from your account");
			iAccountRepository.save(retSavingAccount);
		}
		else
		{
			transaction.setTransactionStatus(TransactionStatus.FAILED);		
			this.closeSavingsAccount(retSavingAccount.getAccountId());
			transaction.setTransactionRemarks("The transaction failed because of less balance and "
					+ "and as the balance is very as per comapny policy your account has been closed by bank");
		}
	}		
	catch (TermPeriodNotFinishedException tpe) {			
		
		transaction.setTransactionStatus(TransactionStatus.SUCCESSFUL);
		retTermAccount.setBalance(retTermAccount.getBalance()-amount-retTermAccount.getPenaltyAmount());
		transaction.setTransactionRemarks("The amount has been succesfully withdrwan"
				+ "and as per the bank policy a certain penalty amount has been deducted");
		
		
	}
	return iTransactionRepository.save(transaction);
	}

	@Override
	public Transaction deposit(Long accountId,double amount) throws AccountDoesNotExistException{
		// TODO Auto-generated method stub
		Transaction transaction=new Transaction();
		
		SavingAccount retSavingAccount = null;
		try {
			transaction.setAmount(amount);
			transaction.setBankAccount(iAccountRepository.findById(accountId).orElseThrow(AccountDoesNotExistException::new));


			transaction.setTransactionDateTime(LocalDateTime.now());
			transaction.setTransactionType(TransactionType.DEPOSIT);

			Account retreivedAccount=iAccountRepository.findById(accountId).orElseThrow(AccountDoesNotExistException::new);
			retreivedAccount.setBalance(retreivedAccount.getBalance()+amount);
			iAccountRepository.save(retreivedAccount);
			transaction.setTransactionStatus(TransactionStatus.SUCCESSFUL);
			transaction.setTransactionRemarks("The  amount: "
					+amount+" from account: "+accountId+
					" is succesfully deposited");
			
		}
		catch (AccountDoesNotExistException ae) {
			transaction.setTransactionStatus(TransactionStatus.FAILED);
			transaction.setTransactionRemarks("The transaction is failed becouse there is no such account exist");
		}
		
		 
		return iTransactionRepository.save(transaction);
	}

	@Override
	public SavingAccount addSavingsAccount(SavingAccount saving) {
		
		SavingAccount sa =  iAccountRepository.save(saving);
		return sa;
	}

	@Override
	public TermAccount addTermAccount(TermAccount saving) {
		// TODO Auto-generated method stub
		return iAccountRepository.save(saving);
	}

	@Override
	public SavingAccount updateSavingsAccount(Long accountId,SavingAccount saving) throws AccountDoesNotExistException{
		// TODO Auto-generated method stub
		SavingAccount retSavingAccount=null;
		try {
			retSavingAccount=(SavingAccount)iAccountRepository.findById(accountId).orElseThrow(AccountDoesNotExistException::new);
			BeanUtils.copyProperties(saving, retSavingAccount,"accountId");			
		}
		catch(AccountDoesNotExistException ae)
		{
			
		}
		return iAccountRepository.save(retSavingAccount);
	}

	@Override
	public TermAccount updateTermAccount(Long accountId,TermAccount saving) throws AccountDoesNotExistException{
		// TODO Auto-generated method stub
		TermAccount retTermAccount=null;
		try {
			retTermAccount=(TermAccount)iAccountRepository.findById(accountId).orElseThrow(AccountDoesNotExistException::new);
//			retTermAccount = iTermAccountRepository.findById(accountId).orElseThrow(AccountDoesNotExistException::new);
			BeanUtils.copyProperties(saving, retTermAccount,"accountId");			
		}
		catch(AccountDoesNotExistException ae)
		{
			
		}
		TermAccount updatedTermAcc = iAccountRepository.save(retTermAccount);
		return updatedTermAcc;
	}

	@Override
	public boolean closeSavingsAccount(Long accountId) throws AccountDoesNotExistException{
		// TODO Auto-generated method stub
		Account retSavingAccount=null;
		try {
			retSavingAccount=iAccountRepository.findById(accountId).orElseThrow(AccountDoesNotExistException::new);
			iAccountRepository.delete(retSavingAccount);
			return true;
		}
		catch(AccountDoesNotExistException ae)
		{
			return false;
		}
	}

	@Override
	public boolean closeTermAccount(Long accountId) throws AccountDoesNotExistException{
		// TODO Auto-generated method stub
		Account retTermAccount=null;
		try {
			retTermAccount=iAccountRepository.findById(accountId).orElseThrow(AccountDoesNotExistException::new);
			iAccountRepository.delete(retTermAccount);
			return true;
		}
		catch(AccountDoesNotExistException ae)
		{
			return false;
		}
	}

	@Override
	public Account findAccountById(Long account_id) throws AccountDoesNotExistException{
		// TODO Auto-generated method stub
		Account retAccount=null;
		try {
			retAccount=iAccountRepository.findById(account_id).orElseThrow(AccountDoesNotExistException::new);					
		}
		catch(AccountDoesNotExistException ae)
		{
			
		}
		return retAccount;
	}

	@Override
	public Set<Account> viewAccounts(Long customerId) throws CustomerDoesNotExistException{
		// TODO Auto-generated method stub
	 Set<Account> retAccount = null;
	 Set<SavingAccount> retSavAccount=null;
	 Set<TermAccount> retTermAccount=null;
	
		try {

			Customer cust=iCustomerRepository.findById(customerId).get();
			if(cust!=null)
			{
				retSavAccount=cust.getSavingAccounts();
				retTermAccount=cust.getTermAccounts();
				Customer retCust = iCustomerRepository.findById(customerId).get();
				if(retCust !=null)
				{
					retSavAccount = retCust.getSavingAccounts();
					retTermAccount=retCust.getTermAccounts();
					retAccount = new HashSet<>();
					retAccount.addAll(retSavAccount);
					retAccount.addAll(retTermAccount);
				}
				else
				{
					throw new CustomerDoesNotExistException();		
				}
		}
		}
		catch(CustomerDoesNotExistException ce)
		{
			
		}		
		return retAccount;
	}

	@Override
	public Set<SavingAccount> viewSavingsAcc(Long customerId) {
		Set<SavingAccount> retAccount=null;
		try {
			Customer cust=iCustomerRepository.findById(customerId).get();
			if(cust!=null)
			{				
				retAccount=cust.getSavingAccounts();				
				

			}
			else
			{
				throw new CustomerDoesNotExistException();
			}
		}
		catch(CustomerDoesNotExistException ce)
		{
			
		}		
		return retAccount;
	}

	@Override
	public Set<TermAccount> viewTermAcc(Long customerId) {
		Set<TermAccount> retAccount=null;
		
		try {
			Customer cust=iCustomerRepository.findById(customerId).get();
			if(cust!=null)
			{
				retAccount=cust.getTermAccounts();				
		
			}
			else
			{
				throw new CustomerDoesNotExistException();
			}
		}
		catch(CustomerDoesNotExistException ce)
		{
			
		}		
		return retAccount;
	}
}