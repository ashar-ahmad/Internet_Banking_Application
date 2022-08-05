package com.training.sprint1.validator;
import com.training.sprint1.entities.*;

public class DataValidator {

	public boolean validateCustomer(Customer c)
	{
		String name  = c.getUserName();
		Integer age = c.getAge();
		String password = c.getPassword();
		String phoneNo = c.getPhoneNo();
		Gender gender = c.getGender();
		String panCard = c.getPanCardNumber();
		
		if(name!=null && age!=null && phoneNo!=null && gender!=null && panCard!=null && password!=null)
			return true;
		else
			return false;
	}
	
}
