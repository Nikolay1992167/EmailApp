package com.solbeg.emailservice.enums;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    ERROR_EMAIL_TYPE("EmailType cannot be null."),
    ERROR_EMAIL_FORMAT("Invalid email format."),
    ERROR_NOT_EMPTY("Data cannot be empty.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }
}