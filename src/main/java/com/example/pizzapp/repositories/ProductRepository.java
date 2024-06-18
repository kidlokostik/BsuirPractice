package com.example.pizzapp.repositories;


import com.example.pizzapp.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);
    List<Product> findAllByCategoryId(Long categoryId);

    Product createProduct(Product product);

    Product updateProduct(Product product);

}