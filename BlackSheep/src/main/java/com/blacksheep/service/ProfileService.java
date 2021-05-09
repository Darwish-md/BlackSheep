package com.blacksheep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.blacksheep.POJOS.Profile;
import com.blacksheep.domain.Customer;
import com.blacksheep.repositories.CustomerRepository;

@Service
public class ProfileService {

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private CustomerRepository customerRepo;

	public void editCustomerProfile(Customer customer, Profile profile) {
		Customer myCustomer = customerRepo.findById(customer.getId()).orElse(null);
		myCustomer.editCustomerDetails(profile.getFirstName(), profile.getLastName(), profile.getEmail(),
				profile.getPhone(), profile.getStreetAddress(), profile.getCity(), profile.getState(),
				profile.getPostalCode());
		customerRepo.save(myCustomer);
	}
}
