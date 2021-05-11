package com.blacksheep.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
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


@WebMvcTest(ProductsController.class)
class ProductsControllerTests {

	@Autowired
	private MockMvc mockMvc;

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

	@SuppressWarnings("unchecked")
	@Test
	void testGetProductsByCategory() throws Exception {

		List<Product> products = new ArrayList<>();
		products.add(new Product("Tommy", 500, 0, "SHIRTS", "KID"));
		products.add(new Product("H&M", 600, 100, "JEANS", "UNISEX"));
		products.add(new Product("Zara", 1000, 10, "SHOES", "MEN"));
		products.add(new Product("A&S", 700, 70, "WATCHES", "WOMEN"));
		
		List<Product> filteredProducts = new ArrayList<>();
		filteredProducts.add(new Product("Tommy", 500, 0, "SHIRTS", "KID"));

		Boolean inStock = false;
		String productCategory = "SHIRTS";
		String productCategoryGender = "KID";
		Integer min = 0;
		Integer max = 500;
		
		Mockito.when(productService.getMaxPrice()).thenReturn(max);

		Mockito.when(productService.getProducts(inStock, productCategory, productCategoryGender, min, max))
				.thenReturn(filteredProducts);

		String url = "/products/{productCategory}";
		mockMvc.perform(get(url, "SHIRTS")
				.param("inStock", "false")
                .param("productCategoryGender", "KID")
                .param("min", "0")
                .param("max", "500")).andExpect(status().isOk()).andExpect(model().attribute("products", is(filteredProducts)));
		
	}

	@Test
	void testGet404ProductsByCategory() throws Exception {

		List<Product> products = new ArrayList<>();
		products.add(new Product("Tommy", 500, 0, "SHIRTS", "KID"));
		products.add(new Product("H&M", 600, 100, "JEANS", "UNISEX"));
		products.add(new Product("Zara", 1000, 10, "SHOES", "MEN"));
		products.add(new Product("A&S", 700, 70, "WATCHES", "WOMEN"));
		
		
		Boolean inStock = null;
		String productCategory = "SHIRTS";
		String productCategoryGender = "KID";
		Integer min = 0;
		Integer max = 1000;
		
		Mockito.when(productService.getMaxPrice()).thenReturn(max);

		Mockito.when(productService.getProducts(inStock, productCategory, productCategoryGender, min, max))
				.thenReturn(products);

		String url = "/products/{productCategory}";
		mockMvc.perform(get(url, "SNICKERS")).andExpect(status().is4xxClientError());
	}
}
