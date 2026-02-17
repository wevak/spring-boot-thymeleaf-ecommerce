package com.example.demo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.CartRepository;
import com.example.demo.service.CartService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private UserService userService;
	
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
	
	@GetMapping("/cart")
	public String getCartProducts(Model model, Principal principal) {
		User currentUser = userService.findByEmail(principal.getName());
		
//		model.addAttribute("cartProducts", cartService.getAllCartProducts());
		model.addAttribute("cartProducts", cartService.getAllUserSpecificCartProducts(currentUser.getId()));
		model.addAttribute("categories", categoryService.getAllCategories());
//		model.addAttribute("cartTotalPrice", cartService.calculateTotalCartPrice());
		
		
		model.addAttribute("cartTotalPrice", cartService.calculateUserSpecificTotalCartPrice(currentUser.getId()));
		
		return "cart-products-list";
	}
	
	@PostMapping("/cart/add")
	public String productAdditionToCart(@ModelAttribute("cart") Cart cart, Principal principal, Model model) {
		
		User currentUser = userService.findByEmail(principal.getName());
		
		cart.setUser(currentUser);
		
		cartService.cartProductAddition(cart);
		
//		model.addAttribute("cartProducts", cartService.getAllCartProducts());
		model.addAttribute("categories", categoryService.getAllCategories());
		model.addAttribute("cartProducts", cartService.getAllUserSpecificCartProducts(currentUser.getId()));
//		model.addAttribute("cartTotalPrice", cartService.calculateTotalCartPrice());
		
		model.addAttribute("cartTotalPrice", cartService.calculateUserSpecificTotalCartPrice(currentUser.getId()));
		
		return "cart-products-list";
	}
	
	@DeleteMapping("/cart/delete/{id}")
	public String cartItemDeletion(@PathVariable Long id, Principal principal, Model model) {
		User currentUser = userService.findByEmail(principal.getName());
		
		cartService.specificCartItemDeletion(id);
		
		model.addAttribute("cartProducts", cartService.getAllUserSpecificCartProducts(currentUser.getId()));
//		model.addAttribute("cartProducts", cartService.getAllCartProducts());
		model.addAttribute("categories", categoryService.getAllCategories());
		
		model.addAttribute("cartTotalPrice", cartService.calculateUserSpecificTotalCartPrice(currentUser.getId()));
//		model.addAttribute("cartTotalPrice", cartService.calculateTotalCartPrice());
		
		return "cart-products-list";
	}
	
	@GetMapping("/cart/checkout")
	public String cartCheckout(Model model, Principal principal) {
		User currentUser = userService.findByEmail(principal.getName());
		
		model.addAttribute("cartItems", cartService.getAllUserSpecificCartProducts(currentUser.getId()));
		
		model.addAttribute("cartTotalPrice", cartService.calculateUserSpecificTotalCartPrice(currentUser.getId()));
//		model.addAttribute("cartItems", cartService.getAllCartProducts());
//		model.addAttribute("cartTotalPrice", cartService.calculateTotalCartPrice());
//		model.addAttribute("checkoutForm", new CheckoutRequest());
		
		return "checkout";
	}
}
