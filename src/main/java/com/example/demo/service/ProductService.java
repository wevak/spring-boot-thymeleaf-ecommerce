package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Product;

public interface ProductService {
	List<Product> getAllProducts();
	
	Product getSpecificProduct(Long id);
	
	Product productCreation(Product product);
	
	Product specificProductUpdation(Long id, Product product);
	
	String productDeletion(Long id);
	
	List<Product> getCategoryProducts(Long id);
}
