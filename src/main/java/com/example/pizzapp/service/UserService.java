package com.example.pizzapp.service;

import com.example.pizzapp.dto.request.create.UserCreateRequest;
import com.example.pizzapp.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserCreateRequest createUserRequest);

    void deleteUser(Long id);

    UserResponse getUserById(Long id);

    List<UserResponse> getAllUsers();
}