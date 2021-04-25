package com.blacksheep.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {
	
	@GetMapping("/contact-form")
	public String contactForm() {
		return "contact-form";
	}

}
