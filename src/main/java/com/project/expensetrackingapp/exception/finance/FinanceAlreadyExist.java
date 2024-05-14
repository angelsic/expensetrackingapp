package com.project.expensetrackingapp.exception.finance;

public class FinanceAlreadyExist extends RuntimeException{
    public FinanceAlreadyExist(String message){
        super("Finance " + message + " already exist");
    }
}
