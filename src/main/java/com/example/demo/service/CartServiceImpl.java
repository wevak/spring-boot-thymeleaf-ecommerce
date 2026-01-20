package com.example.demo.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductRepository;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;
	
//	@Autowired
//	private ProductRepository productRepository;
	
	@Override
	public List<Cart> getAllCartProducts() {
		
		return cartRepository.findAll();
	}

	@Override
	public String specificCartItemDeletion(Long id) {
		
		cartRepository.deleteById(id);
		
		return "Cart Item Successfully Deleted";
	}

	@Override
	public Cart cartProductAddition(Cart item) {
		
		return cartRepository.save(item);
	}
	
	@Override
	public double calculateTotalCartPrice() {
		List<Cart> cartProducts = cartRepository.findAll();
		return cartProducts.stream()
							.mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
				            .sum();
	}
}
