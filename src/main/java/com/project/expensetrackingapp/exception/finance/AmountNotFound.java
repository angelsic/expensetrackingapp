package com.project.expensetrackingapp.exception.finance;

public class AmountNotFound extends RuntimeException{
    public AmountNotFound(){
        super("Amount is not defined");
    }
}
