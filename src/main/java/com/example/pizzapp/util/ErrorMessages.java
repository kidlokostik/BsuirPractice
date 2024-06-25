package com.example.pizzapp.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorMessages {
    public static final String NOT_FOUND_MESSAGE = "%s not found%s.";
    public static final String PASSWORDS_DO_NOT_MATCH = "Passwords do not match";
    public static final String ALREADY_USED_MESSAGE = "Duplicate or already used value: %s";

    public static String formatNotFoundMessage(String resourceName, Long id) {
        return String.format(NOT_FOUND_MESSAGE, resourceName, id != null ? " with id = " + id : "");
    }
}
