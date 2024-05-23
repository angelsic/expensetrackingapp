package com.project.expensetrackingapp.exception.portfolio;

/**
 * Manage exception when Portfolio Already Exist
 * @author Angel Sic
 */
public class PortfolioAlreadyExist extends RuntimeException{
    public PortfolioAlreadyExist(String name){
        super("Portfolio " + name + " already exist!");
    }
}
