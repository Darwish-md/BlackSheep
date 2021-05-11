package com.blacksheep.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.blacksheep.POJOS.Profile;
import com.blacksheep.domain.Customer;
import com.blacksheep.service.ProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@WebMvcTest(ProfileController.class)
class ProfileControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired 
	WebApplicationContext webApplicationContext;
	
	@MockBean
	private ProfileService profileService;
	
	@BeforeEach()
	public void setup() {
	    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	public static RequestPostProcessor rob() {
		return user("rob").roles("ADMIN");
	} 
	
	@Test
	void testEditProfile() throws Exception {
		Customer customer = new Customer(1,"Talha", "Zafar", "email1@test.com", null, 
				"068234653245", "street 1", "Debrecen", "Hajdu-Bihar", "4023", null, null);
		
		Profile editedDetails = new Profile("Talha", "yasseen", "email2@test.com", null, 
				"068234653321", "street 2", "Budapest", "Budapest", "2075");
		
		Customer customerAfterEdit = new Customer(1,"Talha", "yasseen", "email2@test.com", null, 
				"068234653321", "street 2", "Budapest", "Budapest", "2075", null, null);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String requestJson = ow.writeValueAsString(editedDetails);
	    
		String url = "/edit_profile";
		
		Mockito.when(profileService.editCustomerProfile(customer,editedDetails)).thenReturn(customerAfterEdit);
		
		MvcResult mvcResult = mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(requestJson)).andReturn();
		
		assertEquals(mvcResult.getResponse().getStatus(), 200);
		
	}
}