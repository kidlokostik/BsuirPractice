package com.example.pizzapp.mappers;

import com.example.pizzapp.dto.UserDto;
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
    UserCreateRequest toCreateRequest(User user);
    List<UserCreateRequest> toCreateRequest(List<User> users);

    @Mapping(target = "orders", ignore = true)
    User createRequestToEntity(UserCreateRequest userCreateRequest);
    List<User> createRequestToEntity(List<UserCreateRequest> userCreateRequests);

    UserUpdateRequest toUpdateRequest(User user);
    List<UserUpdateRequest> toUpdateRequest(List<User> users);

    @Mapping(target = "orders", ignore = true)
    User updateRequestToEntity(UserUpdateRequest userUpdateRequest);
    List<User> updateRequestToEntity(List<UserUpdateRequest> userUpdateRequests);

    UserResponse toResponse(User user);
    List<UserResponse> toResponse(List<User> users);

    @Mapping(target = "orders", ignore = true)
    User responseToEntity(UserResponse userResponse);
    List<User> responseToEntity(List<UserResponse> userResponses);
}


