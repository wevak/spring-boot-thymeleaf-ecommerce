package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
//	@GetMapping
//	public ResponseEntity<?> getAllCategories() {
//		List<Category> categories = categoryService.getAllCategories(); 
//		return ResponseEntity.ok(categories);
//	}
	
	@GetMapping("/list")
	public String getAllCategories(Model model) {
		List<Category> categories = categoryService.getAllCategories();
		
		model.addAttribute("categories", categories);
		
		return "categories-crud";
	}
	
//	@GetMapping("/{id}")
//	public ResponseEntity<?> getSpecificCategory(@PathVariable Long id) {
//		return ResponseEntity.ok(categoryService.getSpecificCategory(id));
//	}
	
	@GetMapping("/{id}")
	public String getSpecificCategory(@PathVariable Long id, Model model) {
		
		model.addAttribute("category", categoryService.getSpecificCategory(id));
		return "category-details";
	}
	
	@GetMapping("/create-screen")
	public String categoryCreateScreen(Model model) {
		model.addAttribute("category", new Category());
		
		return "category-create";
	}
	
	@PostMapping("/add")
	public String addCategory(@ModelAttribute("category") Category category, Model model) {
		categoryService.categoryCreation(category);
		
		List<Category> categories = categoryService.getAllCategories();
		model.addAttribute("categories", categories);
		
		return "categories-crud";
	}
	
//	@PostMapping("/add")
//	public ResponseEntity<?> addCategory(@RequestBody Category category) {
//		return ResponseEntity.ok(categoryService.categoryCreation(category));
//	}
	
//	@DeleteMapping("/{id}")
//	public ResponseEntity<?> categoryDeletion(@PathVariable Long id) {
//		return ResponseEntity.ok(categoryService.categoryDeletion(id));
//	}
	
	@DeleteMapping("/delete/{id}")
	public String categoryDeletion(@PathVariable Long id, Model model) {
		categoryService.categoryDeletion(id);
		
		List<Category> categories = categoryService.getAllCategories();
		model.addAttribute("categories", categories);
		
		return "categories-crud";
	}
	
	@GetMapping("/update-screen/{id}")
	public String navigatingUpdateScreen(@PathVariable Long id, Model model) {
		model.addAttribute("category", categoryService.getSpecificCategory(id));
		return "category-updation";
	}
	
//	@PutMapping("/update/{id}")
//	public ResponseEntity<?> updatingSpecificCategory(@RequestBody Category category, @PathVariable Long id) {
//		return ResponseEntity.ok(categoryService.specificCategoryUpdation(id, category));
//	}
	
	@PutMapping("/update/{id}")
	public String updatingSpecificCategory(@ModelAttribute("category") Category category, @PathVariable Long id) {
		categoryService.specificCategoryUpdation(id, category);
		
		return "redirect:/categories";
	}
	
	@PutMapping("/update/hql/{id}")
	public ResponseEntity<?> categoryUpdationUsingHQL(@PathVariable Long id, @RequestBody Category category) {
		return ResponseEntity.ok(categoryService.specificCategoryUpdationUsingHQL(category.getName(), id));
	}
	
}
