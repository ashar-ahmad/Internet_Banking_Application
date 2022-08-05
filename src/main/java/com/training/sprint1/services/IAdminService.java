package com.training.sprint1.services;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.training.sprint1.entities.Admin;
import com.training.sprint1.entities.SavingAccount;
import com.training.sprint1.entities.TermAccount;
import com.training.sprint1.exceptions.AdminNotFoundException;

@Service
public interface IAdminService {

	public Admin addAdmin(Admin admin);
	public Admin findAdminById(Long adminId) throws AdminNotFoundException;
	public Admin updateAdmin(Admin admin, Long adminID) throws AdminNotFoundException;
	public boolean removeAdmin(Long adminId) throws AdminNotFoundException;
	public Set<Admin> listAllAdmins ();
	public void setSavingAccFine(SavingAccount savingAccount,Double fine);
	public void setTermAccPenalty(TermAccount termAccount,Double penalty);
	
}
