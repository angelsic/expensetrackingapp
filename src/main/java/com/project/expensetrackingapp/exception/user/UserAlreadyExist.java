package com.project.expensetrackingapp.exception.user;

public class UserAlreadyExist extends RuntimeException{

    private String username;
    public UserAlreadyExist(String username){
        super("User " + username + " already exist!");
    }
}
