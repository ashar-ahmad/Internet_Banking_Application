package com.training.sprint1.services;


import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.sprint1.entities.Account;
import com.training.sprint1.entities.AccountType;
import com.training.sprint1.entities.Customer;
import com.training.sprint1.entities.SavingAccount;
import com.training.sprint1.entities.TermAccount;
import com.training.sprint1.exceptions.CustomerNotFoundException;
import com.training.sprint1.repo.ICustomerRepository;
import com.training.sprint1.repo.ISavingAccountRepository;
import com.training.sprint1.repo.ITermAccountRepository;


@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {
	
	
	//customer jpa repository obj
	@Autowired
	public ICustomerRepository customerRepo;
	
	//saving account repository obj 
	@Autowired
	public ISavingAccountRepository iSavingAccountRepository;
	
	
	//term account repository obj
	@Autowired
	public ITermAccountRepository iTermAccountRepository;

	
	//function to add a customer to repo
	@Override
	public Customer addCustomer(Customer c) {
		
			Customer updatedCustomer = customerRepo.save(c);	
		return updatedCustomer;
	}

	
	//func to update customer
	@Override
	public Customer updateCustomer(Customer c, Long id) throws CustomerNotFoundException {
		//after creating Exception null will be replaced
		//finding the customer by id
		Customer returnedCustomer = null;
		try {
			
			//finding and extracting the customer from repository first from repository
		 returnedCustomer = customerRepo.findById(id).orElseThrow(CustomerNotFoundException::new);
		 
		}
		catch(CustomerNotFoundException ce)
		{
			Customer errorCustomer=  new Customer();
			errorCustomer.setUserId(0L);
			return errorCustomer;
		}		
		BeanUtils.copyProperties(c, returnedCustomer,"userId");
		
		//saving the new customer in database
		Customer updatedCustomer = customerRepo.save(returnedCustomer);
		return returnedCustomer;
	}

	
	//function to delete customer--(returning true for successful deletion)
	@Override
	public String deleteCustomer(Long id) throws CustomerNotFoundException {
		
		//finding the customer first before deletion
		Customer fetchedCustomer = customerRepo.findById(id).orElseThrow(CustomerNotFoundException::new);
		if(fetchedCustomer == null)
			return "Customer was not found";
		
		else
		{
			customerRepo.delete(fetchedCustomer);
			return "Customer has been successfully removed from db";
		}
		
	}

	//function to list all customers
	@Override
	public List<Customer> listAllCustomer() {
		return customerRepo.findAll();
	}

	//fuction to find by id-------(returning CustomerNotFoundException if customer is not found)
	@Override
	public Customer findCustomerById(Long id) throws CustomerNotFoundException {
		Customer customerById=null;
		try {
		 customerById = customerRepo.findById(id).orElseThrow(CustomerNotFoundException::new); 
		}
		catch(CustomerNotFoundException ce)
		{
			return null;
		}
		return customerById;
	}

}