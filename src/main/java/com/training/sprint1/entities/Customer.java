package com.training.sprint1.entities;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name ="IBA_Customer" )
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Customer extends User {	
	
	@ManyToMany(targetEntity = SavingAccount.class, cascade = CascadeType.ALL)
	private Set<SavingAccount> savingAccounts;	
	
	@ManyToMany(targetEntity = TermAccount.class, cascade = CascadeType.ALL)
	private Set<TermAccount> termAccounts;
	
	private String panCardNumber;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(Long userId, String userName, String phoneNo, String emailID, String password, int age,
			Gender gender,Set<Role> roles) {
		super(userId, userName, phoneNo, emailID, password, age, gender,roles);
		// TODO Auto-generated constructor stub
	}

	public Customer(String userName, String phoneNo, String emailID, String password, int age, Gender gender,Set<Role> roles) {
		super(userName, phoneNo, emailID, password, age, gender,roles);
		// TODO Auto-generated constructor stub
	}
	
	

//	public Customer(String userName, String phoneNo, String emailID, String password, int age, Gender gender,
//			Set<SavingAccount> savingAccounts, String panCardNumber) {
//		super(userName, phoneNo, emailID, password, age, gender);
//		this.savingAccounts = savingAccounts;
//		this.panCardNumber = panCardNumber;
//	}
//	
//	
//
//	public Customer(String userName, String phoneNo, String emailID, String password, int age, Gender gender,
//			Set<TermAccount> termAccounts, String panCardNumber) {
//		super(userName, phoneNo, emailID, password, age, gender);
//		this.termAccounts = termAccounts;
//		this.panCardNumber = panCardNumber;
//	}
	
	
	

	public Customer(Set<SavingAccount> savingAccounts, Set<TermAccount> termAccounts, String panCardNumber) {
		super();
		this.savingAccounts = savingAccounts;
		this.termAccounts = termAccounts;
		this.panCardNumber = panCardNumber;
	}

	public Customer(String userName, String phoneNo, String emailID, String password, int age, Gender gender,
		Set<SavingAccount> savingAccounts, Set<TermAccount> termAccounts, String panCardNumber,Set<Role> roles) {
	super(userName, phoneNo, emailID, password, age, gender,roles);
	this.savingAccounts = savingAccounts;
	this.termAccounts = termAccounts;
	this.panCardNumber = panCardNumber;
}

	public Customer(long l, String string, String string2, String string3, String string4, int i, Gender male,
			SavingAccount sa1, String string5) {
		// TODO Auto-generated constructor stub
	}

	public Customer(long l, String string, String string2, String string3, String string4, int i, Gender male,
			Set<SavingAccount> accounts, String string5) {
		// TODO Auto-generated constructor stub
	}

	public Set<SavingAccount> getSavingAccounts() {
		return savingAccounts;
	}

	public void setSavingAccounts(Set<SavingAccount> savingAccounts) {
		this.savingAccounts = savingAccounts;
	}

	public Set<TermAccount> getTermAccounts() {
		return termAccounts;
	}

	public void setTermAccounts(Set<TermAccount> termAccounts) {
		this.termAccounts = termAccounts;
	}

	public String getPanCardNumber() {
		return panCardNumber;
	}

	public void setPanCardNumber(String panCardNumber) {
		this.panCardNumber = panCardNumber;
	}

	@Override
	public String toString() {
		return "Customer [savingAccounts=" + savingAccounts + ", termAccounts=" + termAccounts + ", panCardNumber="
				+ panCardNumber + ", userId=" + userId + ", userName=" + userName + ", phoneNo=" + phoneNo
				+ ", emailID=" + emailID + ", age=" + age + ", gender=" + gender + "]";
	}

	
	
	
//	@Override
//	public String toString() {
//		return "Customer [savingAccounts=" + savingAccounts + ", termAccounts=" + termAccounts + ", panCardNumber="
//				+ panCardNumber + ", userId=" + userId + ", userName=" + userName + ", phoneNo=" + phoneNo
//				+ ", emailID=" + emailID + ", password=" + password + ", age=" + age + ", gender=" + gender + "]";
//	}
//
//	
	
	

}
