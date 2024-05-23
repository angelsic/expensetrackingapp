package com.project.expensetrackingapp.exception.user;

/**
 * Manage exception when User Already Exist
 * @author Angel Sic
 */
public class UserAlreadyExist extends RuntimeException{

    private String username;
    public UserAlreadyExist(String username){
        super("User " + username + " already exist!");
    }
}
