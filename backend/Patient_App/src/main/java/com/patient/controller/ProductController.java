package com.patient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.patient.Entity.Product;
import com.patient.ServiceImpl.ProductServiceImpl;

import jakarta.persistence.EntityManager;

@RestController
@CrossOrigin
public class ProductController {
	
	
	@Autowired
	private EntityManager entityManager;
	
	
	@Autowired
	private ProductServiceImpl productServiceImpl;
	
	@PostMapping("/createProduct")
	private ResponseEntity<?> createProduct(@RequestBody Product product)
	{
		Product createdProduct=productServiceImpl.createProduct(product);
		return new ResponseEntity<Product>(createdProduct,HttpStatus.OK);
	}

}
