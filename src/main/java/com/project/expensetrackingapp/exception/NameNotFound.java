package com.project.expensetrackingapp.exception;

/**
 * Manage exception when Name request is not Found
 * @author Angel Sic
 */
public class NameNotFound extends RuntimeException{
    public NameNotFound(String message){
        super(message);
    }
}
