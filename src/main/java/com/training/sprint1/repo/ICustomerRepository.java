package com.training.sprint1.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.sprint1.entities.Customer;
import com.training.sprint1.entities.User;


@Repository
public interface ICustomerRepository extends JpaRepository<Customer,Long> {

	User findByUserNameAndPassword(String username, String password);

	Customer findByUserName(String username);
}
