package com.project.expensetrackingapp.exception.category;

/**
 * Manage exception if Category Already Exist
 * @author Angel Sic
 */
public class CategoryAlreadyExist extends RuntimeException{
    public CategoryAlreadyExist(String name){ super("Category " + name + " already exist!");}
}
