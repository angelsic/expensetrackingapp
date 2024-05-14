package com.project.expensetrackingapp.exception.category;

public class CategoryNotFound extends RuntimeException{
    public CategoryNotFound(){ super("Category is not defined");}
}
