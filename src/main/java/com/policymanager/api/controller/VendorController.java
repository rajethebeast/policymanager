package com.policymanager.api.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.policymanager.api.dto.ResPolicyDto;
import com.policymanager.api.dto.ResVendorDto;
import com.policymanager.api.model.Customer;
import com.policymanager.api.model.Policy;
import com.policymanager.api.model.User;
import com.policymanager.api.model.Vendor;
import com.policymanager.api.repository.PolicyRepository;
import com.policymanager.api.repository.UserRepository;
import com.policymanager.api.repository.VendorRepository;
import com.policymanager.api.service.PolicyService;
import com.policymanager.api.service.VendorService;

@RestController
@RequestMapping("app/vendor")
@CrossOrigin(origins = { "http://localhost:1929" })
public class VendorController {
	@Autowired
	private VendorService vendorService;

	@Autowired
	private PolicyService policyService;

	@Autowired
	private PolicyRepository policyRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private VendorRepository vendorRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ResVendorDto resVendorDto;

	@PostMapping("/add")
	public Vendor addVendor(@RequestBody Vendor vendor) {
		User user = vendor.getUser();
		if (user == null) {
			throw new RuntimeException("User Data not present");
		}
		user.setRole("VENDOR");
		String encryptedPassword = passwordEncoder.encode(user.getPassword());

		user.setPassword(encryptedPassword);
		user = userRepository.save(user);
		vendor.setUser(user);
		return vendorService.addVendor(vendor);
	}

	@GetMapping("/all")
	public List<Vendor> getAllVendor() {
		return vendorService.getAllVendors();
	}

	@PutMapping("/update")
	public Vendor editVendor(Principal principal, @RequestBody Vendor vendor) {
		String username = principal.getName();
		Vendor vendorDB = vendorRepository.getVendorByUsername(username);
		if (vendorDB == null)
			throw new RuntimeException("Invalid ID");

		vendorDB.setName(vendor.getName());
		vendorDB.setAddress(vendor.getAddress());
		vendorDB.setContact(vendor.getContact());

		vendorDB = vendorService.addVendor(vendorDB);
		return vendorDB;
	}


	@GetMapping("/one")
	public ResVendorDto getSingleVendor(Principal principal) {
		String username = principal.getName();
		/* Go to manager repo and fetch details by username */
		Vendor vendor = vendorRepository.getVendorDetails(username);
		resVendorDto.setId(vendor.getId());
		resVendorDto.setName(vendor.getName());
		resVendorDto.setAddress(vendor.getAddress());
		resVendorDto.setContact(vendor.getContact());
		resVendorDto.setUsername(vendor.getUser().getUsername());
		resVendorDto.setRole(vendor.getUser().getRole());
		return resVendorDto;
	}

	/*---------POLICY CONFIGURATIONS----------*/

	@GetMapping("/policy/all")
	public List<ResPolicyDto> getAllPolicy(Principal principal) {
		String username = principal.getName();
		List<Policy> list = policyRepository.getPolicyByUsername(username);
		List<ResPolicyDto> listDto = ResPolicyDto.convertToDto(list);
		return listDto;
	}

	@PutMapping("/policy/edit/{id}")
	public Policy editPolicy(@PathVariable("id") Long id, @RequestBody Policy policyNew) {
		Policy policyDB = policyService.getPolicyById(id);
		if (policyDB == null)
			throw new RuntimeException("Invalid ID");

		policyDB.setName(policyNew.getName());
		policyDB.setCategory(policyNew.getCategory());
		policyDB.setDuration(policyNew.getDuration());
		policyDB.setInitialDeposit(policyNew.getInitialDeposit());
		//policyDB.setUserType(policyNew.getUserType());
		policyDB.setTermPerYear(policyNew.getTermPerYear());
		//policyDB.setTermAmount(policyNew.getTermAmount());
		policyDB.setRateOfInterest(policyNew.getRateOfInterest());

		policyDB = policyService.addPolicy(policyDB);
		return policyDB;
	}

	/* policy by vendor */
	@GetMapping("/policy/{vid}/{pid}")
	public Vendor buyPolicy(@PathVariable("vid") Long vid, @PathVariable("pid") Long pid) {
		/* fetch the customer details */
		Optional<Vendor> optionalV = vendorRepository.findById(vid);
		if (!optionalV.isPresent())
			throw new RuntimeException("Customer ID Invalid");
		Vendor vendor = optionalV.get();

		/* fetch the policy details */
		Optional<Policy> optionalP = policyRepository.findById(pid);
		if (!optionalP.isPresent())
			throw new RuntimeException("Policy ID Invalid");
		Policy policy = optionalP.get();

		/* Attach the policy to customer */
		List<Policy> list = vendor.getPolicy(); // list of policies that customer has
		list.add(policy); // add new policy to the list
		vendor.setPolicy(list); // attach the updated list to customer

		/* save the customer as its policies have been updated */
		return vendorRepository.save(vendor);

	}

	@PostMapping("/policy/add")
	public void postPolicy(@RequestBody Policy policy, Principal principal) {
		String username = principal.getName();
		/* Fetch employee by username */
		Vendor vendor = vendorRepository.getVendorByUsername(username);
		/* Set other properties of leave model */
		long zero=0;
		policy.setBuyCount(zero);
		policy.setVendor(vendor);

		policyRepository.save(policy);
	}

	@GetMapping("/policy/vendor/{vid}")
	public List<Policy> getVendorById(@PathVariable("vid") Long vid) {
		Vendor vendor = vendorService.getVendorById(vid);
		if (vendor == null)
			throw new RuntimeException("Invalid Vendor ID");
		List<Policy> list = vendor.getPolicy();
		return list;

	}
	/*
	@GetMapping("/customer/all/{id}")
	public void getCustomerByPolicyId(@PathVariable("id") Long id) {
		List<Customer> clist = new ArrayList<Customer>();
		
		Optional<Policy> optional = policyRepository.findById(id);
		if (!optional.isPresent())
			throw new RuntimeException("Customer ID Invalid");
		Policy policy = optional.get();
		
		List<ResCustomerDto> dto = pol
	}*/
	

}
