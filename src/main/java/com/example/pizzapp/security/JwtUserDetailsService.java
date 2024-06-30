package com.example.pizzapp.security;

import com.example.pizzapp.component.EmailValidatorComponent;
import com.example.pizzapp.model.User;
import com.example.pizzapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;
    private final EmailValidatorComponent emailValidator;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user;

        if(emailValidator.isValid(login)) {
            user = userService.findUserByEmailOrThrow(login);
        } else {
            user = userService.findUserByLoginOrThrow(login);
        }

        return JwtEntityFactory.create(user);
    }
}
