package com.etiya.northwindSpring.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etiya.northwindSpring.entities.concretes.Category;

public interface CategoryDao extends JpaRepository<Category, Integer> {
	
	boolean existsCategoryByCategoryName(String categoryName);
	
}
