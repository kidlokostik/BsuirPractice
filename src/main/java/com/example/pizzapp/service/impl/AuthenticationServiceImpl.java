package com.example.pizzapp.service.impl;

import com.example.pizzapp.component.EmailValidatorComponent;
import com.example.pizzapp.exception.AccessDeniedException;
import com.example.pizzapp.model.User;
import com.example.pizzapp.security.JwtTokenProvider;
import com.example.pizzapp.security.dto.JwtRequest;
import com.example.pizzapp.security.dto.JwtResponse;
import com.example.pizzapp.service.AuthenticationService;
import com.example.pizzapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final EmailValidatorComponent emailValidator;
    private final PasswordEncoder passwordEncoder;

    @Override
    public JwtResponse authenticate(final JwtRequest jwtRequest) {
        JwtResponse jwtResponse = new JwtResponse();
        User user;

        if (emailValidator.isValid(jwtRequest.getLogin())){
            user = userService.findUserByEmailOrThrow(jwtRequest.getLogin());
        } else{
            user = userService.findUserByLoginOrThrow(jwtRequest.getLogin());
        }

        if (passwordEncoder.matches(jwtRequest.getPassword(), user.getPassword())){
            String accessToken = jwtTokenProvider.createAccessToken(user.getId(), user.getLogin(), user.getRole().getName().name());
            String refreshToken = jwtTokenProvider.createRefreshToken(user.getId(), user.getLogin());

            Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
            authenticationManager.authenticate(authentication);

            jwtResponse.setId(user.getId());
            jwtResponse.setUsername(user.getLogin());
            jwtResponse.setEmail(user.getEmail());
            jwtResponse.setAccessToken(accessToken);
            jwtResponse.setRefreshToken(refreshToken);

        } else {
            throw new AccessDeniedException();
        }

        return jwtResponse;
    }

    public JwtResponse refreshToken(
            final String refreshToken
    ) {
        return jwtTokenProvider.refreshUserTokens(refreshToken);
    }
}