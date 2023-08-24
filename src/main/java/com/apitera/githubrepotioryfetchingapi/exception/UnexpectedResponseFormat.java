package com.apitera.githubrepotioryfetchingapi.exception;

public class UnexpectedResponseFormat extends RuntimeException {
    public UnexpectedResponseFormat(String format) {
        super(format);
    }
}
