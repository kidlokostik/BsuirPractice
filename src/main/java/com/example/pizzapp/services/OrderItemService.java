package com.example.pizzapp.services;

import com.example.pizzapp.models.OrderItem;

public interface OrderItemService {
    OrderItem getById(Long id);
}
