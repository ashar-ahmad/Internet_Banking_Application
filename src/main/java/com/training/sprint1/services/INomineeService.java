package com.training.sprint1.services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.training.sprint1.entities.Nominee;
import com.training.sprint1.exceptions.NomineeNotFoundException;

@Service
public interface INomineeService {
	public Nominee addNominee(Nominee nominee) throws NomineeNotFoundException;
	public Nominee updateNominee(Nominee nominee,Long id) throws NomineeNotFoundException;
	public boolean deleteNominee(Long nomineeId) throws NomineeNotFoundException;
	public Nominee findNomineeById(Long nomineeId) throws NomineeNotFoundException;
	public Set<Nominee> listAllNominees(Long accountId) throws NomineeNotFoundException;

}
