package com.blacksheep.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blacksheep.domain.Product;
import com.blacksheep.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductsController {

	@Autowired
	private ProductService productService;

	@GetMapping("/{productCategory}")
	public String productsView(@PathVariable String productCategory,
			@RequestParam(value = "productCategoryGender", required = false) String productCategoryGender,
			@RequestParam(value = "inStock", required = false) boolean inStock, ModelMap model) {

		List<Product> products = productService.getProducts(inStock, productCategory,
				productCategoryGender);
		model.put("products", products);

		return "products";
	}
}
