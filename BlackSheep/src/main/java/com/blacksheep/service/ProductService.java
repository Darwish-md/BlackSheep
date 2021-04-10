package com.blacksheep.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blacksheep.domain.Product;
import com.blacksheep.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepo;

	public List<Product> getProducts(Boolean inStock, String category, String categoryGender, Integer min, Integer max){
		return productRepo.findProducts(inStock, category, categoryGender, min, max);
	}
	
	 
	public Integer getMaxPrice() {
		return productRepo.findMaxPrice();
	}
	
	public Optional<Product> getProduct(Long id){
		return productRepo.findById(id);
	}
	
}
