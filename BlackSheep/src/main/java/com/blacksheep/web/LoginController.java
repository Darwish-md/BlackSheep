package com.blacksheep.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.blacksheep.domain.Customer;
import com.blacksheep.service.CustomerService;

@Controller
public class LoginController  {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/login")
	public String loginView() {
		return "login";
	}

	@GetMapping("/register")
	public String registerationView(ModelMap model) {
		model.put("customer", new Customer());
		return "register";
	}
	
	@PostMapping("/register")
	public String registerationPost(@ModelAttribute Customer customer) {
	    customerService.save(customer);
		return "redirect:/login";
	}
}
