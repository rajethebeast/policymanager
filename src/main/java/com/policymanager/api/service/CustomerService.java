package com.policymanager.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.policymanager.api.model.Customer;
import com.policymanager.api.repository.CustomerRepository;
import com.policymanager.api.repository.PolicyRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	private PolicyRepository policyRepository;

	public Customer addCustomer(Customer customer) {

		return customerRepository.save(customer);
	}

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	public Customer getCustomerById(Long id) {
		Optional<Customer> optional = customerRepository.findById(id);
		if (optional.isPresent()) {
			Customer customer = optional.get();
			return customer;
		}
		return null;

	}

	public void deleteCustomerById(Long id) {
		customerRepository.deleteById(id);
	}


}
