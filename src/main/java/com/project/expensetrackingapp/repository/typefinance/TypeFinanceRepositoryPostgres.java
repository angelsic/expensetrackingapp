package com.project.expensetrackingapp.repository.typefinance;

import com.project.expensetrackingapp.repository.entity.typefinance.TypeFinance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeFinanceRepositoryPostgres extends CrudRepository<TypeFinance, Long> {
    TypeFinance findByName(String name);
}
