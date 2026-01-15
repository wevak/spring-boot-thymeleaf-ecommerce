package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/dashboard")
    public String index(Model model) {
		model.addAttribute("productCount", productService.getAllProducts().size());
		model.addAttribute("categoryCount", categoryService.getAllCategories().size());
		
        return "dashboard";
    }

	@GetMapping("/categories/list")
	public String getAllCategories(Model model) {
		List<Category> categories = categoryService.getAllCategories();
		
		model.addAttribute("categories", categories);
		
		return "categories-crud";
	}
	
	@GetMapping("/products/list")
	public String productsList(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		
		return "products-list";
	}
}
