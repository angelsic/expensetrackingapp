package com.project.expensetrackingapp.repository.typefinance;

import com.project.expensetrackingapp.repository.entity.typefinance.TypeFinance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Type Finance Postgres Connection Interface
 * @author Angel Sic
 */
@Repository
public interface TypeFinanceRepositoryPostgres extends CrudRepository<TypeFinance, Long> {
    TypeFinance findByName(String name);
}
