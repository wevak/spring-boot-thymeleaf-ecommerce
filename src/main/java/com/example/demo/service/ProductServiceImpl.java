package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Product> getAllProducts() {
		
		return productRepository.findAll();
	}

	@Override
	public Product getSpecificProduct(Long id) {
		
		return productRepository.findById(id).orElseThrow();
	}

	@Override
	public Product specificProductUpdation(Long id, Product product) {
		Product existingProduct = productRepository.findById(id).orElseThrow();
		
		existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setWeight(product.getWeight());
        existingProduct.setImgName(product.getImgName());
		
        //Handling the Category association
        if (product.getCategory() != null) {
            long categoryId = product.getCategory().getId();
            
            // Fetching the managed category entity from the DB
            Category category = categoryRepository.findById(categoryId)
                .orElseThrow();
            
            existingProduct.setCategory(category);
        }
        
		return productRepository.save(existingProduct);
	}

	@Override
	public Product productCreation(Product product) {
		return productRepository.save(product);
	}

	@Override
	public String productDeletion(Long id) {
		productRepository.deleteById(id);
		return "successfully deleted product with id " + id;
	}

	@Override
	public List<Product> getCategoryProducts(Long id) {
		
		return productRepository.findAllByCategoryId(id);
	}

}
