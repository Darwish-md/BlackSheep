package com.blacksheep.web;

import java.util.Date;
import java.util.Optional;

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
import com.blacksheep.domain.Order;
import com.blacksheep.domain.OrderItem;
import com.blacksheep.domain.OrderItemId;
import com.blacksheep.domain.Product;
import com.blacksheep.exceptions.InternalServerErrorException;
import com.blacksheep.repositories.OrderItemRepository;
import com.blacksheep.repositories.OrderRepository;
import com.blacksheep.repositories.ProductRepository;
import com.blacksheep.service.PaymentService;


@Controller
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private OrderItemRepository orderItemRepo;
	
	@Autowired
	private OrderRepository orderRepo;
	
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
	public String processcustomerPayment(@AuthenticationPrincipal Customer customer, @RequestBody PaymentCustomerForm paymentCustomerForm ) throws InternalServerErrorException{
		try {
			Order newOrder = new Order();
			orderRepo.save(newOrder);
			Order orderToInsert = orderRepo.findById(newOrder.getId()).orElse(null);
			for (int i=0; i< paymentCustomerForm.getItems().size(); i++) {
				Product product = productRepo.findById(Long.valueOf(paymentCustomerForm.getItems().get(i).getId())).orElse(null);
				OrderItemId orderItemId = new OrderItemId();
				OrderItem orderItem = new OrderItem();
				orderItemId.setOrder(orderToInsert);
				orderItemId.setProduct(product);
				orderItem.setPk(orderItemId);
				orderItem.setQuantity(paymentCustomerForm.getItems().get(i).getNo());
				orderItem.setUnitPrice(paymentCustomerForm.getItems().get(i).getPrice());
				orderItemRepo.save(orderItem);
				System.out.println(orderItem);
			}
			
			orderToInsert.setOrderNumber("BlackSheep" + orderToInsert.getId());
			orderToInsert.setCustomer(customer);
			orderToInsert.setTotalCost(paymentCustomerForm.getTotalPrice());
//			To make it in readable format:
//			SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
//			formatter.format(date);
			Date date = new Date(System.currentTimeMillis());
			orderToInsert.setOrderDate(date);
			orderToInsert.setStreetAddress(paymentCustomerForm.getStreetAddress());
			orderToInsert.setCity(paymentCustomerForm.getCity());
			orderToInsert.setState(paymentCustomerForm.getState());
			orderToInsert.setPostalCode(paymentCustomerForm.getPostalCode());
			System.out.println(orderToInsert);
			
			return "redirect:/";
			
			}
		catch (Exception e) {
			//below assignment disables the improved rethrow exception type checking feature of Java 7
			// e=new ThirdException();
			throw e;
		}
	}
	
	
	@PostMapping("/payment_guests")
	@Transactional
	public String processGuestPayment(@RequestBody PaymentGuestForm paymentguestForm) throws InternalServerErrorException{
		
		return "redirect:/";
}
}
