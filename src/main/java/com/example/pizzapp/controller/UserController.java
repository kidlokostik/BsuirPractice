package com.example.pizzapp.controller;

import com.example.pizzapp.dto.request.create.UserCreateRequest;
import com.example.pizzapp.dto.request.update.UserUpdateRequest;
import com.example.pizzapp.dto.response.UserResponse;
import com.example.pizzapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/new")
    public UserResponse createUser(@RequestBody @Valid UserCreateRequest userCreateRequest) {
        return userService.createUser(userCreateRequest);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@E.canAccessUser(#id)")
    public UserResponse updateUser(
            @PathVariable @P("id") Long id,
            @RequestBody @Valid UserUpdateRequest userUpdateRequest
    ) {
        return userService.updateUser(id, userUpdateRequest);
    }

    @GetMapping("/{id}")
    @PreAuthorize("@E.canAccessUser(#id)")
    public UserResponse getById(@PathVariable @P("id") Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@E.canAccessUser(#id)")
    public void deleteUser(@PathVariable @P("id") Long id) {
       userService.deleteUser(id);
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }
}
