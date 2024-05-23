package com.project.expensetrackingapp.exception.typefinance;

/**
 * Manage exception when Type Finance Already Exist
 * @author Angel Sic
 */
public class TypeFinanceAlreadyExist extends RuntimeException{
    public TypeFinanceAlreadyExist(String name){ super("Type Finance " + name + " already exist!");}
}
