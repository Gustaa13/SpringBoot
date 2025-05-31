package com.gustaa13.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustaa13.auth.domain.product.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
}
