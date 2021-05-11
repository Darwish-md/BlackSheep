package com.blacksheep.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.blacksheep.POJOS.PaymentGuestForm;
import com.blacksheep.domain.Guest;
import com.blacksheep.service.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@WebMvcTest(PaymentController.class)
class PaymentControllerPostRequestTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired 
	WebApplicationContext webApplicationContext;
	
	@MockBean
	private PaymentService paymentService;
	

	@BeforeEach()
	public void setup() {
	    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	
	@Test
	void testProcessGuestPayment() throws Exception {
		PaymentGuestForm guestPaymentDetails = new PaymentGuestForm(null, 2756f, "Mahmoud Darwish", "darwish@test.com", 
				"Kasssai Ut", "Debrecen", "Hajdu-Bihar", "4032");
		
		Guest savedGuestDetails = new Guest("Mahmoud Darwish", "darwish@test.com");
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson = ow.writeValueAsString(guestPaymentDetails);
	    
	    
		String url = "/payment_guests";
		
		Mockito.when(paymentService.recordNewGuestOrder(guestPaymentDetails)).thenReturn(savedGuestDetails);
		
		MvcResult mvcResult = mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(requestJson)).andReturn();
		
		assertEquals(mvcResult.getResponse().getStatus(), 200);
	}

}
