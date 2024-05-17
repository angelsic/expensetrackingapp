package com.project.expensetrackingapp.exception.portfolio;

public class PortfolioAlreadyExist extends RuntimeException{
    public PortfolioAlreadyExist(String name){
        super("Portfolio " + name + " already exist!");
    }
}
