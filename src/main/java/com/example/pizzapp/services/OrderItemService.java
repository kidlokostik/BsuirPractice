package com.example.pizzapp.services;

import com.example.pizzapp.models.OrderItem;
import org.springframework.stereotype.Service;

@Service
public interface OrderItemService {
    OrderItem getById(Long id);
}
