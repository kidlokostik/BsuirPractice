package com.example.pizzapp.component;

import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.stereotype.Component;

@Component
public class EmailValidatorComponent {

    public EmailValidator emailValidator;

    public EmailValidatorComponent (){
        emailValidator = new EmailValidator();
        emailValidator.initialize(null);
    }

    public boolean isValid(String login){
        return emailValidator.isValid(login, null);
    }
}
