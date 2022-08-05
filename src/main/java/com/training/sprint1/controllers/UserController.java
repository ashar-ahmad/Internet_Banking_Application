package com.training.sprint1.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.sprint1.config.TokenProvider;
import com.training.sprint1.entities.Admin;
import com.training.sprint1.entities.Customer;
import com.training.sprint1.entities.JwtRequest;
import com.training.sprint1.entities.JwtResponse;
import com.training.sprint1.entities.User;
import com.training.sprint1.repo.ICustomerRepository;
import com.training.sprint1.repo.IUserRepository;
import com.training.sprint1.services.IAdminService;
import com.training.sprint1.services.ICustomerService;
import com.training.sprint1.services.CustomUserDetailsService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowedHeaders={"Authorization","Access-Control-Request-Headers","Content-Type","Access-Control-Allow-Origin","Access-Control-Allow-Credentials","Access-Control-Allow-Headers"})
@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private ICustomerService iCustomerService;
	
	@Autowired	
	private IAdminService iAdminService;
	
	@Autowired
	private CustomUserDetailsService userDetailService;
	
	@Autowired
	private TokenProvider tokenProvider;
	
	@Autowired
	private IUserRepository iUserRepository;
	
	//api to add a Customer
	@PostMapping("/signUpC")
	public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer c)
	{
		Customer addedCustomer = iCustomerService.addCustomer(c);
		return new ResponseEntity<Customer>(addedCustomer,HttpStatus.OK);
	}
	
	//api to add a Admin
	@PostMapping("/signUpA")
	public ResponseEntity<Admin> addAdmin(@RequestBody Admin ad)
	{
		Admin addedAdmin = iAdminService.addAdmin(ad);
		return new ResponseEntity<Admin>(addedAdmin,HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> generateToekn(@RequestBody JwtRequest jReq) throws Exception
	{
		System.out.println(jReq);		
		try {
			 this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jReq.getUserName(), jReq.getPassword()));
		}
		catch(UsernameNotFoundException ue)
		{
			ue.printStackTrace();
			throw new Exception("Bad Credentials");
		}
		catch(BadCredentialsException be)
		{
			be.printStackTrace();
			throw new BadCredentialsException("Bad Credentials");
		}
	
		UserDetails userDetails=this.userDetailService.loadUserByUsername(jReq.getUserName());		
		
		
		final Authentication authentication = authenticationManager.authenticate(
        		
                new UsernamePasswordAuthenticationToken(
                       jReq.getUserName(),
                       jReq.getPassword()
                )
        );
        System.out.println(authentication.getCredentials()+"********************************");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = tokenProvider.generateToken(authentication);
        System.out.println(token+"************");
		
		System.out.println("JWT :-> "+token);
		
		return new ResponseEntity<JwtResponse>(new JwtResponse(token),HttpStatus.OK);
	}	
	
	@GetMapping("/getUser/{userName}")
	public ResponseEntity<User> getCustomerByName(@PathVariable String userName)
	{		
		User user=null;
		try {
			user = iUserRepository.findByUserName(userName);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("username not found");
		}
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}

}
