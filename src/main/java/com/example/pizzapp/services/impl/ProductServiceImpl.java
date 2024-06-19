package com.example.pizzapp.services.impl;

import com.example.pizzapp.dto.request.create.ProductCreateRequest;
import com.example.pizzapp.dto.request.update.ProductUpdateRequest;
import com.example.pizzapp.dto.response.ProductResponse;
import com.example.pizzapp.exception.ResourceNotFoundException;
import com.example.pizzapp.mappers.ProductMapper;
import com.example.pizzapp.models.Product;
import com.example.pizzapp.repositories.ProductRepository;
import com.example.pizzapp.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ProductMapper productMapper;

    @Override
    public ProductResponse createProduct(ProductCreateRequest createProductRequest) {
        Product product = productMapper.createRequestToEntity(createProductRequest);
        return productMapper.toResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductUpdateRequest updateProductRequest) {
        Product newestProduct = findProductyByIdOrThrow(id);
        productMapper.toUpdateRequest(newestProduct);

        Product updateProduct = productRepository.save(newestProduct);
        return productMapper.toResponse(updateProduct);
    }

    private Product findProductyByIdOrThrow(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(""));
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = findProductyByIdOrThrow(id);
        return productMapper.toResponse(product);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toResponse)
                .toList();
    }
}
