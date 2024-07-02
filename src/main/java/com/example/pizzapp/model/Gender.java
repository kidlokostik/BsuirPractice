package com.example.pizzapp.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    MALE, FEMALE, OTHER;

    @JsonValue
    public String getName(){
        return name();
    }
}
