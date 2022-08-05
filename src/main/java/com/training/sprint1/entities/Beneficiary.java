package com.training.sprint1.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name ="IBA_Beneficiaries")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Beneficiary {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long beneficiaryId;
	
	private String beneficiaryName;
	private Long beneficiaryAccNo;
	private String ifsc;
	private AccountType accountType;
	public Beneficiary() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Beneficiary(Long beneficiaryId, String beneficiaryName, Long beneficiaryAccNo, String ifsc,
			AccountType accountType) {
		super();
		this.beneficiaryId = beneficiaryId;
		this.beneficiaryName = beneficiaryName;
		this.beneficiaryAccNo = beneficiaryAccNo;
		this.ifsc = ifsc;
		this.accountType = accountType;
	}
	public Beneficiary(String beneficiaryName, Long beneficiaryAccNo, String ifsc, AccountType accountType) {
		super();
		this.beneficiaryName = beneficiaryName;
		this.beneficiaryAccNo = beneficiaryAccNo;
		this.ifsc = ifsc;
		this.accountType = accountType;
	}
	
	public Long getBeneficiaryId() {
		return beneficiaryId;
	}
	public void setBeneficiaryId(Long beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}
	public String getBeneficiaryName() {
		return beneficiaryName;
	}
	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}
	public Long getBeneficiaryAccNo() {
		return beneficiaryAccNo;
	}
	public void setBeneficiaryAccNo(Long beneficiaryAccNo) {
		this.beneficiaryAccNo = beneficiaryAccNo;
	}
	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	@Override
	public String toString() {
		return "Beneficiary [beneficiaryId=" + beneficiaryId + ", beneficiaryName=" + beneficiaryName
				+ ", beneficiaryAccNo=" + beneficiaryAccNo + ", ifsc=" + ifsc + ", accountType=" + accountType + "]";
	}
	

}