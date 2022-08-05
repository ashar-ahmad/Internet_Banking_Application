package com.training.sprint1.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "IBA_Nominee")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Nominee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long nomineeId;
	private String name;
	private String govtId;
	private GovtIdType govtIdType;
	private String phoneNo;
	private Relation relation;
	
	//Default Constructor
	public Nominee() {
		super();
		// TODO Auto-generated constructor stub
	}
	//Parameterized constructor with all attributes
	public Nominee(Long nomineeId, String name, String govtId, GovtIdType govtIdType, String phoneNo,
			Relation relation) {
		super();
		this.nomineeId = nomineeId;
		this.name = name;
		this.govtId = govtId;
		this.govtIdType = govtIdType;
		this.phoneNo = phoneNo;
		this.relation = relation;
	}
	
	public Nominee(String name, String govtId, GovtIdType govtIdType, String phoneNo, Relation relation) {
		super();
		this.name = name;
		this.govtId = govtId;
		this.govtIdType = govtIdType;
		this.phoneNo = phoneNo;
		this.relation = relation;
	}
	
	public Long getNomineeId() {
		return nomineeId;
	}
	public void setNomineeId(Long nomineeId) {
		this.nomineeId = nomineeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGovtId() {
		return govtId;
	}
	public void setGovtId(String govtId) {
		this.govtId = govtId;
	}
	public GovtIdType getGovtIdType() {
		return govtIdType;
	}
	public void setGovtIdType(GovtIdType govtIdType) {
		this.govtIdType = govtIdType;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public Relation getRelation() {
		return relation;
	}
	public void setRelation(Relation relation) {
		this.relation = relation;
	}
	@Override
	public String toString() {
		return "Nominee [nomineeId=" + nomineeId + ", name=" + name + ", govtId=" + govtId + ", govtIdType="
				+ govtIdType + ", phoneNo=" + phoneNo + ", relation=" + relation + "]";
	}
	
	

}
