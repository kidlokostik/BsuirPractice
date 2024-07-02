package com.example.pizzapp.security;

import com.example.pizzapp.dto.response.UserResponse;
import com.example.pizzapp.exception.AccessDeniedException;
import com.example.pizzapp.security.dto.JwtResponse;
import com.example.pizzapp.security.prop.JwtProperties;
import com.example.pizzapp.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.example.pizzapp.util.ErrorMessages.ACCESS_DENIED_MESSAGE;

@Service
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private SecretKey key;

    @PostConstruct
    public void init(){
        this.key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
    }

    public String createAccessToken(Long userId, String login, String role){
        Map<String, Object> claims = new HashMap<>();

        claims.put("id", userId);
        claims.put("role", role);

        Date now = new Date();
        Date validity = new Date(now.getTime() + jwtProperties.getAccess());

        return Jwts.builder()
                .subject(login)
                .claims(claims)
                .issuedAt(now)
                .expiration(validity)
                .signWith(key)
                .compact();
    }

    public String createRefreshToken(Long userId, String login){
        Map<String, Object> claims = new HashMap<>();

        claims.put("id", userId);

        Date now = new Date();
        Date validity = new Date(now.getTime() + jwtProperties.getRefresh());

        return Jwts.builder()
                .subject(login)
                .claims(claims)
                .issuedAt(now)
                .expiration(validity)
                .signWith(key)
                .compact();
    }

    public JwtResponse refreshUserTokens(String refreshToken) {
        if (!validateToken(refreshToken)){
            throw new AccessDeniedException(String.format(ACCESS_DENIED_MESSAGE));
        }
        Long userId = Long.valueOf(getIdFromToken(refreshToken));
        UserResponse userResponse = userService.getUserById(userId);

        String login = userResponse.login();
        String accessToken = createAccessToken(userId, userResponse.login(), userResponse.role());
        String newRefreshToken = createRefreshToken(userId, userResponse.login());

        return new JwtResponse(userId, login, accessToken, newRefreshToken);
    }

    public boolean validateToken(String token){
        Jws<Claims> claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token);
        return !claims.getPayload().getExpiration().before(new Date());
    }

    private String getIdFromToken(String token){
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("id")
                .toString();
    }

    private String getLoginFromToken(String token){
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public Authentication getAuthentication(String token){
        String login = getLoginFromToken(token);
        
        UserDetails userDetails = userDetailsService.loadUserByUsername(login);

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
