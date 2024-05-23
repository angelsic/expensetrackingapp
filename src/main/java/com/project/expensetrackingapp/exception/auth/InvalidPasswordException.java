package com.project.expensetrackingapp.exception.auth;

/**
 * Manage exception for Invalid Password
 * @author Angel Sic
 */
public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException(String message){
        super(message);
    }
}
