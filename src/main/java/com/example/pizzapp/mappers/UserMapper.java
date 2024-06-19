package com.example.pizzapp.mappers;

import com.example.pizzapp.dto.UserDto;
import com.example.pizzapp.dto.request.create.UserCreateRequest;
import com.example.pizzapp.dto.request.update.UserUpdateRequest;
import com.example.pizzapp.dto.response.UserResponse;
import com.example.pizzapp.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserCreateRequest toCreateRequest(User user);
    List<UserCreateRequest> toCreateRequest(List<User> users);

    User createRequestToEntity(UserCreateRequest userCreateRequest);

    UserUpdateRequest toUpdateRequest(User user);
    List<UserUpdateRequest> toUpdateRequest(List<User> users);

    User updateRequestToEntity(UserUpdateRequest userUpdateRequest);

    UserResponse toResponse(User user);
    List<UserResponse> toResponse(List<User> users);

    User responseToEntity(UserResponse userResponse);
}
