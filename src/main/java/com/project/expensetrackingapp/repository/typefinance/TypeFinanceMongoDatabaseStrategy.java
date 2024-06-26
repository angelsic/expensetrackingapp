package com.project.expensetrackingapp.repository.typefinance;

import com.project.expensetrackingapp.repository.entity.typefinance.TypeFinance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Type Finance Mongodb Database Strategy access
 * @author Angel Sic
 */
@Repository
public class TypeFinanceMongoDatabaseStrategy implements TypeFinanceDatabaseStrategy{
    @Autowired
    private TypeFinanceRepositoryMongo repository;

    @Override
    public TypeFinance findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<TypeFinance> findAll() {
        return (List<TypeFinance>) repository.findAll();
    }

    @Override
    public TypeFinance save(TypeFinance entity) {
        if(entity.getId() == 0) {
            entity.setId(Math.abs(System.currentTimeMillis() + UUID.randomUUID().getMostSignificantBits()));
        }
        return repository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<TypeFinance> findById(Long id) {
        return repository.findById(id);
    }
}
