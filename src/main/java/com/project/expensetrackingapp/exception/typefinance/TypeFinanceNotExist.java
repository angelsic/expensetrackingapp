package com.project.expensetrackingapp.exception.typefinance;

/**
 * Manage exception when Type Finance Not Exist
 * @author Angel Sic
 */
public class TypeFinanceNotExist extends RuntimeException{
    public TypeFinanceNotExist(String name){ super("Type Finance " + name + " not exist");}
}
