package com.training.sprint1.services;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.sprint1.entities.Admin;
import com.training.sprint1.entities.SavingAccount;
import com.training.sprint1.entities.TermAccount;
import com.training.sprint1.exceptions.AdminNotFoundException;
import com.training.sprint1.repo.IAccountRepository;
import com.training.sprint1.repo.IAdminRepository;


@Service
@Transactional
public class AdminServiceImpl implements IAdminService {

	@Autowired
	IAdminRepository iAdminRepository;
	
	@Autowired
	IAccountRepository iAccountRepository;
	
	@Override
	public Admin addAdmin(Admin admin) {
		Admin q = iAdminRepository.save(admin);
		return q;
	}

	@Override
	public Admin findAdminById(Long adminId) {
		
		return iAdminRepository.findById(adminId).orElseThrow(null);
		  
	}

	@Override
	public Admin updateAdmin(Admin admin, Long adminID) throws AdminNotFoundException {
		
		Admin returnedAdmin = null;
		Admin savedAdmin = null;
		try {
			 returnedAdmin = iAdminRepository.findById(adminID).orElseThrow(AdminNotFoundException::new);
		}
		catch (AdminNotFoundException ae) {
			
			ae.printStackTrace();
		}
		
		BeanUtils.copyProperties(admin, returnedAdmin, "userId");
		savedAdmin = iAdminRepository.save(returnedAdmin);
		return savedAdmin;
	}

	@Override
	public boolean removeAdmin(Long adminId) throws AdminNotFoundException  {
		Admin admin=null;
		try { 
			 admin=iAdminRepository.findById(adminId).orElseThrow(AdminNotFoundException::new);
			
				iAdminRepository.delete(admin);
				return true;
			}
		catch (AdminNotFoundException ae)
		{
			ae.printStackTrace();
			return false;
		}
		
	}

	@Override
	public Set<Admin> listAllAdmins() {
		List<Admin> listAdmins = iAdminRepository.findAll();
		Set<Admin> setAdmins = new HashSet<Admin>();
		for(Admin a : listAdmins )
		{
			setAdmins.add(a);
			
		}
		return setAdmins;
	}

	@Override
	public void setSavingAccFine(SavingAccount savingAccount,Double fine) {
		// TODO Auto-generated method stub
		savingAccount.setFine(fine);
	}

	@Override
	public void setTermAccPenalty(TermAccount termAccount,Double penalty) {
		// TODO Auto-generated method stub
		termAccount.setPenaltyAmount(penalty);
	}
	
	

}
