package com.training.sprint1.entities;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.persistence.JoinColumn;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Required;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "IBA_User")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "userId")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long userId;
	
	
	protected String userName;
	
	
	protected String phoneNo;
	
	
	protected String emailID;
	
	
	protected String password;
	
	
	protected int age;
	
	
	protected Gender gender;
	
	 @JoinTable(name = "iba_user_role", joinColumns = {
	            @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
	            @JoinColumn(name = "ROLE_ID") })
	 @ManyToMany(fetch = FetchType.EAGER)
	  private Set<Role> roles;
	
	// Default Constructor
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	
	// Constructor with ID
	public User(Long userId, String userName, String phoneNo, String emailID, String password, int age, Gender gender,Set<Role> role) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.phoneNo = phoneNo;
		this.emailID = emailID;
		this.password = password;
		this.age = age;
		this.gender = gender;
		this.roles=role;
	}


	// Constructor without ID
	public User(String userName, String phoneNo, String emailID, String password, int age, Gender gender,Set<Role> role) {
		super();
		this.userName = userName;
		this.phoneNo = phoneNo;
		this.emailID = emailID;
		this.password = password;
		this.age = age;
		this.gender = gender;
		this.roles=role;
	}
	
	
	
	// Getters and Setters 
	
	
	
	public Long getUserId() {
		return userId;
	}


	public Set<Role> getRoles() {
		return roles;
	}




	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}




	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	
	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPhoneNo() {
		return phoneNo;
	}


	
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}


	public String getEmailID() {
		return emailID;
	}

	
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}


	public String getPassword() {
		return password;
	}

	
	public void setPassword(String password) {
		this.password = password;
	}


	public int getAge() {
		return age;
	}

	
	public void setAge(int age) {
		this.age = age;
	}


	public Gender getGender() {
		return gender;
	}

	
	public void setGender(Gender gender) {
		this.gender = gender;
	}


	// toString
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", phoneNo=" + phoneNo + ", emailID=" + emailID
				+ ", password=" + password + ", age=" + age + ", gender=" + gender + ", role=" + roles + "]";
	}
	
	
	
	
	
	
}
