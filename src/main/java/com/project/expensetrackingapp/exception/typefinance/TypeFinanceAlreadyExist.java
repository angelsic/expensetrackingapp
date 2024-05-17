package com.project.expensetrackingapp.exception.typefinance;

public class TypeFinanceAlreadyExist extends RuntimeException{
    public TypeFinanceAlreadyExist(String name){ super("Type Finance " + name + " already exist!");}
}
