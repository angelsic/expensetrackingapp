package com.project.expensetrackingapp.exception.finance;

/**
 * Manage exception when Finance Not Exist
 * @author Angel Sic
 */
public class FinanceNotExist extends RuntimeException{
    public FinanceNotExist(){
        super("Finance not exist!");
    }
}
