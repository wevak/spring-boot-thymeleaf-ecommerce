package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> getAllCategories() {
		
		return categoryRepository.findAll();
	}

	@Override
	public Category categoryCreation(Category category) {
		return categoryRepository.save(category);
	}
	
	@Override
	public String categoryDeletion(Long id) {
		categoryRepository.deleteById(id);
		return "Category with id " + id + " successfully deleted";
	}

	@Override
	public Category getSpecificCategory(Long id) {
		
		return categoryRepository.findById(id).orElseThrow();
	}

	@Override
	public Category specificCategoryUpdation(Long id, Category category) {
		
		Optional<Category> categoryOptional = categoryRepository.findById(id);
		
		if(categoryOptional.isPresent()) {
			Category cat = categoryOptional.get();
			
			cat.setName(category.getName());
			
			return categoryRepository.save(cat);
		} else {
			throw new RuntimeException("Category not found with id: " + id);
		}
	}

	@Override
	public Category specificCategoryUpdationUsingHQL(String name, Long id) {
		categoryRepository.updateCategoryInfoById(name, id);
		
		return null;
	}
	
	
}
