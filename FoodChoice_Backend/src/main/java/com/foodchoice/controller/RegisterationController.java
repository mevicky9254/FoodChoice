package com.foodchoice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/all")
public class RegisterationController {
	
	 @Autowired
	    private UserService userService;
	 
	 @Autowired
	 private PasswordEncoder passwordEncoder;
	 
	 @GetMapping("/hello")
		public String testHandler() {
			return "Welcome to Spring Security";
		}
	    
	    
	    @PostMapping("/register")
	    public ResponseEntity<Customer> registerUser(@RequestBody Customer user) {
	    	
	        try {
	        	Customer eUser=userService.getUserByUsername(user.getUsername());
	        	
	        	Customer newUser=null;
	        	
	        	if(eUser==null) {
	        		
	        		user.setPassword(passwordEncoder.encode(user.getPassword()));
	        		user.setRole("ROLE_USER");
	        		user.setUsername(user.getEmail());
	        		newUser=userService.createUser(user);
	        		
	        	}
	        	
	        	return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
	           
	        } catch (UserException e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	        }
	    }


	 
	 

}
