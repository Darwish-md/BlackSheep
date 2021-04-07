package com.blacksheep.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blacksheep.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	List <Product> findByCategory(String category);
	List <Product> findByCategoryGender(String categoryGender);
	List <Product> findByCategoryAndCategoryGender(String category, String categoryGender);
	
	@Query(value = " SELECT * FROM product "
			+ "WHERE stock_quantity > (IF(:inStock,  0, 0-1))"
			+ "And category like coalesce(:category, '%') "
			+ "And category_gender like coalesce(:categoryGender, '%')", nativeQuery = true)
	List<Product>  findByCategoryAndCategoryGenderAndstockQuantity(Boolean inStock, String category, String categoryGender);
	
}
