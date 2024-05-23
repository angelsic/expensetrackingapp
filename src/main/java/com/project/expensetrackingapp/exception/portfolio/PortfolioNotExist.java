package com.project.expensetrackingapp.exception.portfolio;

/**
 * Manage exception when Portfolio not exist
 * @author Angel Sic
 */
public class PortfolioNotExist extends RuntimeException{
    public PortfolioNotExist(String name){
        super("Portfolio " + name + " not exist");
    }
}
