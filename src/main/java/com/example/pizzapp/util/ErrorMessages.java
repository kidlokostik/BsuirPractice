package com.example.pizzapp.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorMessages {
    public static final String VALIDATION_FAILED_MESSAGE = "Validation failed";

    public static final String HTTP_MESSAGE_NOT_READABLE_MESSAGE = "Request body is missing or could not be read";

    public static final String INTERNAL_SERVER_ERROR_MESSAGE = "An unexpected error occurred.";

    public static final String NOT_FOUND_MESSAGE = "%s not found%s.";

    public static final String PASSWORDS_DO_NOT_MATCH = "Passwords do not match";

    public static final String ALREADY_USED_MESSAGE = "Duplicate or already used value: %s";

    public static final String ACCESS_DENIED_MESSAGE = "Access denied";
}