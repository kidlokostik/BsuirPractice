package com.example.pizzapp.services.impl;

import com.example.pizzapp.dto.ProductDto;
import com.example.pizzapp.mappers.ProductMapper;
import com.example.pizzapp.models.Product;
import com.example.pizzapp.repositories.ProductRepository;
import com.example.pizzapp.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {


    private ProductRepository productRepository;
    private ProductMapper productMapper;
    @Override
    public Product createProduct(Product product) {
        return productRepository.createProduct(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.updateProduct(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product getProductId(Long id) {
        return productRepository.getById(id);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toDto)
                .toList();
    }
}
