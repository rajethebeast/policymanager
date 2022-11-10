package com.policymanager.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.policymanager.api.model.Admin;
import com.policymanager.api.repository.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepository;

	public Admin addAdmin(Admin admin) {
		
		return adminRepository.save(admin);
	}


	public List<Admin> getAllAdmins() {
		return adminRepository.findAll();
	}


	public void deleteAdminById(Long id) {
		adminRepository.deleteById(id);
	}

}
