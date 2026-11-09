package com.ashish.product.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ashish.product.service.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
