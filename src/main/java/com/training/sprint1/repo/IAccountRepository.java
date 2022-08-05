package com.training.sprint1.repo;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.training.sprint1.entities.Account;
import com.training.sprint1.entities.AccountType;
import com.training.sprint1.entities.SavingAccount;
import com.training.sprint1.entities.Transaction;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {		

	Set<Account> findByAccountType(AccountType savingsAccount);

}
