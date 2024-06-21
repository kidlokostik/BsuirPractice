package com.example.pizzapp.service.impl;

import com.example.pizzapp.dto.request.create.ProductCreateRequest;
import com.example.pizzapp.dto.request.update.ProductUpdateRequest;
import com.example.pizzapp.dto.response.ProductResponse;
import com.example.pizzapp.exception.ResourceNotFoundException;
import com.example.pizzapp.mapper.ProductMapper;
import com.example.pizzapp.model.Product;
import com.example.pizzapp.repository.ProductRepository;
import com.example.pizzapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import static com.example.pizzapp.util.ErrorMessages.NOT_FOUND_MESSAGE;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponse createProduct(ProductCreateRequest createProductRequest) {
        Product product = productMapper.createRequestToEntity(createProductRequest);
        return productMapper.toResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductUpdateRequest productUpdateRequest) {
        Product product = findProductByIdOrThrow(id);
        productMapper.updateProductFromUpdateRequest(productUpdateRequest,product);
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

    private Product findProductByIdOrThrow(Long id) {
        return productRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(String.format(NOT_FOUND_MESSAGE, "Product", id))
                );
    }
}
