package com.blacksheep.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactFormController {
	
	@GetMapping("/contact-form")
	public String contactForm() {
		return "contact_form";
	}

}