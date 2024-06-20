package com.example.pizzapp.mappers;

import com.example.pizzapp.dto.request.create.UserCreateRequest;
import com.example.pizzapp.dto.request.update.UserUpdateRequest;
import com.example.pizzapp.dto.response.UserResponse;
import com.example.pizzapp.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    @Mapping(target = "orders", ignore = true)
    User createRequestToEntity(UserCreateRequest userCreateRequest);

    List<User> createRequestToEntity(List<UserCreateRequest> userCreateRequests);

    UserResponse toResponse(User user);

    List<UserResponse> toResponse(List<User> users);
}


