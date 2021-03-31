package com.blacksheep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.blacksheep.repositories.CustomerRepository;

public class CustomerDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private CustomerRepository customerRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		customerRepo.findByEmail(email);
		return null;
		
	}

}
