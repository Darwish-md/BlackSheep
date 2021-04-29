package com.blacksheep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.blacksheep.POJOS.Profile;
import com.blacksheep.domain.Customer;
import com.blacksheep.repositories.CustomerRepository;

@Service
public class ProfileService {



	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private CustomerRepository customerRepo;

	public void editCustomerProfile(Customer customer,  Profile profile) {
		Customer myCustomer = customerRepo.findById(customer.getId()).orElse(null);
		myCustomer.setFirstName(profile.getFirstName());
		myCustomer.setLastName(profile.getLastName());
		myCustomer.setEmail(profile.getEmail());

		String encodedPassword = encoder.encode(profile.getPassword());
		myCustomer.setPassword(encodedPassword);

		myCustomer.setStreetAddress(profile.getStreetAddress());
		myCustomer.setPhone(profile.getPhone());
		myCustomer.setCity(profile.getCity());
		myCustomer.setState(profile.getState());
		myCustomer.setPostalCode(profile.getPostalCode());
		customerRepo.save(myCustomer);
	}
}
