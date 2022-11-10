package com.policymanager.api.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.policymanager.api.dto.PolicyDetailDto;
import com.policymanager.api.dto.ResCustPolicyDto;
import com.policymanager.api.dto.ResCustomerDto;
import com.policymanager.api.dto.ResponseDto;
import com.policymanager.api.model.CustPolicy;
import com.policymanager.api.model.Customer;
import com.policymanager.api.model.Policy;
import com.policymanager.api.model.User;
import com.policymanager.api.repository.CustPolicyRepository;
import com.policymanager.api.repository.CustomerRepository;
import com.policymanager.api.repository.PolicyRepository;
import com.policymanager.api.repository.UserRepository;
import com.policymanager.api.service.CustomerService;
import com.policymanager.api.service.PolicyService;

@RestController
@CrossOrigin(origins = { "http://localhost:1929" })
@RequestMapping("app/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private PolicyRepository policyRepository;
	@Autowired
	private CustPolicyRepository custPolicyRepository;
	@Autowired
	private PolicyService policyService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private ResponseDto response;
	@Autowired
	private ResCustomerDto resCustomerDto;
	
	Logger logger = LoggerFactory.getLogger(LogController.class);

	@PostMapping("/add")
	public ResponseEntity<Object> addCustomer(@RequestBody Customer customer) {
		User user = customer.getUser();
		if (user == null) {
			logger.error("User not Found");
			return ResponseEntity.status(400).body("User Data Not Present");
		}
		user.setRole("CUSTOMER");
		String encryptedPassword = passwordEncoder.encode(user.getPassword());

		user.setPassword(encryptedPassword);
		user = userRepository.save(user);
		customer.setUser(user);
		customer.setAge(Period.between(customer.getDob(), LocalDate.now()).getYears());
		customerRepository.save(customer);
		response.setMsg("Sign Up Success");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/all")
	public List<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}

	@GetMapping("/policy/exp")
	public List<Policy> getPolCustomer(Principal principal) {
		String username = principal.getName();

		Customer customer = customerRepository.getByUsername(username);
		// List<CustPolicy> list = customer.getPolicy();
		return null;
		// return list;
	}

	@GetMapping("/cust-policy/all")
	public List<PolicyDetailDto> getAllCustPolicy(Principal principal) {

		String username = principal.getName();
		Customer customerDB = customerRepository.getByUsername(username);
		
		List<PolicyDetailDto> detailList = new ArrayList<>();
		List<CustPolicy> list = customerDB.getCustPolicy();
		for (CustPolicy cp : list) {

			Long id = cp.getPolicyId();
			Optional<Policy> optionalP = policyRepository.findById(id);
			if (!optionalP.isPresent())
				throw new RuntimeException("Policy ID Invalid");
			Policy policy = optionalP.get();

			List<PolicyDetailDto> listDto = PolicyDetailDto.convertToDto(policy,cp.getStartDate(),cp.getEndDate(),cp.getMaturityAmount(),cp.getTermAmount());
			detailList.addAll( listDto);
			
		}

		return detailList;
	}

	@GetMapping("/policy/one/{id}")
	public Policy getAllPolicy(@PathVariable("id") Long id) {

		Optional<Policy> optionalP = policyRepository.findById(id);
		if (!optionalP.isPresent())
			throw new RuntimeException("Policy ID Invalid");

		Policy policy = optionalP.get();
		return policy;

	}

	@PutMapping("/update")
	public Customer editCustomer(Principal principal, @RequestBody Customer customer) {
		String username = principal.getName();
		Customer customerDB = customerRepository.getByUsername(username);
		if (customerDB == null)
			throw new RuntimeException("Invalid ID");

		customerDB.setFirstName(customer.getFirstName());
		customerDB.setLastName(customer.getLastName());
		customerDB.setDob(customer.getDob());
		customerDB.setAddress(customer.getAddress());
		customerDB.setSalary(customer.getSalary());
		customerDB.setContactNo(customer.getContactNo());
		customerDB.setPanNo(customer.getPanNo());
		customerDB.setEmail(customer.getEmail());
		customerDB.setAge(Period.between(customer.getDob(), LocalDate.now()).getYears());

		customerDB = customerService.addCustomer(customerDB);
		return customerDB;
	}

	@GetMapping("/one")
	public ResCustomerDto getSingleCustomer(Principal principal) {
		String username = principal.getName();

		Customer customer = customerRepository.getByUsername(username);

		resCustomerDto.setId(customer.getId());
		resCustomerDto.setFirstName(customer.getFirstName());
		resCustomerDto.setLastName(customer.getLastName());
		resCustomerDto.setDob(customer.getDob());
		resCustomerDto.setAge(customer.getAge());
		resCustomerDto.setAddress(customer.getAddress());
		resCustomerDto.setSalary(customer.getSalary());
		resCustomerDto.setContactNo(customer.getContactNo());
		resCustomerDto.setPanNo(customer.getPanNo());
		resCustomerDto.setEmail(customer.getEmail());
		resCustomerDto.setEmployerName(customer.getEmployerName());
		resCustomerDto.setEmployerType(customer.getEmployerType());
		resCustomerDto.setUsername(customer.getUser().getUsername());

		return resCustomerDto;
	}

	/*--------------------- Policy --------------------------*/
	@GetMapping("/buy/{pid}")
	public Customer buyPolicy(Principal principal, @PathVariable("pid") Long pid) {
		String username = principal.getName();

		Customer customer = customerRepository.getByUsername(username);
		resCustomerDto.setId(customer.getId());
		resCustomerDto.setFirstName(customer.getFirstName());
		resCustomerDto.setLastName(customer.getLastName());
		resCustomerDto.setDob(customer.getDob());
		resCustomerDto.setAddress(customer.getAddress());
		resCustomerDto.setSalary(customer.getSalary());
		resCustomerDto.setContactNo(customer.getContactNo());
		resCustomerDto.setPanNo(customer.getPanNo());
		resCustomerDto.setEmail(customer.getEmail());
		resCustomerDto.setEmployerName(customer.getEmployerName());
		resCustomerDto.setEmployerType(customer.getEmployerType());
		resCustomerDto.setUsername(customer.getUser().getUsername());
		Optional<Policy> optionalP = policyRepository.findById(pid);
		if (!optionalP.isPresent())
			throw new RuntimeException("Policy ID Invalid");

		Policy policy = optionalP.get();
		policy.setBuyCount(policy.getBuyCount() + 1);

		CustPolicy custPolicy = new CustPolicy();
		custPolicy.setStartDate(LocalDate.now());
		custPolicy.setEndDate(LocalDate.now().plusYears(policy.getDuration()));
		custPolicy.setPolicyId(policy.getId());
		custPolicyRepository.save(custPolicy);

		int cage = customer.getAge();
		String temp = null;
		if (cage >= 18 && cage < 30)
			temp = "a";
		else if (cage >= 30 && cage < 40) {
			temp = "b";
		} else if (cage >= 40 && cage < 50) {
			temp = "c";
		} else
			temp = "d";

		switch (temp) {
		case "a":
			custPolicy.setTermAmount(policy.getSlab1());
			break;
		case "b":
			custPolicy.setTermAmount(policy.getSlab2());
			break;
		case "c":
			custPolicy.setTermAmount(policy.getSlab3());
			break;
		case "d":
			custPolicy.setTermAmount(policy.getSlab4());
			break;
		default:
			break;
		}
		double p=policy.getInitialDeposit()+(policy.getDuration()*policy.getTermPerYear()*custPolicy.getTermAmount());
		double v =(policy.getDuration()*policy.getTermPerYear()*custPolicy.getTermAmount());
		custPolicy.setMaturityAmount(p+v*(policy.getRateOfInterest()/100)+policy.getInitialDeposit()*policy.getDuration());
		//Maturity amount = (initial deposit) + (duration * term_in_years * term amount) + 
				//((duration * term_in_years * term amount) * (interest /100)+(initial deposit * duration)

		List<CustPolicy> list = customer.getCustPolicy(); // list of policies that customer has
		list.add(custPolicy); // add new policy to the list
		customer.setCustPolicy(list); // attach the updated list to customer

		return customerRepository.save(customer);
	}

	@GetMapping("/policy/{cid}")
	public List<Policy> getCustomerById(@PathVariable("cid") Long cid) {
		Customer customer = customerService.getCustomerById(cid);
		if (customer == null)
			throw new RuntimeException("Invalid Customer ID");
		// List<Policy> list = customer.getPolicy();
		return null;

	}

}
