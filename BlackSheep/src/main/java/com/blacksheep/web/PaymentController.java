package com.blacksheep.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.blacksheep.POJOS.PaymentCustomerForm;
import com.blacksheep.POJOS.PaymentGuestForm;
import com.blacksheep.domain.Customer;
import com.blacksheep.exceptions.InternalServerErrorException;
import com.blacksheep.service.PaymentService;

@Controller
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@GetMapping("/customers_checkout")
	public String customersCheckoutForm(ModelMap model, @AuthenticationPrincipal Customer customer) {
		model.put("customer", customer);
		return "payment_customers";
	}

	@GetMapping("/guests_checkout")
	public String guestsCheckoutForm() {
		return "payment_guests";
	}

	@PostMapping("/payment_customers")
	@Transactional
	public String processcustomerPayment(@AuthenticationPrincipal Customer customer,
			@RequestBody PaymentCustomerForm paymentCustomerForm) throws InternalServerErrorException {
		try {
			paymentService.recordNewCustomerOrder(customer, paymentCustomerForm);
			return "redirect:/";
		} catch (Exception e) {
			throw e;
		}
	}

	@PostMapping("/payment_guests")
	@Transactional
	public String processGuestPayment(@RequestBody PaymentGuestForm paymentguestForm)
			throws InternalServerErrorException {
		try {
			paymentService.recordNewGuestOrder(paymentguestForm);
			return "redirect:/products";
		} catch (Exception e) {
			throw e;
		}
	}
}
