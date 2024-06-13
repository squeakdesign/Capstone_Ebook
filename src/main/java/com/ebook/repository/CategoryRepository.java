package com.ebook.repository;

import java.util.List;

import com.ebook.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ebook.model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

	public Boolean existsByName(String name);

	public List<Category> findByIsActiveTrue();

	// Custom query to find categories by partial name match
	@Query("SELECT c FROM Category c WHERE c.name LIKE %:name%")
	List<Category> findByNameContaining(@Param("name") String name);



}
