package com.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.domain.Product;

public interface ProdutoRepository extends JpaRepository<Product, Long> {

	Product save(Optional<Product> updatedProduct);

}
