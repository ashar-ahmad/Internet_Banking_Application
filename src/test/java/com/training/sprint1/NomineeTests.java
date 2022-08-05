package com.training.sprint1;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
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

import com.training.sprint1.entities.Account;
import com.training.sprint1.entities.Customer;
import com.training.sprint1.entities.GovtIdType;
import com.training.sprint1.entities.Nominee;
import com.training.sprint1.entities.Relation;
import com.training.sprint1.exceptions.NomineeNotFoundException;
import com.training.sprint1.repo.INomineeRepository;
import com.training.sprint1.services.NomineeServiceImpl;

class NomineeTests {
	
	@Mock
	INomineeRepository iNomineeRepository;
	
	@InjectMocks
	NomineeServiceImpl service;
	
	Nominee n1,n2,n3;
	List<Nominee> nominee = new ArrayList<Nominee>();
	List<Nominee> nominees = new ArrayList<Nominee>();
	List<Nominee> temp = new ArrayList<Nominee>();
	Set<Nominee> rnominees = new HashSet<Nominee>();
	
	
	
	@BeforeEach
	public void setaddNominee()
	{
		MockitoAnnotations.initMocks(this);
		n1 = new Nominee(120L,"Rafe","764221005",GovtIdType.ADHAAR_CARD,"9652607186",Relation.BROTHER);
	}
	
	@BeforeEach
	public void setListAllNominee()
	{
		MockitoAnnotations.initMocks(this);
		n1 = new Nominee(121L,"Neha","764221005",GovtIdType.ADHAAR_CARD,"9652607186",Relation.MOTHER);
		n2 = new Nominee(122L,"Nishal","EGOPC23",GovtIdType.PAN_CARD,"9652607186",Relation.SON);
		n3 = new Nominee(123L,"Udit","764221005",GovtIdType.ADHAAR_CARD,"9652607186",Relation.FATHER);
		nominees.add(n1);
		nominees.add(n2);
		nominees.add(n3);
	}
	
	@Test
	void testaddNominee() throws NomineeNotFoundException {
		when(iNomineeRepository.save(n1)).thenReturn(n1);
		Assertions.assertEquals(n1,service.addNominee(n1));
	}
	
	@Test
	void testListAllNominee() throws NomineeNotFoundException {
		when(iNomineeRepository.findAll()).thenReturn(nominees);
        Assertions.assertEquals(3,nominees.size());
	}
	@Test
	void testUpdateNominee() throws NomineeNotFoundException {
		when(iNomineeRepository.findById(n1.getNomineeId())).thenReturn(Optional.of(n1));
		when(iNomineeRepository.save(n1)).thenReturn(n2);
		Assertions.assertEquals(n2,service.updateNominee(n2,n1.getNomineeId()));
	}
	@Test
	void testfindNomineeById() throws NomineeNotFoundException{
		when(iNomineeRepository.findById(n1.getNomineeId())).thenReturn(Optional.of(n1));
		Assertions.assertEquals(n1,service.findNomineeById(n1.getNomineeId()));
		
	}
	@Test
	void testDeleteNominee() throws NomineeNotFoundException
	{
		when(iNomineeRepository.findById(n1.getNomineeId())).thenReturn(Optional.of(n1));
		Assertions.assertEquals(true,service.deleteNominee(n1.getNomineeId()));
	}

}
