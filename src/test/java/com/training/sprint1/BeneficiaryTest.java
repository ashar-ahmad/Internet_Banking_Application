package com.training.sprint1;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import com.training.sprint1.entities.AccountType;
import com.training.sprint1.entities.Beneficiary;
import com.training.sprint1.exceptions.BeneficiaryNotFoundException;
import com.training.sprint1.repo.IBeneficiaryRepository;
import com.training.sprint1.services.BeneficiaryServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BeneficiaryTest {
	@Mock
	 IBeneficiaryRepository repo;
	@InjectMocks
	BeneficiaryServiceImpl service;
	Beneficiary b1,b2,b3;
	List<Beneficiary> bens=new ArrayList<Beneficiary>();
	
	Set<Beneficiary> beneSet=new HashSet<Beneficiary>();
	
	@BeforeEach
	public void addBeneficiary1()
	{
		MockitoAnnotations.initMocks(this);
		b1 = new Beneficiary(1001L,"Bene1",12345678L,"SBI1001",AccountType.SAVINGS_ACCOUNT);
		
	}
	
	
	@BeforeEach
	public void listAllBeneficiaries1(){
		MockitoAnnotations.initMocks(this);
		b1=new Beneficiary(1001L,"Bene1",12345678L,"SBI1001",AccountType.SAVINGS_ACCOUNT);
		b2=new Beneficiary(1001L,"Bene2",22345678L,"SBI1002",AccountType.SAVINGS_ACCOUNT);
		b3=new Beneficiary(1003L,"Bene3",32345678L,"SBI1003",AccountType.TERM_ACCOUNT);
		bens.add(b1);
		bens.add(b2);
		bens.add(b3);
		
	}
	@Test
	void testaddBeneficiary()
	{
		when(repo.save(b1)).thenReturn(b1);
		Assertions.assertEquals(b1,service.addBeneficiary(b1));
	}
	@Test
	void testAllBeneficiary()
	{

       when(repo.findAll()).thenReturn(bens);
       Assertions.assertEquals(3,bens.size());
	}
	@Test
	void testFindBenById()throws BeneficiaryNotFoundException
	{
		when(repo.findById(b1.getBeneficiaryId())).thenReturn(Optional.of(b1));
		Assertions.assertEquals(b1,service.findBeneficiaryById(b1.getBeneficiaryId()));
		
	}
	@Test
	void testupdate()throws BeneficiaryNotFoundException
	{
		when(repo.findById(b1.getBeneficiaryId())).thenReturn(Optional.of(b1));
		when(repo.save(b1)).thenReturn(b2);
		Assertions.assertEquals(b2,service.updateBeneficiary(b2,b1.getBeneficiaryId()));
		
	}
	@Test
	void testDelete()throws BeneficiaryNotFoundException
	{
		when(repo.findById(b1.getBeneficiaryId())).thenReturn(Optional.of(b1));
		Assertions.assertEquals(true,service.deleteBeneficiary(b1.getBeneficiaryId()));
	}

}
