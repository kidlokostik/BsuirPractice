package com.example.pizzapp.security;

import com.example.pizzapp.exception.ResourceNotFoundException;
import com.example.pizzapp.model.User;
import com.example.pizzapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.example.pizzapp.util.ErrorMessages.NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final EmailValidator emailValidator;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user;

        if(emailValidator.isValid(login, null)) {
            user = userRepository.findByEmail(login).orElseThrow(
                    () -> new ResourceNotFoundException(String.format(NOT_FOUND_MESSAGE, "User", login))
            );
        } else {
            user = userRepository.findByLogin(login).orElseThrow(
                    () -> new ResourceNotFoundException(String.format(NOT_FOUND_MESSAGE, "User", login))
            );
        }

        return JwtEntityFactory.create(user);
    }
}
