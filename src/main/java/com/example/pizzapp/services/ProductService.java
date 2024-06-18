package com.example.pizzapp.services;

import com.example.pizzapp.models.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    Product getById(Long id);
}
