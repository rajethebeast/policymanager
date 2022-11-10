package com.policymanager.api.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.policymanager.api.dto.ResAdminDto;
import com.policymanager.api.model.Admin;
import com.policymanager.api.model.Customer;
import com.policymanager.api.model.Policy;
import com.policymanager.api.model.User;
import com.policymanager.api.model.Vendor;
import com.policymanager.api.repository.AdminRepository;
import com.policymanager.api.repository.UserRepository;
import com.policymanager.api.service.AdminService;
import com.policymanager.api.service.CustomerService;
import com.policymanager.api.service.PolicyService;
import com.policymanager.api.service.VendorService;

@RestController
@RequestMapping("app/admin")
@CrossOrigin(origins = { "http://localhost:1929" })
public class AdminController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private ResAdminDto resAdminDto;

	@PostMapping("/add")
	public Admin addAdmin(@RequestBody Admin admin) {
		User user = admin.getUser();
		if (user == null) {
			throw new RuntimeException("User Data not present");
		}
		user.setRole("ADMIN");
		String encryptedPassword = passwordEncoder.encode(user.getPassword());

		user.setPassword(encryptedPassword);
		user = userRepository.save(user);
		admin.setUser(user);
		return adminService.addAdmin(admin);
	}

	@GetMapping("/all")
	public List<Admin> getAllCustomer() {
		return adminService.getAllAdmins();
	}

	@DeleteMapping("/delete")
	public void deleteAdminById(@RequestParam("id") Long id) {
		adminService.deleteAdminById(id);
	}

	@GetMapping("/one")
	public ResAdminDto getSingleAdmin(Principal principal) {
		String username = principal.getName();
		Admin admin = adminRepository.getAdminbyUsername(username);

		resAdminDto.setId(admin.getId());
		resAdminDto.setName(admin.getName());
		resAdminDto.setEmail(admin.getUser().getUsername());

		return resAdminDto;
	}

	/*-------------VENDOR CONFIGURATION----------------*/

	@Autowired
	private VendorService vendorService;

	@PostMapping("/vendor/add")
	public Vendor addVendor(@RequestBody Vendor vendor) {
		return vendorService.addVendor(vendor);
	}

	@GetMapping("/vendor/all")
	public List<Vendor> getAllVendor() {
		return vendorService.getAllVendors();
	}

	@DeleteMapping("/vendor/delete")
	public void deleteVendorById(@RequestParam("id") Long id) {
		vendorService.deleteVendorById(id);
	}

	/*----------------CUSTOMER CONFIGURATION----------------*/

	@Autowired
	private CustomerService customerService;

	@PostMapping("/customer/add")
	public Customer addCustomer(@RequestBody Customer customer) {
		return customerService.addCustomer(customer);
	}

	@GetMapping("/customer/all")
	public List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}

	@DeleteMapping("/customer/delete")
	public void deleteCustomerById(@RequestParam("id") Long id) {
		customerService.deleteCustomerById(id);
	}

	@Autowired
	private PolicyService policyService;

	@PutMapping("/policy/status/{pid}")
	public Policy editPolicyStatus(@PathVariable("pid") Long pid, @RequestBody Policy policyNew) {
		Policy policyDB = policyService.getPolicyById(pid);
		if (policyDB == null)
			throw new RuntimeException("Invalid policy ID");

		policyDB.setStatus(policyNew.getStatus());

		policyDB = policyService.addPolicy(policyDB);
		return policyDB;
	}

	/*
	 * @GetMapping("/policy") public List<Policy> getPolicyByStatus(@RequestParam
	 * ("status") String status){
	 * 
	 * return policyService.getPolicyByStatus(status);}
	 */

}
