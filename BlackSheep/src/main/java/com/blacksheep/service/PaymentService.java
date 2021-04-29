package com.blacksheep.service;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blacksheep.POJOS.PaymentCustomerForm;
import com.blacksheep.POJOS.PaymentGuestForm;
import com.blacksheep.domain.Customer;
import com.blacksheep.domain.Guest;
import com.blacksheep.domain.Order;
import com.blacksheep.domain.OrderItem;
import com.blacksheep.domain.OrderItemId;
import com.blacksheep.domain.Product;
import com.blacksheep.repositories.GuestRepository;
import com.blacksheep.repositories.OrderItemRepository;
import com.blacksheep.repositories.OrderRepository;
import com.blacksheep.repositories.ProductRepository;

@Service
public class PaymentService {

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private OrderItemRepository orderItemRepo;

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private GuestRepository guestRepo;

	public void recordNewCustomerOrder(Customer customer, PaymentCustomerForm paymentCustomerForm) {
		Order newOrder = new Order();
		orderRepo.save(newOrder);
		Order orderToInsert = orderRepo.findById(newOrder.getId()).orElse(null);
		for (int i = 0; i < paymentCustomerForm.getItems().size(); i++) {
			Product product = productRepo.findById(Long.valueOf(paymentCustomerForm.getItems().get(i).getId()))
					.orElse(null);

			OrderItemId orderItemId = new OrderItemId(product, orderToInsert);

			OrderItem orderItem = new OrderItem(orderItemId, paymentCustomerForm.getItems().get(i).getPrice(),
					paymentCustomerForm.getItems().get(i).getNo());

			orderItemRepo.save(orderItem);
		}
		
		Date date = new Date(System.currentTimeMillis());
		orderToInsert.setNewOrder("BlackSheep" + orderToInsert.getId(), date, paymentCustomerForm.getTotalPrice(),
				paymentCustomerForm.getStreetAddress(), paymentCustomerForm.getCity(), paymentCustomerForm.getState(),
				paymentCustomerForm.getPostalCode());
		
		orderToInsert.setCustomer(customer);
	}

}
