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
import com.blacksheep.exceptions.ResourceNotFoundException;
import com.blacksheep.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductsController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("")
	public String showAllProducts(ModelMap model) throws ResourceNotFoundException {
		try {
			List<Product> products = productService.getAllProducts();

			model.put("products", products);
			return "products";
			
		} catch (Exception e) {
			//below assignment disables the improved rethrow exception type checking feature of Java 7
			// e=new ThirdException();
			throw e;
		}
	}

	@GetMapping("/{productCategory}")
	public String showProductsByCategory(@PathVariable String productCategory,
			@RequestParam(value = "productCategoryGender", required = false) String productCategoryGender,
			@RequestParam(value = "inStock", required = false) boolean inStock,
			@RequestParam(value = "min", required = false, defaultValue = "0") Integer min,
			@RequestParam(value = "max", required = false) Integer max, ModelMap model)
			throws ResourceNotFoundException {

		Integer Price = productService.getMaxPrice();
		if (max == null) {
			max = Price;
		}
		
		try {
			List<Product> products = productService.getProducts(inStock, productCategory, productCategoryGender, min, max);

			if (products.isEmpty()) {
				throw new ResourceNotFoundException();
			}
			model.put("products", products);
			return "products_by_category";
			
		} catch (Exception e) {
			//below assignment disables the improved rethrow exception type checking feature of Java 7
			// e=new ThirdException();
			throw e;
		}
		
	}
}
