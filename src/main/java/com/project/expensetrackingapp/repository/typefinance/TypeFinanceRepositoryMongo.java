package com.project.expensetrackingapp.repository.typefinance;

import com.project.expensetrackingapp.repository.entity.typefinance.TypeFinance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeFinanceRepositoryMongo extends MongoRepository<TypeFinance, Long> {
    TypeFinance findByName(String name);
}
