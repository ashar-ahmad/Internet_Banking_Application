package com.training.sprint1.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.training.sprint1.entities.Beneficiary;
import com.training.sprint1.exceptions.BeneficiaryNotFoundException;

@Service
public interface IBeneficiaryService {
	public Beneficiary addBeneficiary(Beneficiary beneficiary);
	public Beneficiary updateBeneficiary(Beneficiary beneficiary,Long beneficiaryId) throws BeneficiaryNotFoundException;
	public boolean deleteBeneficiary(Long beneficiaryId) throws BeneficiaryNotFoundException;
	public Beneficiary findBeneficiaryById(Long beneficiaryId) throws BeneficiaryNotFoundException;
	public Set <Beneficiary> listAllBeneficiaries();
	

}
