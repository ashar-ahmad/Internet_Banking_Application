package com.training.sprint1.services;

import java.util.HashSet;
import java.util.Set;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.sprint1.entities.Beneficiary;
import com.training.sprint1.exceptions.BeneficiaryNotFoundException;
import com.training.sprint1.repo.IBeneficiaryRepository;

@Service
@Transactional
public class BeneficiaryServiceImpl implements IBeneficiaryService{
	@Autowired
    IBeneficiaryRepository iBeneficiaryRepository;
	@Override
	public Beneficiary addBeneficiary(Beneficiary beneficiary) {
		iBeneficiaryRepository.save(beneficiary);
		return iBeneficiaryRepository.save(beneficiary); 
	}

	@Override
	public Beneficiary updateBeneficiary(Beneficiary beneficiary,Long beneficiaryId)throws BeneficiaryNotFoundException {
		Beneficiary retBeneficiary=null;
		Beneficiary updatedBen=null;
		try {
			 retBeneficiary=iBeneficiaryRepository.findById(beneficiaryId).orElseThrow(BeneficiaryNotFoundException::new);
			 
			 retBeneficiary.setAccountType(beneficiary.getAccountType());
			 retBeneficiary.setBeneficiaryAccNo(beneficiary.getBeneficiaryAccNo());
					
			 retBeneficiary.setBeneficiaryName(beneficiary.getBeneficiaryName());
			 retBeneficiary.setIfsc(beneficiary.getIfsc());
			 updatedBen=iBeneficiaryRepository.save(retBeneficiary);			
		}
		catch(BeneficiaryNotFoundException e)
		{
			e.printStackTrace();
		}	
		
		return updatedBen;
	}

	@Override
	public boolean deleteBeneficiary(Long beneficiaryId)throws BeneficiaryNotFoundException {
		Beneficiary delbeneficiary=null;
		try {
		 delbeneficiary=iBeneficiaryRepository.findByBeneficiaryId(beneficiaryId);
		if(delbeneficiary != null)
		{
			iBeneficiaryRepository.delete(delbeneficiary);
		}
		else
		{			
			throw new BeneficiaryNotFoundException();
		}
		}
		catch(BeneficiaryNotFoundException e)
		{			
			e.printStackTrace();
			return false;
					
		}		
		return true; 
	}

	@Override
	public Beneficiary findBeneficiaryById(Long beneficiaryId)throws BeneficiaryNotFoundException {
		// TODO Auto-generated method stub
		Beneficiary beneficiaryById=null;
		try {
			beneficiaryById=iBeneficiaryRepository.findByBeneficiaryId(beneficiaryId);
			if(beneficiaryById==null)
			{
				throw new BeneficiaryNotFoundException();
			}
		}
		catch(BeneficiaryNotFoundException e)
		{
			e.printStackTrace();
		}
		return beneficiaryById;
	}

	@Override
	public Set<Beneficiary> listAllBeneficiaries() {
		// TODO Auto-generated method stub
		
		return new HashSet<Beneficiary>(iBeneficiaryRepository.findAll());
	}
	

	
	
	

}
