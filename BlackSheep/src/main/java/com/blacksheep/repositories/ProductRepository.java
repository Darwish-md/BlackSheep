package com.blacksheep.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.blacksheep.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(value = "SELECT * FROM product "
			+ "WHERE stock_quantity > (SELECT CASE WHEN CAST(?1 AS BOOLEAN) THEN (SELECT CAST ('0' AS INTEGER)) ELSE (SELECT CAST ('-1' AS INTEGER)) END)  "
			+ "AND category LIKE COALESCE(CAST(?2 AS TEXT), '%') "
			+ "AND category_gender LIKE COALESCE(CAST(?3 AS TEXT), '%') "
			+ "AND unit_price BETWEEN CAST(?4 AS INTEGER) AND CAST(?5 AS INTEGER) ", nativeQuery = true)
	List<Product> findProducts(Boolean inStock, String category, String categoryGender, Integer min, Integer max);

	@Query(value = "SELECT MAX(unit_price) FROM product", nativeQuery = true)
	public Integer findMaxPrice();

//	public Optional<Product> findById(long id);

	List<Product> findByCategory(String productCategory);

}
