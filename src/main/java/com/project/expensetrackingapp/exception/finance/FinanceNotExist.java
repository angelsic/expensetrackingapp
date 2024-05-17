package com.project.expensetrackingapp.exception.finance;

public class FinanceNotExist extends RuntimeException{
    public FinanceNotExist(){
        super("Finance not exist!");
    }
}
