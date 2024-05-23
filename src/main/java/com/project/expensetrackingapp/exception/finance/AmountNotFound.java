package com.project.expensetrackingapp.exception.finance;

/**
 * Manage exception when not found Amount
 * @author Angel Sic
 */
public class AmountNotFound extends RuntimeException{
    public AmountNotFound(){
        super("Amount is not defined");
    }
}
