package com.policymanager.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.policymanager.api.model.User;
import com.policymanager.api.repository.UserRepository;
@Service
public class MyUserDetailsService implements UserDetailsService  {
@Autowired
private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		//System.out.println(user);
		if(user == null)
			throw new UsernameNotFoundException("Invalid Credentials");
		SimpleGrantedAuthority sga = new SimpleGrantedAuthority(user.getRole());
		List<GrantedAuthority> list = new ArrayList<>();
		list.add(sga);
		 
		return new org.springframework.security.core.userdetails.User
				(user.getUsername(),user.getPassword(),list);
	}

}
