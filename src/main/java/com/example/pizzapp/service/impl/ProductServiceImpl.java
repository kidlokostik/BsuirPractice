package com.example.pizzapp.service.impl;

import com.example.pizzapp.dto.request.create.ProductCreateRequest;
import com.example.pizzapp.dto.request.update.ProductUpdateRequest;
import com.example.pizzapp.dto.response.ProductResponse;
import com.example.pizzapp.exception.DuplicateFoundException;
import com.example.pizzapp.exception.ResourceNotFoundException;
import com.example.pizzapp.mapper.ProductMapper;
import com.example.pizzapp.model.Product;
import com.example.pizzapp.repository.ProductRepository;
import com.example.pizzapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.pizzapp.util.ErrorMessages.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponse createProduct(ProductCreateRequest createProductRequest) {
        if (uniqueProductCheck(createProductRequest)) {
            throw new DuplicateKeyException(String.format(DUPLICATE_FOUND_MESSAGE, "product", createProductRequest.name()));
        }
        Product product = productMapper.createRequestToEntity(createProductRequest);
        return productMapper.toResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductUpdateRequest updateProductRequest) {
        Product product = findProductByIdOrThrow(id);
        if (product.getName().equals(updateProductRequest.name().toString())) {
            productMapper.updateProductFromUpdateRequest(updateProductRequest,product);
            return productMapper.toResponse(productRepository.save(product));
        }
        if (uniqueProductCheck(updateProductRequest)) {
            throw new DuplicateFoundException(String.format(DUPLICATE_FOUND_MESSAGE, "product", updateProductRequest.name()));
        }
        productMapper.updateProductFromUpdateRequest(updateProductRequest,product);
        return productMapper.toResponse(productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = findProductByIdOrThrow(id);
        return productMapper.toResponse(product);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toResponse)
                .toList();
    }

    @Override
    public boolean uniqueProductCheck(ProductCreateRequest createProductRequest) {
        return productRepository.existsByName(createProductRequest.name());
    }

    @Override
    public boolean uniqueProductCheck(ProductUpdateRequest updateProductRequest) {
        return productRepository.existsByName(updateProductRequest.name());
    }

    private Product findProductByIdOrThrow(Long id) {
        return productRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(String.format(NOT_FOUND_MESSAGE, "Product", id))
                );
    }
}
