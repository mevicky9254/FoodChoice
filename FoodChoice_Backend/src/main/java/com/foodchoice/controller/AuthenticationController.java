package com.foodchoice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodchoice.exception.UserException;
import com.foodchoice.model.Customer;
import com.foodchoice.service.UserService;

@RestController
@RequestMapping("/auth")

public class AuthenticationController {
	
	 @Autowired
	 private UserService userService;
	
	 
	
	@GetMapping("/signin")
	public ResponseEntity<String> getLoggedInCustomerDetailsHandler(Authentication auth) throws UserException{
		
		System.out.println(auth); // this Authentication object having Principle object details
		
		 Customer customer= userService.getUserByUsername(auth.getName());
		 
		 return new ResponseEntity<>(customer.getFirstName()+"Logged In Successfully", HttpStatus.ACCEPTED);	
	}
	
	
	
	
	
	 
	
	

}
