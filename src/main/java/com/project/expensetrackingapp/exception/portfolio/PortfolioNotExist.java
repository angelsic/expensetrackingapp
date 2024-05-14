package com.project.expensetrackingapp.exception.portfolio;

public class PortfolioNotExist extends RuntimeException{
    public PortfolioNotExist(String name){
        super("Portfolio " + name + " not exist");
    }
}
