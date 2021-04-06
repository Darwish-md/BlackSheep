package com.blacksheep.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import com.blacksheep.domain.Product;

public interface ProductRepositoryCustom {

	public static final EntityManager em = null;

	public List<Product> findFilteredProducts(String category, Product product);

}
