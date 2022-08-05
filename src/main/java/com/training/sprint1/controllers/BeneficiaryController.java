//package com.training.sprint1.controllers;
//
//
//import java.util.Set;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.training.sprint1.entities.Beneficiary;
//import com.training.sprint1.exceptions.BeneficiaryNotFoundException;
//import com.training.sprint1.services.BeneficiaryServiceImpl;
//
//@RestController
//@RequestMapping("/api/beneficiary")
//public class BeneficiaryController {
//	
//	@Autowired
//	BeneficiaryServiceImpl beneficiaryService;
//	
//	@PostMapping
//	public ResponseEntity<Beneficiary> addBeneficiary(@RequestBody Beneficiary b)
//	{
//		Beneficiary addedBeneficiary = beneficiaryService.addBeneficiary(b);
//		return new ResponseEntity<Beneficiary>(addedBeneficiary,HttpStatus.OK);
//	}
//	
//	@GetMapping
//	public ResponseEntity<Set<Beneficiary>> listAllBeneficiaries()
//	{
//		
//		return new ResponseEntity<Set<Beneficiary>>(beneficiaryService.listAllBeneficiaries(),HttpStatus.OK);
//	}
//	@PutMapping
//	public ResponseEntity<Beneficiary> updateBeneficiary(@RequestBody Beneficiary b,Long beneficiaryId) throws BeneficiaryNotFoundException
//	{
//		
//		return new ResponseEntity <Beneficiary>(beneficiaryService.updateBeneficiary(b,beneficiaryId),HttpStatus.OK);
//	}
//	@GetMapping("/{id}")
//	public ResponseEntity<Beneficiary> findBeneficiaryById(@PathVariable Long beneficiaryId) throws BeneficiaryNotFoundException
//	{
//		
//		return new ResponseEntity <Beneficiary>(beneficiaryService.findBeneficiaryById(beneficiaryId),HttpStatus.OK);
//	}
//	@DeleteMapping
//	public ResponseEntity<Boolean> deleteBeneficiary(@PathVariable Long beneficiaryId) throws BeneficiaryNotFoundException
//	{
//		
//		return new ResponseEntity<Boolean>(beneficiaryService.deleteBeneficiary(beneficiaryId),HttpStatus.OK);
//	}
//	
//	
//
//}
