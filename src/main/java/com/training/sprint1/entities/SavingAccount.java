package com.training.sprint1.entities;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name ="IBA_Saving_Accounts")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class SavingAccount extends Account{

	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private  Double minBalance;
	
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private Double fine;
	
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	@ManyToMany(targetEntity = Customer.class, mappedBy = "savingAccounts", cascade = CascadeType.ALL)
	protected Set<Customer> customers;

	public SavingAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SavingAccount(Double interestRate, Double balance, LocalDateTime dateOfOpening) {
		super(interestRate, balance, dateOfOpening);
		// TODO Auto-generated constructor stub
	}

	public SavingAccount(Long accountId, Double interestRate, Double balance, LocalDateTime dateOfOpening,
			AccountType accountType) {
		super(accountId, interestRate, balance, dateOfOpening, accountType);
		// TODO Auto-generated constructor stub
	}

	public SavingAccount(long l, double d, double e, LocalDateTime of) {
		super(l, d, e, of);
		// TODO Auto-generated constructor stub
	}

	public SavingAccount(Long accountId, Double interestRate, Double balance, LocalDateTime dateOfOpening) {
		super(accountId, interestRate, balance, dateOfOpening);
		// TODO Auto-generated constructor stub
	}

	public SavingAccount(Long accountId, Double interestRate, Double balance, LocalDateTime dateOfOpening,
			AccountType accountType, Double minBalance, Double fine, Set<Customer> customers) {
		super(accountId, interestRate, balance, dateOfOpening, accountType);
		this.minBalance = minBalance;
		this.fine = fine;
		this.customers = customers;
	}

	public SavingAccount(long l, double d, double e, LocalDateTime of, AccountType savingsAccount, double f, double g) {
		// TODO Auto-generated constructor stub
	}

	public Double getMinBalance() {
		return minBalance;
	}

	public void setMinBalance(Double minBalance) {
		this.minBalance = minBalance;
	}

	public Double getFine() {
		return fine;
	}

	public void setFine(Double fine) {
		this.fine = fine;
	}

	public Set<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}
	
	public Set<Nominee> getNominees() {
		return nominees;
	}

	public void setNominees(Set<Nominee> nominees) {
		this.nominees = nominees;
	}

	public Set<Beneficiary> getBeneficiaries() {
		return beneficiaries;
	}

	public void setBeneficiaries(Set<Beneficiary> beneficiaries) {
		this.beneficiaries = beneficiaries;
	}
	
	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}

	public Double getBalance() {
//		Period period=Period.between(this.dateOfOpening.toLocalDate(), LocalDateTime.now().toLocalDate());
//		this.balance=this.balance+((this.balance*this.interestRate*period.getMonths())/12)/100;
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public LocalDateTime getDateOfOpening() {
		return dateOfOpening;
	}

	public void setDateOfOpening(LocalDateTime dateOfOpening) {
		this.dateOfOpening = dateOfOpening;
	}
	
	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	
	

	public List<Transaction> getTransactions() {
		return transactions;
	}



	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return "SavingAccount [accountId=" + accountId + ", balance=" + balance + ", dateOfOpening=" + dateOfOpening
				+ ", accountType=" + accountType + "]";
	}

	

//	@Override
//	public String toString() {
//		return "SavingAccount [minBalance=" + minBalance + ", fine=" + fine + ", customers=" + customers
//				+ ", accountId=" + accountId + ", interestRate=" + interestRate + ", balance=" + balance
//				+ ", dateOfOpening=" + dateOfOpening + ", nominees=" + nominees + ", beneficiaries=" + beneficiaries
//				+ ", accountType=" + accountType + ", transactions=" + transactions + "]";
//	}
//	
//	
	
   
	
	
	
	
}