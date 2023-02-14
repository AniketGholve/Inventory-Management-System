package com.patient.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.patient.Entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

}
