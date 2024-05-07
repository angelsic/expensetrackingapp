package com.project.expensetrackingapp.exception.user;

public class PasswordNotFound extends RuntimeException{
    public PasswordNotFound(){
        super("Password is not defined");
    }
}
