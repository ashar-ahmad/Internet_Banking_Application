package com.training.sprint1.entities;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity(name = "IBA_Accounts")
@Inheritance(strategy =InheritanceType.JOINED)
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@JsonIdentityInfo(

        generator = ObjectIdGenerators.PropertyGenerator.class,

        property = "accountId")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long accountId;
	
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	protected Double interestRate;
	
	protected Double balance;
	protected LocalDateTime dateOfOpening;
	
//	@ManyToMany(targetEntity = Customer.class, mappedBy = "accounts", cascade = CascadeType.ALL)
//	protected Set<Customer> customers;
	
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	@OneToMany
	protected Set<Nominee> nominees;
	
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	@OneToMany
	protected Set<Beneficiary> beneficiaries;
	
	protected AccountType accountType;
	
	@JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	@OneToMany(targetEntity = Transaction.class,mappedBy = "bankAccount",cascade = CascadeType.ALL)	
	protected List<Transaction> transactions;
	
	public Account(Long accountId, Double interestRate, Double balance, LocalDateTime dateOfOpening,
			AccountType accountType) {
		super();
		this.accountId = accountId;
		this.interestRate = interestRate;
		this.balance = balance;
		this.dateOfOpening = dateOfOpening;
		this.accountType = accountType;
	}

//	public Account(Double interestRate, Double balance, LocalDateTime dateOfOpening, Set<Customer> customers,
//			Set<Nominee> nominees, Set<Beneficiary> beneficiaries, AccountType accountType) {
//		super();
//		this.interestRate = interestRate;
//		this.balance = balance;
//		this.dateOfOpening = dateOfOpening;
//		this.customers = customers;
//		this.nominees = nominees;
//		this.beneficiaries = beneficiaries;
//		this.accountType = accountType;
//	}

	
	
//	public Account(Double interestRate, Double balance, LocalDateTime dateOfOpening,
//			Set<Customer> customers, Set<Nominee> nominees, Set<Beneficiary> beneficiaries, AccountType accountType,
//			Set<Transaction> transactions) {
//		super();		
//		this.interestRate = interestRate;
//		this.balance = balance;
//		this.dateOfOpening = dateOfOpening;
//		this.customers = customers;
//		this.nominees = nominees;
//		this.beneficiaries = beneficiaries;
//		this.accountType = accountType;
//		this.transactions = transactions;
//	}



//	public Set<Customer> getCustomers() {
//		return customers;
//	}
//
//	public void setCustomers(Set<Customer> customers) {
//		this.customers = customers;
//	}

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

	//Default Constructor
	public Account() { 
		super();
		// TODO Auto-generated constructor stub
	}

	//Parameterized Constructor with all Attributes
	public Account(Long accountId, Double interestRate, Double balance, LocalDateTime dateOfOpening) {
		super();
		this.accountId = accountId;
		this.interestRate = interestRate;
		this.balance = balance;
		this.dateOfOpening = dateOfOpening;
	}

	//Parameterized Constructor without id
	public Account(Double interestRate, Double balance, LocalDateTime dateOfOpening) {
		super();
		this.interestRate = interestRate;
		this.balance = balance;
		this.dateOfOpening = dateOfOpening;
	}

	
	
	public Account(long l, double d, double e, LocalDateTime of) {
		this.accountId=l;
		this.interestRate=d;
		this.balance=e;
		this.dateOfOpening=of;
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
		Period period=Period.between(this.dateOfOpening.toLocalDate(), LocalDateTime.now().toLocalDate());
		this.balance=this.balance+((this.balance*this.interestRate*period.getMonths())/12)/100;
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
		return "Account [accountId=" + accountId + ", interestRate=" + interestRate + ", balance=" + balance
				+ ", dateOfOpening=" + dateOfOpening + "]";
	}
	
	
	
	
}