package com.blacksheep.web;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.blacksheep.service.PaymentService;

@WebMvcTest(PaymentController.class)
class PaymentControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PaymentService paymentService;
	
	@Test
	void testGetUponCheckoutPage() throws Exception {
		String url = "/upon_checkout";
		MvcResult mvcResult = mockMvc.perform(get(url)).andReturn();
		assertEquals(mvcResult.getResponse().getStatus(), 200);
	}
	
		
	@Test
	void testGetGuestsCheckoutPage() throws Exception {
		String url = "/guests_checkout";
		MvcResult mvcResult = mockMvc.perform(get(url)).andReturn();
		assertEquals(mvcResult.getResponse().getStatus(), 200);
	}
	
	

}
