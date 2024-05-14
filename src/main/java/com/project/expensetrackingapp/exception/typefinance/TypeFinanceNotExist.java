package com.project.expensetrackingapp.exception.typefinance;

public class TypeFinanceNotExist extends RuntimeException{
    public TypeFinanceNotExist(String name){ super("Type Finance " + name + " not exist");}
}
