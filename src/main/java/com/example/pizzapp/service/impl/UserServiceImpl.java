package com.example.pizzapp.service.impl;

import com.example.pizzapp.dto.request.create.UserCreateRequest;
import com.example.pizzapp.dto.request.update.UserUpdateRequest;
import com.example.pizzapp.dto.response.UserResponse;
import com.example.pizzapp.exception.DuplicateFoundException;
import com.example.pizzapp.exception.ResourceNotFoundException;
import com.example.pizzapp.mapper.UserMapper;
import com.example.pizzapp.model.User;
import com.example.pizzapp.repository.UserRepository;
import com.example.pizzapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.pizzapp.util.ErrorMessages.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse createUser(UserCreateRequest createUserRequest) {
        if (uniqueUserCheck(createUserRequest)) {
            throw new DuplicateKeyException(String.format(DUPLICATE_FOUND_MESSAGE, "user", createUserRequest.phone()));
        }
        User user = userMapper.createRequestToEntity(createUserRequest);
        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    public UserResponse updateUser(Long id, UserUpdateRequest updateUserRequest) {
        User user = findUserByIdOrThrow(id);
        if (user.getPhone().equals(updateUserRequest.phone().toString())) {
            userMapper.updateUserFromUpdateRequest(updateUserRequest,user);
            return userMapper.toResponse(userRepository.save(user));
        }
        if (uniqueUserCheck(updateUserRequest)) {
            throw new DuplicateFoundException(String.format(DUPLICATE_FOUND_MESSAGE, "user", updateUserRequest.phone()));
        }
        userMapper.updateUserFromUpdateRequest(updateUserRequest,user);
        return userMapper.toResponse(userRepository.save(user));
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

    @Override
    public boolean uniqueUserCheck(UserCreateRequest createUserRequest) {
        return userRepository.existsByPhone(createUserRequest.phone());
    }

    @Override
    public boolean uniqueUserCheck(UserUpdateRequest updateUserRequest) {
        return userRepository.existsByPhone(updateUserRequest.phone());
    }

    private User findUserByIdOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(String.format(NOT_FOUND_MESSAGE, "User", id))
                );
    }
}
