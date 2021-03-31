package com.blacksheep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blacksheep.domain.Customer;
import com.blacksheep.repositories.CustomerRepository;
import com.blacksheep.security.CustomSecurity;

@Service
public class CustomerDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private CustomerRepository customerRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Customer customer = customerRepo.findByEmail(email);
		
		if (customer == null) 
				throw new UsernameNotFoundException("invalid email or password");
		
		return new CustomSecurity(customer);
		
	}

}
