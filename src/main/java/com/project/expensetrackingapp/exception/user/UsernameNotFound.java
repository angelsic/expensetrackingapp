package com.project.expensetrackingapp.exception.user;

public class UsernameNotFound extends RuntimeException{
    public UsernameNotFound(String message){
        super("Username is not defined");
    }
}
