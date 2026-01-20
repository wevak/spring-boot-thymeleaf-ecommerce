package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Cart;

public interface CartService {
	List<Cart> getAllCartProducts();
	
	Cart cartProductAddition(Cart item);
	
	String specificCartItemDeletion(Long id);
	
	double calculateTotalCartPrice();
}
