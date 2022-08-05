//package com.training.sprint1.controllers;
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
//import com.training.sprint1.entities.Admin;
//
//import com.training.sprint1.exceptions.AdminNotFoundException;
//import com.training.sprint1.services.AdminServiceImpl;
//
//@RestController
//@RequestMapping("/api/admin/jaya")
//public class JayaController {
//	
//	@Autowired
//	AdminServiceImpl adminService;
//	
//	@PostMapping
//	public ResponseEntity<Admin> addAdmin(@RequestBody Admin ad)
//	{
//		Admin addedAdmin = adminService.addAdmin(ad);
//		return new ResponseEntity<Admin>(addedAdmin,HttpStatus.OK);
//	}
//
//	@GetMapping
//	public ResponseEntity<Set<Admin>> listAllAdmins()
//	{
//		return new ResponseEntity<Set<Admin>>(adminService.listAllAdmins(),HttpStatus.OK);
//	}
//	
//	@GetMapping("/id")
//	public ResponseEntity<Admin> findAdminById(@PathVariable Long Id) throws AdminNotFoundException
//	{
//		return new ResponseEntity<Admin>(adminService.findAdminById(Id),HttpStatus.OK);
//	}
//	
//	@PutMapping
//	public ResponseEntity<Admin> updateAdmin(@RequestBody Admin ad,@PathVariable Long id) throws AdminNotFoundException
//	{
//		return new ResponseEntity<Admin>(adminService.updateAdmin(ad, id),HttpStatus.OK);
//	}
//	
//	
//	
//	@DeleteMapping
//	public ResponseEntity<Boolean> removeAdmin(@PathVariable Long id) throws AdminNotFoundException
//	{
//		return new ResponseEntity<Boolean>(adminService.removeAdmin(id),HttpStatus.OK);
//	}
//}
