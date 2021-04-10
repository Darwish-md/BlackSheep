package com.blacksheep.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.blacksheep.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(value = " SELECT * FROM product " + "WHERE stock_quantity > IF(:inStock,  0, 0-1)"
			+ "And category like coalesce(:category, '%') " + "And category_gender like coalesce(:categoryGender, '%') "
			+ "And unit_price BETWEEN :min AND :max ", nativeQuery = true)
	List<Product> findProducts(Boolean inStock, String category, String categoryGender, Integer min, Integer max);

	@Query(value = "select max(unit_price) from product", nativeQuery = true)
	public Integer findMaxPrice();
	
	public Optional<Product> findById(Long id); 

}
