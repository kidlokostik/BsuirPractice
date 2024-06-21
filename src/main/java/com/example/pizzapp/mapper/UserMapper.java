package com.example.pizzapp.mapper;

import com.example.pizzapp.dto.request.create.UserCreateRequest;
import com.example.pizzapp.dto.request.update.UserUpdateRequest;
import com.example.pizzapp.dto.response.UserResponse;
import com.example.pizzapp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User createRequestToEntity(UserCreateRequest userCreateRequest);

    User updateRequestToEntity(Long id, UserUpdateRequest userUpdateRequest);

    UserResponse toResponse(User user);
}


