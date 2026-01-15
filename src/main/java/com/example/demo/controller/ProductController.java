package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final CategoryRepository categoryRepository;
	
	@Autowired
	private ProductService productService;

    ProductController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
	
	@GetMapping("/list")
	public String productsList(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		
		return "products-list";
	}
	
	@GetMapping("/view/{id}")
	public String specificProductDetails(@PathVariable Long id, Model model) {
		model.addAttribute("product", productService.getSpecificProduct(id));
		
		return "product-details";
	}
	
	@GetMapping("/update-screen/{id}")
	public String navigatingUpdateScreen(@PathVariable Long id, Model model) {
		model.addAttribute("product", productService.getSpecificProduct(id));
		return "product-updation";
	}
	
	@GetMapping("/create-screen")
	public String navigatingCreateScreen(Model model) {
		model.addAttribute("product", new Product());
	    model.addAttribute("categories", categoryRepository.findAll());
		
		return "product-creation";
	}
	
	@PostMapping("/create")
	public String creatingSpecificProduct(@ModelAttribute("product") Product product) {
		productService.productCreation(product);

		return "redirect:/products/list";
	}
	
	@PutMapping("/update/{id}")
	public String updatingSpecificProduct(@ModelAttribute("product") Product product, @PathVariable Long id) {
		productService.specificProductUpdation(id, product);

		return "redirect:/products/list";
	}
	
	@DeleteMapping("/delete/{id}")
	public String categoryDeletion(@PathVariable Long id, Model model) {
		productService.productDeletion(id);
		
		List<Product> products = productService.getAllProducts();
		model.addAttribute("products", products);
		
		return "redirect:/products/list";
	}
}
