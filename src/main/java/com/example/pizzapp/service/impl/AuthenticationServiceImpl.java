package com.example.pizzapp.service.impl;

import com.example.pizzapp.model.User;
import com.example.pizzapp.security.JwtTokenProvider;
import com.example.pizzapp.security.dto.JwtRequest;
import com.example.pizzapp.security.dto.JwtResponse;
import com.example.pizzapp.service.AuthenticationService;
import com.example.pizzapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public JwtResponse authenticate(final JwtRequest jwtRequest) {
        JwtResponse jwtResponse = new JwtResponse();

        User user = userService.findUserByEmailOrThrow(jwtRequest.getEmail());

        String accessToken = jwtTokenProvider.createAccessToken(user.getId(), user.getLogin(), user.getPassword());
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getId(), user.getLogin());

        Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);

        jwtResponse.setId(user.getId());
        jwtResponse.setLogin(user.getLogin());
        jwtResponse.setAccessToken(accessToken);
        jwtResponse.setRefreshToken(refreshToken);

        return jwtResponse;
    }

    public JwtResponse refreshToken(
            final String refreshToken
    ) {
        return jwtTokenProvider.refreshUserTokens(refreshToken);
    }
}