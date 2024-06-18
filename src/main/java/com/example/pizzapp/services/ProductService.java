package com.example.pizzapp.services;

import com.example.pizzapp.dto.ProductDto;
import com.example.pizzapp.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Product createProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(Long id);

    Product getProductId(Long id);

    List<ProductDto> getAllProducts();
}
