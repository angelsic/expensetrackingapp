package com.project.expensetrackingapp.exception;

import org.springframework.http.HttpStatus;

/**
 * Manage exception when JSON Web Token is incorrect
 * @author Angel Sic
 */
public class JwtException extends RuntimeException {
    private final HttpStatus status;

    public JwtException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
