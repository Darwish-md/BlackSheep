package com.blacksheep.web;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.blacksheep.POJOS.Profile;
import com.blacksheep.domain.Customer;
import com.blacksheep.service.ProfileService;

@Controller
public class ProfileController {

	@Autowired
	private ProfileService profileService;

	@GetMapping("/profile")
	public String profileView(ModelMap model, @AuthenticationPrincipal Customer customer) {
		model.put("customer", customer);
		return ("profile");
	}

	@PostMapping("/edit_profile")
	public String editProfile(@AuthenticationPrincipal Customer customer, @RequestBody Profile profile) {
		try {
<<<<<<< Updated upstream
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			id = auth.getDetails();
			id=sth
		    id=sth again
			Optional<Customer> updateCustomer = customerService.getCustomerById(customer.getId());
		    
=======

			profileService.editCustomerProfile(customer, profile);
>>>>>>> Stashed changes
			return "redirect:/profile";

		} catch (Exception e) {
			throw e;
		}
	}
}
