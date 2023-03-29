package com.patient.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.patient.Entity.Product;
import com.patient.ServiceImpl.ProductServiceImpl;

@RestController
@CrossOrigin
public class ProductController {
	
	@Autowired
	private ProductServiceImpl productServiceImpl;
	
	@PostMapping("/createProduct")
	public ResponseEntity<?> createProduct(@RequestBody Product product)
	{
		Product createdProduct=productServiceImpl.createProduct(product);
		return new ResponseEntity<Product>(createdProduct,HttpStatus.OK);
	}
	
	@GetMapping("/getDoseName/{productId}/{serialNo}")
	public Product getDoseName(@PathVariable("productId") int productId,@PathVariable("serialNo") int serialNo)
	{
		Product p = productServiceImpl.getDoseName(productId,serialNo);
		return p;
	}

}
