package com.project.expensetrackingapp.exception.category;

public class CategoryNotExist extends RuntimeException{
    public CategoryNotExist(String name){ super("Category " + name + " not exist");}
}
