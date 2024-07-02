package com.example.pizzapp.security.expression;

import com.example.pizzapp.model.Order;
import com.example.pizzapp.model.RoleType;
import com.example.pizzapp.repository.OrderRepository;
import com.example.pizzapp.security.JwtEntity;
import com.example.pizzapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("E")
@RequiredArgsConstructor
public class CustomSecurityExpression {

    private final UserService userService;
    private final OrderRepository orderRepository;

    public boolean canAccessUser(Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        JwtEntity user = (JwtEntity) authentication.getPrincipal();

        Long userId = user.getId();

        return userId.equals(id) || hasAnyRole(authentication, RoleType.ADMIN);
    }

    private boolean hasAnyRole(Authentication authentication, RoleType... roles){
        for(RoleType role : roles){
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
            if (authentication.getAuthorities().contains(authority)){
                return true;
            }
        }
        return false;
    }

    public boolean canAccessOrder(Long orderId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Order order;

        JwtEntity jwtEntity = (JwtEntity) authentication.getPrincipal();

        if(orderRepository.findById(orderId).isPresent()){
            order = orderRepository.findById(orderId).get();
        } else {
            return false;
        }

        Long jwtEntityId = jwtEntity.getId();
        Long userId = order.getUser().getId();

        return jwtEntityId.equals(userId);
    }
}
