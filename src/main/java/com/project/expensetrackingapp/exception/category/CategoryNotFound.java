package com.project.expensetrackingapp.exception.category;

/**
 * Manage exception when not found Category
 * @author Angel Sic
 */
public class CategoryNotFound extends RuntimeException{
    public CategoryNotFound(){ super("Category is not defined");}
}
