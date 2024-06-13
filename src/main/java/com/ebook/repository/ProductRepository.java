package com.ebook.repository;

import java.util.List;

import com.ebook.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ebook.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> findByIsActiveTrue();

List<Product> findByCategory(String category);

//	// Method to find products by category using custom query
//	@Query("SELECT p FROM Product p WHERE p.category = :category")
//	List<Product> findByCategory(@Param("category") String category);



}
