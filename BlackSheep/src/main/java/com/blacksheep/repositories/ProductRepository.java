package com.blacksheep.repositories;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blacksheep.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> , ProductRepositoryCustom {
	
	List <Product> findByCategory(String category);
	List <Product> findByCategoryGender(String categoryGender);
    
	@Override
	public default List<Product> findFilteredProducts(String category, Product product) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);

		Root<Product> productEntity = cq.from(Product.class);
		Predicate categoryPredicate = cb.equal(productEntity.get("category"), product.getCategory());
//        Predicate categoryGenderPredicate = cb.like(product.get("categoryGender"), "%" + categoryGender + "%");
		Predicate categoryGenderPredicate = cb.equal(productEntity.get("categoryGender"), product.getCategoryGender());
		cq.where(categoryPredicate, categoryGenderPredicate);

		TypedQuery<Product> query = em.createQuery(cq);
		return query.getResultList();
	}
}
