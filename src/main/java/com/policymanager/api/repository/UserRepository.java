package com.policymanager.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.policymanager.api.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	//User findByUsername(String username);

	//User findByUsername(String userName);

}
