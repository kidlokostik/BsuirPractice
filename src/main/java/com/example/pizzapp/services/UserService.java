package com.example.pizzapp.services;

import com.example.pizzapp.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User getById(Long id);
}
