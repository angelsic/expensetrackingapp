package com.project.expensetrackingapp.exception.category;

public class CategoryAlreadyExist extends RuntimeException{
    public CategoryAlreadyExist(String name){ super("Category " + name + " already exist!");}
}
