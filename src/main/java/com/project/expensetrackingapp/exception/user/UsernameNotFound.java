package com.project.expensetrackingapp.exception.user;

public class UsernameNotFound extends RuntimeException{
    public UsernameNotFound(){
        super("Username is not defined");
    }
}
