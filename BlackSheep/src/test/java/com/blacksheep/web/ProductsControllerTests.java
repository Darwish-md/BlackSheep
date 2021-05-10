package com.blacksheep.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.blacksheep.domain.Product;
import com.blacksheep.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ProductsController.class)
class ProductsControllerTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@MockBean
	private ProductService productService;
	
	
	@SuppressWarnings("unchecked")
	@Test
	void testGetAllProductsPage() throws Exception {
		
		List<Product> products = new ArrayList<>();
		products.add(new Product("Tommy", 500, 70, "SHIRTS", "KID"));
		products.add(new Product("H&M", 600, 100, "JEANS", "MEN"));
		products.add(new Product("Zara", 1000, 10, "SHOES", "MEN"));
		
		Mockito.when(productService.getAllProducts()).thenReturn(products);

		String url = "/products";
		
		MvcResult mvcResult = mockMvc.perform(get(url)).andReturn();
		Collection<Object> actualResponseProducts = mvcResult.getModelAndView().getModel().values();
	
		assertEquals(mvcResult.getResponse().getStatus(), 200);
		actualResponseProducts.forEach (e -> assertThat(products.size()).isEqualTo(((List<Product>) e).size()));
	
	}

}
