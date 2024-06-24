package com.example.pizzapp.mapper;

import com.example.pizzapp.dto.request.create.UserCreateRequest;
import com.example.pizzapp.dto.response.UserResponse;
import com.example.pizzapp.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    @Mapping(target = "role", ignore = true)
    User createRequestToEntity(UserCreateRequest userCreateRequest);

    @Mapping(source = "role.role", target = "role")
    UserResponse toResponse(User user);
}


