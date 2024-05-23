package com.project.expensetrackingapp.repository.typefinance;

import com.project.expensetrackingapp.repository.entity.typefinance.TypeFinance;

import java.util.List;
import java.util.Optional;

/**
 * Type Finance Database Strategy Interface
 * @author Angel Sic
 */
public interface TypeFinanceDatabaseStrategy {
    TypeFinance findByName(String name);
    List<TypeFinance> findAll();
    TypeFinance save(TypeFinance entity);
    void deleteById(Long id);
    Optional<TypeFinance> findById(Long id);
}
