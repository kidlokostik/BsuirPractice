package com.example.pizzapp.services;

import com.example.pizzapp.models.Order;

public interface OrderService {
    Order getById(Long id);
}
