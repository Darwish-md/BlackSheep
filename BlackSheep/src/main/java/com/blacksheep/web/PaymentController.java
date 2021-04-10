package com.blacksheep.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.blacksheep.domain.Customer;
import com.blacksheep.exceptions.InternalServerErrorException;
import com.blacksheep.service.PaymentCustomerForm;
import com.blacksheep.service.PaymentService;


@Controller
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@GetMapping("/customers_checkout")
	public String checkoutForm() {
		return "payment_customers";
	}

	@PostMapping("/payment_customers")
	public String processcustomerPayment(@AuthenticationPrincipal Customer customer, @RequestBody PaymentCustomerForm paymentCustomerForm ) throws InternalServerErrorException{
		try {
			System.out.println(paymentCustomerForm.getItems()[0]);
			
			return "redirect:/";
			
			}
		catch (Exception e) {
			//below assignment disables the improved rethrow exception type checking feature of Java 7
			// e=new ThirdException();
			throw e;
		}
	}
	
}
