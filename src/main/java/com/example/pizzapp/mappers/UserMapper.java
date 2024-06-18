package com.example.pizzapp.mappers;

import com.example.pizzapp.dto.UserDto;
import com.example.pizzapp.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserDto toDto(User user);
    List<UserDto> toDto(List<User> users);

    User toEntity(UserDto userDto);
}
