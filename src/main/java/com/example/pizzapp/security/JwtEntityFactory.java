package com.example.pizzapp.security;

import com.example.pizzapp.model.Role;
import com.example.pizzapp.model.RoleType;
import com.example.pizzapp.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JwtEntityFactory {

    public static JwtEntity create(User user){
        List<Role> roles = new ArrayList<>();
        roles.add(user.getRole());
        return new JwtEntity(
                user.getId(),
                user.getLogin(),
                user.getName(),
                user.getPassword(),
                mapToGrantedAuthorities(new ArrayList<>(roles))
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles){
        return roles.stream()
                .map(Role::getName)
                .map(RoleType::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
