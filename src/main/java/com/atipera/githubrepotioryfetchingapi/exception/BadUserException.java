package com.atipera.githubrepotioryfetchingapi.exception;

public class BadUserException extends RuntimeException {
    public BadUserException(String format) {
        super(format);
    }
}
