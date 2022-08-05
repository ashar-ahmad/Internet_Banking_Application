package com.training.sprint1.entities;

import java.time.LocalDateTime;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity(name = "IBA_Transaction")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@JsonIdentityInfo(

        generator = ObjectIdGenerators.PropertyGenerator.class,

        property = "transactionId")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long transactionId;
	private Double amount;
	
	//EnumF
	private TransactionType transactionType;
	
	private LocalDateTime transactionDateTime;
	
	//@JsonIgnore
	@ManyToOne(targetEntity = Account.class,cascade = CascadeType.ALL)
	private Account bankAccount;
	
	
	public Account getBankAccount() {
		return bankAccount;
	}


	public void setBankAccount(Account bankAccount) {
		this.bankAccount = bankAccount;
	}


	//Enum
	private TransactionStatus transactionStatus;
	
	private String transactionRemarks;

	
	
	// Default Constructor 
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	



	public Transaction(Double amount, TransactionType transactionType, LocalDateTime transactionDateTime,
			Account bankAccount, TransactionStatus transactionStatus, String transactionRemarks) {
		super();
		this.amount = amount;
		this.transactionType = transactionType;
		this.transactionDateTime = transactionDateTime;
		this.bankAccount = bankAccount;
		this.transactionStatus = transactionStatus;
		this.transactionRemarks = transactionRemarks;
	}


	// Constructor with ID
	public Transaction(Long transactionId, Double amount, TransactionType transactionType,
			LocalDateTime transactionDateTime, TransactionStatus transactionStatus, String transactionRemarks) {
		super();
		this.transactionId = transactionId;
		this.amount = amount;
		this.transactionType = transactionType;
		this.transactionDateTime = transactionDateTime;
		this.transactionStatus = transactionStatus;
		this.transactionRemarks = transactionRemarks;
	}


	// Constructor without ID
	public Transaction(Double amount, TransactionType transactionType, LocalDateTime transactionDateTime,
			TransactionStatus transactionStatus, String transactionRemarks) {
		super();
		this.amount = amount;
		this.transactionType = transactionType;
		this.transactionDateTime = transactionDateTime;
		this.transactionStatus = transactionStatus;
		this.transactionRemarks = transactionRemarks;
	}


	public Transaction(Long transactionId, Double amount, TransactionType transactionType,
			LocalDateTime transactionDateTime, Account bankAccount, TransactionStatus transactionStatus,
			String transactionRemarks) {
		super();
		this.transactionId = transactionId;
		this.amount = amount;
		this.transactionType = transactionType;
		this.transactionDateTime = transactionDateTime;
		this.bankAccount = bankAccount;
		this.transactionStatus = transactionStatus;
		this.transactionRemarks = transactionRemarks;
	}


	// Getters and Setters
	public Long getTransactionId() {
		return transactionId;
	}


	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}


	public Double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public TransactionType getTransactionType() {
		return transactionType;
	}


	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}


	public LocalDateTime getTransactionDateTime() {
		return transactionDateTime;
	}


	public void setTransactionDateTime(LocalDateTime transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}


	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}


	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}


	public String getTransactionRemarks() {
		return transactionRemarks;
	}


	public void setTransactionRemarks(String transactionRemarks) {
		this.transactionRemarks = transactionRemarks;
	}

	
	// toString
	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", amount=" + amount + ", transactionType="
				+ transactionType + ", transactionDateTime=" + transactionDateTime + ", transactionStatus="
				+ transactionStatus + ", transactionRemarks=" + transactionRemarks + "]";
	}
	
	
	
}