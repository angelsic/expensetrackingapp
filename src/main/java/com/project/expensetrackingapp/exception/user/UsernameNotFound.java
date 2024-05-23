package com.project.expensetrackingapp.exception.user;

/**
 * Manage exception when Username Not Found
 * @author Angel Sic
 */
public class UsernameNotFound extends RuntimeException{
    public UsernameNotFound(){
        super("Username is not defined");
    }
}
