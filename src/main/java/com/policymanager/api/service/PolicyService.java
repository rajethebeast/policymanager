package com.policymanager.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.policymanager.api.model.Policy;
import com.policymanager.api.repository.PolicyRepository;

@Service
public class PolicyService {
	@Autowired
	private PolicyRepository policyRepository;

	public Policy addPolicy(Policy policy) {

		return policyRepository.save(policy);
	}

	public List<Policy> getAllPolicies() {

		return policyRepository.findAll();
	}

	public Policy getPolicyById(Long id) {
		Optional<Policy> optional = policyRepository.findById(id);
		if (optional.isPresent()) {
			Policy policy = optional.get();
			return policy;
		}
		return null;
	}

	public List<Policy> getPolicyByCategory(String category) {

		return policyRepository.findByCategory(category);
	}

	public List<Policy> getPolicyByCategoryLife() {
		String auto = "Life Insurance";
		String status = "APPROVED";
		return policyRepository.findAuto(auto, status);
	}

	public List<Policy> getPolicyByCategoryHealth() {
		String auto = "Health Insurance";
		String status = "APPROVED";
		return policyRepository.findAuto(auto, status);
	}

	/*
	 * public List<Policy> getPolicyByStatus(String status) {
	 * 
	 * return policyRepository.findByStatus(status); }
	 */

}
