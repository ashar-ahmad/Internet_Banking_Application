package com.training.sprint1.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.sprint1.entities.TermAccount;

public interface ITermAccountRepository extends JpaRepository<TermAccount,Long>{

	
}
