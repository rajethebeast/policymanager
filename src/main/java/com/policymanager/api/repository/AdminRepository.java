package com.policymanager.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.policymanager.api.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{

	@Query("select d from Admin d where d.user.username=?1")
	Admin getAdminbyUsername(String username);

}
