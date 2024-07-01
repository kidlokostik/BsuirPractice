package com.example.pizzapp.security;

import com.example.pizzapp.model.User;
import com.example.pizzapp.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;
    private final EmailValidator emailValidator;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user;

        if(emailValidator.isValid(login, null)) {
            user = userService.findUserByEmailOrThrow(login);
        } else {
            user = userService.findUserByLoginOrThrow(login);
        }

        return JwtEntityFactory.create(user);
    }
}
