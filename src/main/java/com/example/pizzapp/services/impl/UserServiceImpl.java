package com.example.pizzapp.services.impl;

import com.example.pizzapp.dto.UserDto;
import com.example.pizzapp.dto.request.create.UserCreateRequest;
import com.example.pizzapp.dto.request.update.UserUpdateRequest;
import com.example.pizzapp.dto.response.UserResponse;
import com.example.pizzapp.exception.ResourceNotFoundException;
import com.example.pizzapp.mappers.UserMapper;
import com.example.pizzapp.models.User;
import com.example.pizzapp.repositories.UserRepository;
import com.example.pizzapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Override
    public UserResponse createUser(UserCreateRequest createUserRequest) {
        User user = userMapper.createRequestToEntity(createUserRequest);
        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    public UserResponse updateUser(Long id, UserUpdateRequest updateUserRequest) {
        User newestUser = findUserByIdOrThrow(id);
        userMapper.toUpdateRequest(newestUser);

        User updateUser = userRepository.save(newestUser);
        return userMapper.toResponse(updateUser);
    }

    private User findUserByIdOrThrow(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(""));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = findUserByIdOrThrow(id);
        return userMapper.toResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponse)
                .toList();
    }
}
