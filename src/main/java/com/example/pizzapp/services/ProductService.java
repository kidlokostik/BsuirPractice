package com.example.pizzapp.services;

import com.example.pizzapp.models.Category;
import com.example.pizzapp.models.Product;

public interface ProductService {
    Product getById(Long id);
}
