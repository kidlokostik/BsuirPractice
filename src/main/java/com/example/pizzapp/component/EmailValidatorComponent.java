package com.example.pizzapp.component;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailValidatorComponent {

    private final EmailValidator emailValidator;

    @PostConstruct
    private void init(){
        emailValidator.initialize(null);
    }

    public boolean isValid(String login){
        return emailValidator.isValid(login, null);
    }
}
