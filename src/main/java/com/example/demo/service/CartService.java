package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Cart;

public interface CartService {
	List<Cart> getAllCartProducts();
	
	public List<Cart> getAllUserSpecificCartProducts(Long id);
	
	Cart cartProductAddition(Cart item);
	
	String specificCartItemDeletion(Long id);
	
	double calculateTotalCartPrice();
	
	public double calculateUserSpecificTotalCartPrice(Long id);
}
