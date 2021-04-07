package com.blacksheep.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blacksheep.domain.Product;
import com.blacksheep.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepo;

	public List<Product> getByCategory(String category) {
		return productRepo.findByCategory(category);
	}

	public List<Product> getByCategoryGender(String categoryGender) {
		return productRepo.findByCategoryGender(categoryGender);
	}

	public List<Product> getFilteredProducts(String category, String categoryGender) {
		return productRepo.findByCategoryAndCategoryGender(category, categoryGender);
	}

	public List<Product> getFilteredInStockProducts(Boolean inStock, String category, String categoryGender){
		return productRepo.findByCategoryAndCategoryGenderAndstockQuantity(inStock, category, categoryGender);
	}
}
