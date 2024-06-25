package com.example.pizzapp.service.impl;

import com.example.pizzapp.dto.request.create.UserCreateRequest;
import com.example.pizzapp.dto.request.update.UserUpdateRequest;
import com.example.pizzapp.dto.response.UserResponse;
import com.example.pizzapp.exception.DuplicateFoundException;
import com.example.pizzapp.exception.ResourceNotFoundException;
import com.example.pizzapp.exception.ValidationException;
import com.example.pizzapp.mapper.UserMapper;
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
        checkCreateUserData(createUserRequest);
        Role customerRole = roleRepository.findByName(RoleType.CUSTOMER)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(NOT_FOUND_MESSAGE, "Role", null)));
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
        validateUniqueFields(createUserRequest.login(), createUserRequest.email(), createUserRequest.phone(), null);
    }

    private void checkUpdateUserData(UserUpdateRequest updateUserRequest, User existingUser) {
        validatePassword(updateUserRequest.password(), updateUserRequest.confirmPassword());
        validateUniqueFields(updateUserRequest.login(), updateUserRequest.email(), updateUserRequest.phone(), existingUser);
    }

    private User findUserByIdOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(String.format(NOT_FOUND_MESSAGE, "User", id))
                );
    }

    private void validatePassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new ValidationException(PASSWORDS_DO_NOT_MATCH);
        }
    }

    private void validateUniqueFields(String login, String email, String phone, User existingUser) {
        if ((existingUser == null || !login.equals(existingUser.getLogin())) && userRepository.existsByLogin(login)) {
            throw new DuplicateFoundException(String.format(ALREADY_USED, login));
        }
        if ((existingUser == null || !email.equals(existingUser.getEmail())) && userRepository.existsByEmail(email)) {
            throw new DuplicateFoundException(String.format(ALREADY_USED, email));
        }
        if ((existingUser == null || !phone.equals(existingUser.getPhone())) && userRepository.existsByPhone(phone)) {
            throw new DuplicateFoundException(String.format(ALREADY_USED, phone));
        }
    }
}
