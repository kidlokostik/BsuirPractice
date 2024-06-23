package com.example.pizzapp.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorMessages {
    public static final String NOT_FOUND_MESSAGE = "%s with id = %d not found.";
    public static final String DUPLICATE_FOUND = "Duplicate in %s parameter. %s is already used.";
}
