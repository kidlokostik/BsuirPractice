package com.example.pizzapp.component;

import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailValidatorComponent {

    public EmailValidator emailValidator;

    public EmailValidatorComponent (EmailValidator emailValidator){
        emailValidator = new EmailValidator();
        emailValidator.initialize(null);
    }

    public boolean isValid(String login){
        return emailValidator.isValid(login, null);
    }
}
