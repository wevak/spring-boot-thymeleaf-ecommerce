package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.UserDTO;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
    public String root() {
        return "redirect:/user/products"; // Redirects root to /home
    }
	
	@GetMapping("/dashboard")
    public String index(Model model) {
		model.addAttribute("productCount", productService.getAllProducts().size());
		model.addAttribute("categoryCount", categoryService.getAllCategories().size());
		
        return "dashboard";
    }
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("user", new User());
		
	    return "login";
	}
	
//	@PostMapping("/login")
//	public String userLogin(@ModelAttribute("user")UserDTO userDTO, Model model) {
//		model.addAttribute("productCount", productService.getAllProducts().size());
//		model.addAttribute("categoryCount", categoryService.getAllCategories().size());
//		
//	    return "dashboard";
//	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		
	    return "register";
	}
	
	@PostMapping("/register/user")
	public String registerUser(@ModelAttribute("user")UserDTO userDTO, Model model) {
		userDTO.setRole("USER");
		
		userService.save(userDTO);
		
		model.addAttribute("message", "Registered Successfully");
		
	    return "login";
	}
}
