package com.blacksheep.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.blacksheep.domain.Product;
import com.blacksheep.service.ProductService;

@SpringBootTest
@AutoConfigureMockMvc
class ProductsControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ProductService productService;

	@SuppressWarnings("unchecked")
	@Test
	void testGetAllProductsPage() throws Exception {

		String url = "/products";

		MvcResult mvcResult = mockMvc.perform(get(url)).andReturn();
		Collection<Object> actualResponceProducts = mvcResult.getModelAndView().getModel().values();

		System.out.println(actualResponceProducts);
		assertEquals(mvcResult.getResponse().getStatus(), 200);

		actualResponceProducts.forEach(e -> assertThat(23).isEqualTo(((List<Product>) e).size()));
	}

	@SuppressWarnings("unchecked")
	@Test
	void testGetProductsByCategory() throws Exception {

		Boolean inStock = false;
		String productCategory = "SHIRTS";
		String productCategoryGender = "KID";
		Integer min = 0;
		Integer max;

		max = productService.getMaxPrice();

		List<Product> filteredProducts = productService.getProducts(inStock, productCategory, productCategoryGender,
				min, max);

		String url = "/products/{productCategory}";
		MvcResult mvcResult = mockMvc.perform(get(url, "SHIRTS").param("inStock", "false")
				.param("productCategoryGender", "KID").param("min", "0").param("max", "500")).andExpect(status().isOk())
				.andReturn();

		Collection<Object> actualResponceProducts = mvcResult.getModelAndView().getModel().values();

		actualResponceProducts.forEach(e -> System.out.println(((List<Product>) e).get(0)));
		actualResponceProducts.forEach(
				e -> assertThat(((List<Product>) e).get(0).getId()).isEqualTo(filteredProducts.get(0).getId()));
	}

	@Test
	void testGet404ProductsByCategory() throws Exception {

		String url = "/products/{productCategory}";
		mockMvc.perform(get(url, "SNICKERS").param("inStock", "null").param("productCategoryGender", "null")
				.param("min", "null").param("max", "null")).andExpect(status().is4xxClientError());

	}

}
