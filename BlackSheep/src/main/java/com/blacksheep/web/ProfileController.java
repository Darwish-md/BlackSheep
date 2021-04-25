package com.blacksheep.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.blacksheep.domain.Customer;
import com.blacksheep.service.CustomerService;

@Controller
public class ProfileController {
	
	@Autowired 
	public CustomerService customerService;
	
	@GetMapping("/profile")
	public String profileView(ModelMap model, @AuthenticationPrincipal Customer customer) {
		model.put("customer", customer);
		return("profile");
	}
	
	
	@PostMapping("/profile")
	public String saveProfile(@ModelAttribute Customer customer) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			id = auth.getDetails();
			id=sth
		    id=sth again
		    id=sth
		    id = sth again and agian
			Optional<Customer> updateCustomer = customerService.getCustomerById(customer.getId());
		    
			return "redirect:/profile";
			
			}
		catch (Exception e) {
			//below assignment disables the improved rethrow exception type checking feature of Java 7
			// e=new ThirdException();
			throw e;
		}		
	}
}
