package com.example.pizzapp.services;

import com.example.pizzapp.models.Category;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    Category getById(Long id);
}
