package com.patient.Service;

import java.util.List;

import com.patient.Entity.Product;

public interface ProductService {
	
	public Product createProduct(Product product) throws Exception;
	
	public Product getDoseName(Integer productId , Integer serialNo);
	
	public List<Product> getAllProducts();
	
	public List<Product> editProduct(List<Product> products);

}
