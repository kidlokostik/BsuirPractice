package com.example.pizzapp.service.impl;

import com.example.pizzapp.model.User;
import com.example.pizzapp.security.JwtTokenProvider;
import com.example.pizzapp.security.dto.JwtRequest;
import com.example.pizzapp.security.dto.JwtResponse;
import com.example.pizzapp.service.AuthenticationService;
import com.example.pizzapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final EmailValidator emailValidator;

    @Override
    public JwtResponse authenticate(final JwtRequest jwtRequest) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                jwtRequest.login(), jwtRequest.password()
            )
        );

        User user;

        if (emailValidator.isValid(jwtRequest.login(), null)){
            user = userService.findUserByEmailOrThrow(jwtRequest.login());
        } else {
            user = userService.findUserByLoginOrThrow(jwtRequest.login());
        }

        Long id = user.getId();
        String login = user.getLogin();
        String accessToken = jwtTokenProvider.createAccessToken(user.getId(), user.getLogin(), user.getRole().getName().name());
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getId(), user.getLogin());

        return new JwtResponse(id, login, accessToken, refreshToken);
    }

    public JwtResponse refreshToken(
            final String refreshToken
    ) {
        return jwtTokenProvider.refreshUserTokens(refreshToken);
    }
}