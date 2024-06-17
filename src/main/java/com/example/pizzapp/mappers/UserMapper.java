package com.example.pizzapp.mappers;

import com.example.pizzapp.dto.UserDTO;
import com.example.pizzapp.models.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);
    List<UserDTO> toDTO(List<User> users);

    User toEntity(UserDTO userDTO);
}
