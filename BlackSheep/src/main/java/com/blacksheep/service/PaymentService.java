package com.blacksheep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blacksheep.repositories.OrderItemRepository;
import com.blacksheep.repositories.OrderRepository;

@Service
public class PaymentService {
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private OrderItemRepository orderItemRepo;
	
	

	
	public void recordNeworder() {
		
		
	}
	
}
