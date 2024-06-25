package com.example.pizzapp.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorMessages {

    public static final String NOT_FOUND_MESSAGE = "%s with id = %d not found.";

    public static final String DUPLICATE_FOUND_MESSAGE = "Duplicate in %s parameter. %s is already used.";

    public static final String VALIDATION_FAILED_MESSAGE = "Validation failed";

    public static final String HTTP_MESSAGE_NOT_READABLE_MESSAGE = "Request body is missing or could not be read";
}
