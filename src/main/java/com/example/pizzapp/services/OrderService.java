package com.example.pizzapp.services;

import com.example.pizzapp.models.Order;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    Order getById(Long id);
}
