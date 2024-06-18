package com.example.pizzapp.services.impl;

import com.example.pizzapp.dto.UserDto;
import com.example.pizzapp.exception.ResourceNotFoundException;
import com.example.pizzapp.mappers.UserMapper;
import com.example.pizzapp.models.User;
import com.example.pizzapp.repositories.UserRepository;
import com.example.pizzapp.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Override
    public User createUser(User user) {
        return userRepository.createUser(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        return userRepository.updateUser(user);
    }

    @Override
    public void deleteUser(Long id) {
       userRepository.deleteById(id);
    }

    @Override
    public User getUserByName(String name) {
        return userRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }
}
