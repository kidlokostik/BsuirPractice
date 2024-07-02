package com.example.pizzapp.service.impl;

import com.example.pizzapp.dto.request.create.UserCreateRequest;
import com.example.pizzapp.dto.request.update.UserUpdateRequest;
import com.example.pizzapp.dto.response.UserResponse;
import com.example.pizzapp.exception.DuplicateFoundException;
import com.example.pizzapp.exception.ResourceNotFoundException;
import com.example.pizzapp.exception.ValidationException;
import com.example.pizzapp.mapper.UserMapper;
import com.example.pizzapp.model.Order;
import com.example.pizzapp.model.Role;
import com.example.pizzapp.model.RoleType;
import com.example.pizzapp.model.User;
import com.example.pizzapp.repository.RoleRepository;
import com.example.pizzapp.repository.UserRepository;
import com.example.pizzapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        checkCreateUserData(createUserRequest);
        Role customerRole = roleRepository.findByName(RoleType.CUSTOMER)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(NOT_FOUND_MESSAGE, "Role", createUserRequest.name())));
        User user = userMapper.createRequestToEntity(createUserRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(customerRole);
        return userMapper.toResponse(userRepository.save(user));
    }

    @Override
    public UserResponse updateUser(Long id, UserUpdateRequest updateUserRequest) {
        User user = findUserByIdOrThrow(id);
        checkUpdateUserData(updateUserRequest, user);
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

    private void checkCreateUserData(UserCreateRequest createUserRequest){
        validatePassword(createUserRequest.password(), createUserRequest.confirmPassword());
        if (userRepository.existsByLogin(createUserRequest.login())) {
            throw new DuplicateFoundException(String.format(ALREADY_USED_MESSAGE, createUserRequest.login()));
        }
        if (userRepository.existsByEmail(createUserRequest.email())) {
            throw new DuplicateFoundException(String.format(ALREADY_USED_MESSAGE, createUserRequest.email()));
        }
        if (userRepository.existsByPhone(createUserRequest.phone())) {
            throw new DuplicateFoundException(String.format(ALREADY_USED_MESSAGE, createUserRequest.phone()));
        }
    }

    private void checkUpdateUserData(UserUpdateRequest updateUserRequest, User existingUser) {
        validatePassword(updateUserRequest.password(), updateUserRequest.confirmPassword());
        if (!updateUserRequest.login().equals(existingUser.getLogin()) && userRepository.existsByLogin(updateUserRequest.login())) {
            throw new DuplicateFoundException(String.format(ALREADY_USED_MESSAGE, updateUserRequest.login()));
        }
        if (!updateUserRequest.email().equals(existingUser.getEmail()) && userRepository.existsByEmail(updateUserRequest.email())) {
            throw new DuplicateFoundException(String.format(ALREADY_USED_MESSAGE, updateUserRequest.email()));
        }
        if (!updateUserRequest.phone().equals(existingUser.getPhone()) && userRepository.existsByPhone(updateUserRequest.phone())) {
            throw new DuplicateFoundException(String.format(ALREADY_USED_MESSAGE, updateUserRequest.phone()));
        }
    }

    private User findUserByIdOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(String.format(NOT_FOUND_MESSAGE, "User", id))
                );
    }

    public User findUserByLoginOrThrow(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(
                        () -> new ResourceNotFoundException(String.format(NOT_FOUND_MESSAGE, "User", login))
                );
    }

    public User findUserByEmailOrThrow(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new ResourceNotFoundException(String.format(NOT_FOUND_MESSAGE, "User", email))
                );
    }

    private void validatePassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new ValidationException(PASSWORDS_DO_NOT_MATCH);
        }
    }

    public boolean isOrderOwner(Long id, Long orderId) {
        User user = findUserByIdOrThrow(id);
        List<Order> userOrders = user.getOrders();

        for (Order userOrder: userOrders) {
            if (userOrder.getId().intValue() == orderId.intValue()) return true;
        }
        return false;
    }
}
