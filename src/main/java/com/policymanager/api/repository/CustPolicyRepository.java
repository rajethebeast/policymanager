package com.policymanager.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.policymanager.api.model.CustPolicy;

public interface CustPolicyRepository extends JpaRepository<CustPolicy, Long>{

}
