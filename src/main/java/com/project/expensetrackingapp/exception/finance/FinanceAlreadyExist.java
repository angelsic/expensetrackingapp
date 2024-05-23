package com.project.expensetrackingapp.exception.finance;

/**
 * Manage exception when Finance Already Exist
 * @author Angel Sic
 */
public class FinanceAlreadyExist extends RuntimeException{
    public FinanceAlreadyExist(String message){
        super("Finance " + message + " already exist");
    }
}
