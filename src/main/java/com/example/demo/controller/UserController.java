package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/products")
	public String getProducts(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		model.addAttribute("categories", categoryService.getAllCategories());
		
		return "user-products";
	}
	
	@GetMapping("/category/{id}")
	public String getCategoryProducts(@PathVariable Long id, Model model) {
		model.addAttribute("activeCategory", id);
		model.addAttribute("products", productService.getCategoryProducts(id));
		model.addAttribute("categories", categoryService.getAllCategories());
		
		return "user-category-products";
	}
}
