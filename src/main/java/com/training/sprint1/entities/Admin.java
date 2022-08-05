package com.training.sprint1.entities;

import java.util.Set;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "IBA_Admin")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Admin extends User{

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(Long userId, String userName, String phoneNo, String emailID, String password, int age,
			Gender gender, Set<Role> roles) {
		super(userId, userName, phoneNo, emailID, password, age, gender,roles);
		// TODO Auto-generated constructor stub
	}

	public Admin(String userName, String phoneNo, String emailID, String password, int age, Gender gender,Set<Role> roles) {
		super(userName, phoneNo, emailID, password, age, gender,roles);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Admin [getUserId()=" + getUserId() + ", getUserName()=" + getUserName() + ", getPhoneNo()="
				+ getPhoneNo() + ", getEmailID()=" + getEmailID() + ", getPassword()=" + getPassword() + ", getAge()="
				+ getAge() + ", getGender()=" + getGender() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}

	
	
}
