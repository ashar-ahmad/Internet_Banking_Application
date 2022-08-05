package com.training.sprint1.repo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.sprint1.entities.Account;
import com.training.sprint1.entities.Transaction;

@Repository
public interface ITransactionRepository extends JpaRepository<Transaction, Long> {	
	

	List<Transaction> findByTransactionDateTimeBetweenAndBankAccount(LocalDateTime from, LocalDateTime to,
			Account retAccount);		
	
}