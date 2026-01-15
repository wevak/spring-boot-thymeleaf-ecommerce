package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Category;

public interface CategoryService {
	List<Category> getAllCategories();
	
	Category getSpecificCategory(Long id);
	
	Category categoryCreation(Category category);
	
	String categoryDeletion(Long id);
	
	Category specificCategoryUpdation(Long id, Category category);
	
	Category specificCategoryUpdationUsingHQL(String name, Long id);
}
