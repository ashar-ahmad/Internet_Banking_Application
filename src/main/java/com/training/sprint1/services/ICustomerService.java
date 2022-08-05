package com.training.sprint1.services;

import java.util.List;


import org.springframework.stereotype.Service;

import com.training.sprint1.entities.Customer;
import com.training.sprint1.exceptions.*;
@Service
public interface ICustomerService {

	public Customer addCustomer(Customer c);
	public Customer updateCustomer(Customer c,Long id) throws CustomerNotFoundException;
	public String deleteCustomer(Long id) throws CustomerNotFoundException;
	public List<Customer> listAllCustomer();
	public Customer findCustomerById(Long id) throws CustomerNotFoundException;    //throws CustomerNotFoundException
	
}
