package com.project.expensetrackingapp.exception.user;

/**
 * Manage exception when User Not Exist
 * @author Angel Sic
 */
public class UserNotExist extends RuntimeException{
    public UserNotExist(String username){
        super("User " + username + " not exist");
    }
}
