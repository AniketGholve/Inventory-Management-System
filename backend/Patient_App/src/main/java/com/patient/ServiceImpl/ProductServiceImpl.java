package com.patient.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patient.Entity.Product;
import com.patient.Repo.ProductRepo;


@Service
public class ProductServiceImpl {
	
	

	@Autowired
	private ProductRepo productRepo;

	public Product createProduct(Product product) {
		// TODO Auto-generated method stub
		
		return productRepo.save(product);
	}

}
