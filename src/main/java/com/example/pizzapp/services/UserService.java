package com.example.pizzapp.services;

import com.example.pizzapp.dto.UserDto;
import com.example.pizzapp.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    User getUserByName(String name);
    List<UserDto> getAllUsers();
}
