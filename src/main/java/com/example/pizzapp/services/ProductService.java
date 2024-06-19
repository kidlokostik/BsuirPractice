package com.example.pizzapp.services;

import com.example.pizzapp.dto.request.create.CategoryCreateRequest;
import com.example.pizzapp.dto.request.create.ProductCreateRequest;
import com.example.pizzapp.dto.request.update.CategoryUpdateRequest;
import com.example.pizzapp.dto.request.update.ProductUpdateRequest;
import com.example.pizzapp.dto.response.CategoryResponse;
import com.example.pizzapp.dto.response.ProductResponse;
import com.example.pizzapp.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductCreateRequest createProductRequest);
    ProductResponse updateProduct(Long id, ProductUpdateRequest updateProductRequest);
    void deleteProduct(Long id);
    ProductResponse getProductById(Long id);
    List<ProductResponse> getAllProducts();
}
