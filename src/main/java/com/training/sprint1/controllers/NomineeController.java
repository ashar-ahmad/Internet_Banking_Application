//package com.training.sprint1.controllers;
//
//import java.util.List;
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
//import com.training.sprint1.entities.Customer;
//import com.training.sprint1.entities.Nominee;
//import com.training.sprint1.exceptions.NomineeNotFoundException;
//import com.training.sprint1.services.NomineeServiceImpl;
//
//@RestController
//@RequestMapping("/api/admin/nominee")
//public class NomineeController {
//	
//	@Autowired
//	NomineeServiceImpl nomineeService;
//	
//	@PostMapping
//	public ResponseEntity<Nominee> addNominee(@RequestBody Nominee nominee) throws NomineeNotFoundException
//	{
//		Nominee addedNominee = nomineeService.addNominee(nominee);
//		return new ResponseEntity<Nominee>(addedNominee,HttpStatus.OK); 
//	}
//	
//	@GetMapping
//	public ResponseEntity<List<Nominee>> listAllCustomers() throws NomineeNotFoundException
//	{
//		return new ResponseEntity<List<Nominee>>(nomineeService.listAllNominees(),HttpStatus.OK);
//	}
//	
//	@PutMapping
//	public ResponseEntity<Nominee> updateNominee(@RequestBody Nominee nominee,Long id) throws NomineeNotFoundException
//	{
//		return new ResponseEntity<Nominee>(nomineeService.updateNominee(nominee, id),HttpStatus.OK);
//	}
//	
//	@DeleteMapping
//	public ResponseEntity<Boolean> deleteNominee(@PathVariable Long nomineeId) throws NomineeNotFoundException
//	{
//		return new ResponseEntity<Boolean>(nomineeService.deleteNominee(nomineeId),HttpStatus.OK);
//	}
//	@GetMapping("/{id}")
//	public ResponseEntity<Nominee> findNomineeById(@PathVariable Long nomineeId) throws NomineeNotFoundException
//	{
//		return new ResponseEntity<Nominee>(nomineeService.findNomineeById(nomineeId),HttpStatus.OK);
//	}
//	
//
//}
