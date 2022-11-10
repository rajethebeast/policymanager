package com.policymanager.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.policymanager.api.model.Vendor;
import com.policymanager.api.repository.VendorRepository;

@Service
public class VendorService {
@Autowired
private VendorRepository vendorRepository;

public Vendor addVendor(Vendor vendor) {
	
	return vendorRepository.save(vendor);
}

public List<Vendor> getAllVendors() {
	
	return vendorRepository.findAll();
}

public void deleteVendorById(Long id) {
	vendorRepository.deleteById(id);
	
}

public Vendor getVendorById(Long id) {
	Optional<Vendor> optional = vendorRepository.findById(id);
	if (optional.isPresent()) {
		Vendor vendor = optional.get();
		return vendor;
	}
	return null;
}

}
