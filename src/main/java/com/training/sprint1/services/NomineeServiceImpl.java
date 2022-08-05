package com.training.sprint1.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.sprint1.entities.Account;
import com.training.sprint1.entities.Nominee;
import com.training.sprint1.exceptions.AccountDoesNotExistException;
import com.training.sprint1.exceptions.NomineeNotFoundException;
import com.training.sprint1.repo.IAccountRepository;
import com.training.sprint1.repo.INomineeRepository;
@Service
@Transactional
public class NomineeServiceImpl implements INomineeService{
	
	@Autowired
	public INomineeRepository iNomineeRepository;
	
	@Autowired
	public IAccountRepository iAccountRepository;

	@Override
	public Nominee addNominee(Nominee nominee) throws NomineeNotFoundException {
		// TODO Auto-generated method stub
		Nominee updatedNominee = iNomineeRepository.save(nominee);
		return updatedNominee;
		
	}

	@Override
	public Nominee updateNominee(Nominee nominee,Long id) throws NomineeNotFoundException {
		// TODO Auto-generated method stub
		Nominee returnedNominee = null;
		try {
			returnedNominee = iNomineeRepository.findById(id).orElseThrow(NomineeNotFoundException::new);
		}
		catch(NomineeNotFoundException ne)
		{
			ne.printStackTrace();
		}
		returnedNominee = iNomineeRepository.findById(id).orElseThrow(null);
		returnedNominee.setGovtId(nominee.getGovtId());
		returnedNominee.setGovtIdType(nominee.getGovtIdType());
		returnedNominee.setName(nominee.getName());
		returnedNominee.setPhoneNo(nominee.getPhoneNo());
		returnedNominee.setRelation(nominee.getRelation());
		
		Nominee updatedNominee  = iNomineeRepository.save(returnedNominee);
		return updatedNominee;
	}

	@Override
	public boolean deleteNominee(Long nomineeId) throws NomineeNotFoundException{
		// TODO Auto-generated method stub
		Nominee fetchedNominee = iNomineeRepository.findById(nomineeId).orElseThrow(NomineeNotFoundException::new);
		if(fetchedNominee == null)
			return false;
		else
		{
			iNomineeRepository.delete(fetchedNominee);
			return true;
		}
	}

	@Override
	public Nominee findNomineeById(Long nomineeId) throws NomineeNotFoundException{
		// TODO Auto-generated method stub
		Nominee nomineeById = iNomineeRepository.findById(nomineeId).orElseThrow(NomineeNotFoundException::new);
		return nomineeById;
	}

	@Override
	public Set<Nominee> listAllNominees(Long accountId) throws NomineeNotFoundException {
		// TODO Auto-generated method stub
		Account retAccount=iAccountRepository.findById(accountId).get();
		try {
		if(retAccount==null)
			throw new AccountDoesNotExistException();
		}
		catch(AccountDoesNotExistException ae)
		{
			ae.printStackTrace();
		}
		return retAccount.getNominees();
	}

}
