package com.example.pizzapp.mapper;

import com.example.pizzapp.dto.request.create.UserCreateRequest;
import com.example.pizzapp.dto.response.UserResponse;
import com.example.pizzapp.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User createRequestToEntity(UserCreateRequest userCreateRequest);

    UserResponse toResponse(User user);
}


