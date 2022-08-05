package com.training.sprint1.entities;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "IBA_Term_Accounts")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class TermAccount extends Account{
	
	
	@Transient
	private Double amount=0.0;
	
	private Integer months;
	private Double penaltyAmount;
	
	@ManyToMany(targetEntity = Customer.class, mappedBy = "termAccounts", cascade = CascadeType.ALL)
	protected Set<Customer> customers1;

	public TermAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TermAccount(Double interestRate, Double balance, LocalDateTime dateOfOpening) {
		super(interestRate, balance, dateOfOpening);
		// TODO Auto-generated constructor stub
	}

	public TermAccount(Long accountId, Double interestRate, Double balance, LocalDateTime dateOfOpening,
			AccountType accountType) {
		super(accountId, interestRate, balance, dateOfOpening, accountType);
		// TODO Auto-generated constructor stub
	}

	public TermAccount(long l, double d, double e, LocalDateTime of) {
		super(l, d, e, of);
		// TODO Auto-generated constructor stub
	}

	public TermAccount(Long accountId, Double interestRate, Double balance, LocalDateTime dateOfOpening) {
		super(accountId, interestRate, balance, dateOfOpening);
		// TODO Auto-generated constructor stub
	}

	public TermAccount(Long accountId, Double interestRate, Double balance, LocalDateTime dateOfOpening,
			AccountType accountType, Double amount, Integer months, Double penaltyAmount, Set<Customer> customers1) {
		super(accountId, interestRate, balance, dateOfOpening, accountType);
		this.amount = amount;
		this.months = months;
		this.penaltyAmount = penaltyAmount;
		this.customers1 = customers1;
	}

	public TermAccount(double d, double e, LocalDateTime of, double f, int i, double g) {
		// TODO Auto-generated constructor stub
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getMonths() {
		return months;
	}

	public void setMonths(Integer months) {
		this.months = months;
	}

	public Double getPenaltyAmount() {
		return penaltyAmount;
	}

	public void setPenaltyAmount(Double penaltyAmount) {
		this.penaltyAmount = penaltyAmount;
	}

	public Set<Customer> getCustomers1() {
		return customers1;
	}

	public void setCustomers1(Set<Customer> customers1) {
		this.customers1 = customers1;
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
		Period period=Period.between(this.dateOfOpening.toLocalDate(), LocalDateTime.now().toLocalDate());
		this.balance=this.balance+((this.balance*this.interestRate*period.getMonths())/12)/100;
		return this.balance;
	}
	
	public Double getBalance(int i) {
		
		return this.balance;
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
		return "TermAccount [accountId=" + accountId + ", balance=" + balance + ", dateOfOpening=" + dateOfOpening
				+ ", accountType=" + accountType + "]";
	}

	


//	@Override
//	public String toString() {
//		return "TermAccount [amount=" + amount + ", months=" + months + ", penaltyAmount=" + penaltyAmount
//				+ ", customers1=" + customers1 + ", accountId=" + accountId + ", interestRate=" + interestRate
//				+ ", balance=" + balance + ", dateOfOpening=" + dateOfOpening + ", nominees=" + nominees
//				+ ", beneficiaries=" + beneficiaries + ", accountType=" + accountType + ", transactions=" + transactions
//				+ "]";
//	}
//	
	
	
	
}
