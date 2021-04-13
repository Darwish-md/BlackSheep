package com.blacksheep.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blacksheep.domain.Customer;
import com.blacksheep.repositories.CustomerRepository;
import com.blacksheep.security.Authority;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public Customer save (Customer customer) {
		String encodedPassword = encoder.encode(customer.getPassword());
		customer.setPassword(encodedPassword);
		
		Authority authority = new Authority();
		authority.setAuthority("ROLE_CUSTOMER");
		authority.setCustomer(customer);
		
		customer.getAuthorities().add(authority);
		
		return customerRepo.save(customer);
	}
	
}
