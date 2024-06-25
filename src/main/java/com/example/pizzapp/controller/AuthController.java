package com.example.pizzapp.controller;

import com.example.pizzapp.security.JwtTokenProvider;
import com.example.pizzapp.security.dto.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public Map<String, String> login(@RequestParam String login, @RequestParam String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));

        UserDetails userDetails = userDetailsService.loadUserByUsername(login);

        String accessToken = jwtTokenProvider.createAccessToken(userDetails.getId(), userDetails.getUsername(), userDetails.getAuthorities().toString());
        String refreshToken = jwtTokenProvider.createRefreshToken(userDetails.getId(), userDetails.getUsername());

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);
        return tokens;
    }

    @PostMapping("/refresh")
    public Map<String, String> refresh(@RequestParam String refreshToken) {
        JwtResponse jwtResponse = jwtTokenProvider.refreshUserTokens(refreshToken);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", jwtResponse.getAccessToken());
        tokens.put("refreshToken", jwtResponse.getRefreshToken());
        return tokens;
    }
}