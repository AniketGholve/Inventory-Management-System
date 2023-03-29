package com.patient.Service;

import com.patient.Entity.Product;

public interface ProductService {
	
	public Product createProduct(Product product) throws Exception;
	
	public Product getDoseName(Integer productId);


}
