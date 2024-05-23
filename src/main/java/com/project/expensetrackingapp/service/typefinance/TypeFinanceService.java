package com.project.expensetrackingapp.service.typefinance;

import com.project.expensetrackingapp.repository.entity.typefinance.TypeFinance;

import java.util.List;

/**
 * Type Finance Service Interface
 * @author Angel Sic
 */
public interface TypeFinanceService {
    TypeFinance saveTypeFinance(TypeFinance typeFinance);
    TypeFinance getTypeFinance(String name);
    List<TypeFinance> getAllTypeFinance();
    TypeFinance updateTypeFinance(TypeFinance typeFinance);
    String deleteTypeFinance(String name);
}
