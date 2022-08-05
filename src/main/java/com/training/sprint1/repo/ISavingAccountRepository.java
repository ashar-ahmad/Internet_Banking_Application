package com.training.sprint1.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.sprint1.entities.SavingAccount;

public interface ISavingAccountRepository extends JpaRepository<SavingAccount,Long> {

}
