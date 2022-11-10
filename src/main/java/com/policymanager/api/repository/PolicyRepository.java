package com.policymanager.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.policymanager.api.dto.ResPolicyDto;
import com.policymanager.api.model.Policy;

public interface PolicyRepository extends JpaRepository<Policy, Long> {

	@Query("select p from Policy p where p.category=?1")
	List<Policy> findByCategory(String category);

	@Query("select p from Policy p where p.status=?1")
	List<Policy> getAllPendingPolicies(String status);

	@Query("select p from Policy p where p.vendor.user.username=?1")
	List<Policy> getPolicyByUsername(String username);

	//@Query("select c from Customer.policy where c.user.username=?1")
	//@Query("select p from Policy p where p.customer.user.username=?1")
	//List<Policy> getPolicyByCustomerUsername(String username);

	@Query("select p from Policy p where p.status=?1")
	List<Policy> getPolicyListByStatus(String status);

	@Query("select p from Policy p where p.category=?1 AND p.status=?2")
	List<Policy> findAuto(String auto, String status);

	// @Query("select p from Policy p, Customer c where c.user.username=?1")
	// List<Policy> getAllPoliciesByCustomerByUsername(String username);

//@Query("select l from Leave l where l.status=?2 AND l.isArchived=?3 "" AND l.employee.manager.user.username=?1")

}
