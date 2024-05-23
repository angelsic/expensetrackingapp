package com.project.expensetrackingapp.exception;

/**
 * Manage exception when Id request is not Found
 * @author Angel Sic
 */
public class IdNotFound extends RuntimeException{
    public IdNotFound(String message){
        super(message);
    }
}
