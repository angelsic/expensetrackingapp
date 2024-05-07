package com.project.expensetrackingapp.exception.user;

public class UserNotExist extends RuntimeException{
    public UserNotExist(String username){
        super("User " + username + " not exist");
    }
}
