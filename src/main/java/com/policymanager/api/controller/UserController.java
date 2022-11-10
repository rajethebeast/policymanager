package com.policymanager.api.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.policymanager.api.dto.CustomerDto;
import com.policymanager.api.dto.ResponseDto;
import com.policymanager.api.dto.UserDto;
import com.policymanager.api.dto.VendorDto;
import com.policymanager.api.model.Customer;
import com.policymanager.api.model.User;
import com.policymanager.api.model.Vendor;
import com.policymanager.api.repository.AdminRepository;
import com.policymanager.api.repository.CustomerRepository;
import com.policymanager.api.repository.UserRepository;
import com.policymanager.api.repository.VendorRepository;

@RestController
@RequestMapping("app/user")
@CrossOrigin(origins = {"http://localhost:1929"})
public class UserController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private VendorRepository vendorRepository; 
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private ResponseDto responseDto;
	
	 
	@GetMapping("/login") //<-- authenticated in security config
	public Object userLogin(Principal principal) { //<-- DI
		//At this line spring already has username and password of the user.
		
		/* Read the username from spring using Principal */
		String username = principal.getName();
		
		/* fetch user details on the basis of this username */
		User user = userRepository.findByUsername(username);
		
		if(user == null) {
			responseDto.setMsg("Invalid Credentials");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDto);
		}
		user.setPassword("");
		if(user.isEnabled() == false) {
			responseDto.setMsg("User not enabled");
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseDto);
		}
		
		UserDto dto = new UserDto(user.getUsername(),user.getRole());
		
		return dto; 
	}
	/*public Object login(Principal principal) {
		 
		String username = principal.getName();
		/* Fetch user from DB using username 
		User user = userRepository.getUserByUsername(username);
		if(user == null) {
			responseDto.setMsg("Invalid Credentials");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDto);
		}
		user.setPassword("");
		if(user.isEnabled() == false) {
			responseDto.setMsg("User not enabled");
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseDto);
		}
		return user; 
	}*/
	
	@GetMapping("/details")
	public Object getUserDetails(Principal principal) {
		//At this line spring already has username of the user.
		
		/* Read the username from spring using Principal */
		String username = principal.getName();
		
		/* fetch user details on the basis of this username */
		User user = userRepository.findByUsername(username);
		
		if(user.getRole().equalsIgnoreCase("CUSTOMER")) {
			Customer customer = customerRepository.getCustomerDetails(username);
			CustomerDto dto = new CustomerDto(customer.getId(),
											  customer.getFirstName(),
											  customer.getLastName(),
											  username);
			
			return dto; 
		}
		if(user.getRole().equalsIgnoreCase("VENDOR")) {
			Vendor vendor = vendorRepository.getVendorDetails(username);
			VendorDto dto = new VendorDto(vendor.getId(),
					vendor.getName(),
					  username);
			return dto;
		}
		if(user.getRole().equalsIgnoreCase("ADMIN")) {
			
		}
		return null;
	}
}
