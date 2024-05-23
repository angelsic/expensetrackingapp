package com.project.expensetrackingapp.exception.category;

/**
 * Manage exception when Category not Exist
 * @author Angel Sic
 */
public class CategoryNotExist extends RuntimeException{
    public CategoryNotExist(String name){ super("Category " + name + " not exist");}
}
