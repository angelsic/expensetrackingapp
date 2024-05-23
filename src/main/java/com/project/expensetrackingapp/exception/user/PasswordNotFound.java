package com.project.expensetrackingapp.exception.user;

/**
 * Manage exception when Not found User Password
 * @author Angel Sic
 */
public class PasswordNotFound extends RuntimeException{
    public PasswordNotFound(){
        super("Password is not defined");
    }
}
