package com.blacksheep.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.blacksheep.POJOS.PaymentCustomerForm;
import com.blacksheep.domain.Customer;
import com.blacksheep.domain.Order;
import com.blacksheep.domain.OrderItem;
import com.blacksheep.domain.OrderItemId;
import com.blacksheep.domain.Product;
import com.blacksheep.exceptions.InternalServerErrorException;
import com.blacksheep.repositories.ProductRepository;
import com.blacksheep.service.PaymentService;


@Controller
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private ProductRepository productRepo;
	
	@GetMapping("/customers_checkout")
	public String checkoutForm() {
		return "payment_customers";
	}
  
	@PostMapping("/payment_customers")
	@Transactional
	public String processcustomerPayment(@AuthenticationPrincipal Customer customer, @RequestBody PaymentCustomerForm paymentCustomerForm ) throws InternalServerErrorException{
		try {
			Order newOrder = new Order();
			
			for (int i=0; i< paymentCustomerForm.getItems().length; i++) {
				Optional<Product> product = productRepo.findById(paymentCustomerForm.getItems()[i].getId());
				OrderItemId orderItemId = new OrderItemId();
				OrderItem orderItem = new OrderItem();
				orderItemId.setOrder(newOrder);
				orderItemId.setProduct(product);
				orderItem.setPk(orderItemId);
				orderItem.setQuantity(paymentCustomerForm.getItems()[i].getNo());
				orderItem.setUnitPrice(paymentCustomerForm.getItems()[i].getPrice());
				System.out.println(orderItemId);
			}
			
			newOrder.setOrderNumber("BlackSheep" + newOrder.getId());
			newOrder.setCustomer(customer);
			newOrder.setTotalCost(paymentCustomerForm.getTotalPrice());
//			To make it in readable format:
//			SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
//			formatter.format(date);
			Date date = new Date(System.currentTimeMillis());
			newOrder.setOrderDate(date);
			
			
			System.out.println(newOrder);
			
			return "redirect:/";
			
			}
		catch (Exception e) {
			//below assignment disables the improved rethrow exception type checking feature of Java 7
			// e=new ThirdException();
			throw e;
		}
	}
	
}
