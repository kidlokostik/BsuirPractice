package com.example.pizzapp.service.impl;

import com.example.pizzapp.dto.request.create.UserCreateRequest;
import com.example.pizzapp.dto.request.update.UserUpdateRequest;
import com.example.pizzapp.dto.response.UserResponse;
import com.example.pizzapp.exception.DuplicateFoundException;
import com.example.pizzapp.exception.ResourceNotFoundException;
import com.example.pizzapp.exception.ValidationException;
import com.example.pizzapp.mapper.UserMapper;
import com.example.pizzapp.model.Role;
import com.example.pizzapp.model.User;
import com.example.pizzapp.repository.RoleRepository;
import com.example.pizzapp.repository.UserRepository;
import com.example.pizzapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.pizzapp.util.ErrorMessages.NOT_FOUND_MESSAGE;

import java.util.List;

import static com.example.pizzapp.util.ErrorMessages.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public UserResponse createUser(UserCreateRequest createUserRequest) {
        checkUniqueUserPhone(createUserRequest);
        validatePassword(createUserRequest.password(), createUserRequest.confirmPassword());
        if (userRepository.existsByLogin(createUserRequest.login())) {
            throw new IllegalArgumentException("Login is already in use");
        }
        if (userRepository.existsByEmail(createUserRequest.email())) {
            throw new IllegalArgumentException("Email is already in use");
        }
        Role customerRole = roleRepository.findByRole("CUSTOMER")
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
        User user = userMapper.createRequestToEntity(createUserRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(customerRole);
        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    public UserResponse updateUser(Long id, UserUpdateRequest updateUserRequest) {
        User user = findUserByIdOrThrow(id);

        checkUniqueUserPhone(updateUserRequest, user.getPhone());

        userMapper.updateUserFromUpdateRequest(updateUserRequest, user);
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

    private boolean uniqueUserCheck(UserCreateRequest createUserRequest) {
        return userRepository.existsByPhone(createUserRequest.phone());
    }

    private boolean uniqueUserCheck(UserUpdateRequest updateUserRequest, String existingPhone) {
        return updateUserRequest.phone() != null &&
                !updateUserRequest.phone().equals(existingPhone) &&
                userRepository.existsByPhone(updateUserRequest.phone());
    }

    private void checkUniqueUserPhone(UserCreateRequest createUserRequest) {
        if (uniqueUserCheck(createUserRequest)) {
            throw new DuplicateFoundException(String.format(DUPLICATE_FOUND_MESSAGE, "user", createUserRequest.phone()));
        }
    }

    private void checkUniqueUserPhone(UserUpdateRequest updateUserRequest, String existingName) {
        if (uniqueUserCheck(updateUserRequest, existingName)) {
            throw new DuplicateFoundException(String.format(DUPLICATE_FOUND_MESSAGE, "user", updateUserRequest.phone()));
        }
    }

    private User findUserByIdOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(String.format(NOT_FOUND_MESSAGE, "User", id))
                );
    }

    private void validatePassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new ValidationException("Passwords do not match");
        }
    }
}
