package com.blacksheep.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blacksheep.domain.Product;
import com.blacksheep.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductsController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/{productCategory}")
	public String productsView (@PathVariable String productCategory, ModelMap model) {
		 List <Product> products = productService.getByCategory(productCategory);
		 model.put("products", products);
		 return "products";
	}
	
	
	
	@PostMapping("/{productCategory}/filtered_products")
	public String productsView (@PathVariable String productCategory, @ModelAttribute Product product, ModelMap model) {
		 List <Product> products = productService.getFilteredProducts(productCategory, product);
		 model.put("products", products);
		 model.put("product", product);
		 return "redirect:/products";
	}
	
	
}
