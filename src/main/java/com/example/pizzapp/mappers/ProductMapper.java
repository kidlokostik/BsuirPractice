package com.example.pizzapp.mappers;

import com.example.pizzapp.dto.ProductDTO;
import com.example.pizzapp.models.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toDTO(Product product);
    List<ProductDTO> toDTO(List<Product> products);

    Product toEntity(ProductDTO productDTO);
}
