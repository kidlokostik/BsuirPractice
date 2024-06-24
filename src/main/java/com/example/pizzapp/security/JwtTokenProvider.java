package com.example.pizzapp.security;

import com.example.pizzapp.dto.response.UserResponse;
import com.example.pizzapp.mapper.UserMapper;
import com.example.pizzapp.security.dto.JwtResponse;
import com.example.pizzapp.security.prop.JwtProperties;
import com.example.pizzapp.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final UserMapper userMapper;
    private Key key;

    @PostConstruct
    public void init(){
        this.key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
    }

    public String createAccessToken(Long userId, String name, Role role){
        Claims claims = Jwts.claims()
                .setSubject(name)
                .build();
        claims.put("id", userId);
        claims.put("role", role.toString());
        Date now = new Date();
        Date validity = new Date(now.getTime() + jwtProperties.getAccess());
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key)
                .compact();
    }

    public enum Role{ //Потом удалить
        ADMIN, CUSTOMER
    }

    public String createRefreshToken(Long userId, String name){
        Claims claims = Jwts.claims()
                .setSubject(name)
                .build();
        claims.put("id", userId);
        Date now = new Date();
        Date validity = new Date(now.getTime() + jwtProperties.getRefresh());
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key)
                .compact();
    }

    public JwtResponse refreshUserTokens(String refreshToken){
        JwtResponse jwtResponse = new JwtResponse();
        if (!validateToken(refreshToken)){
            throw new AccessDeniedException();
        }
        Long userId = Long.valueOf(getIdFromToken(refreshToken));
        UserResponse userResponse = userService.getUserById(userId);
        jwtResponse.setId(userId);
        jwtResponse.setUsername(userResponse.name());
        jwtResponse.setAccessToken(createAccessToken(userId, userResponse.name(), userResponse.role()));
        jwtResponse.setRefreshToken(createRefreshToken(userId, userResponse.name()));
        return jwtResponse;
    }

    private boolean validateToken(String token){
        Jws<Claims> claims = Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
        return !claims.getBody().getExpiration().before(new Date());
    }

    private String getIdFromToken(String token){
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("id")
                .toString();
    }
}
