package com.project.expensetrackingapp.repository.finance;

import com.project.expensetrackingapp.repository.entity.finance.Finance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class FinancePostgresDatabaseStrategy implements FinanceDatabaseStrategy{

    @Autowired
    private FinanceRepositoryPostgres repository;

    @Override
    public List<Finance> findByPortfolioId(Long id) {
        return repository.findByPortfolioId(id);
    }

    @Override
    public List<Finance> findAll() {
        return (List<Finance>) repository.findAll();
    }

    @Override
    public Finance save(Finance entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Finance> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Finance> findByTypeFinanceId(Long id) {
        return repository.findByTypeFinanceId(id);
    }

    @Override
    public List<Finance> findByCategoryId(Long id) {
        return repository.findByCategoryId(id);
    }

    @Override
    public List<Finance> findByDateTimeBetween(LocalDateTime start, LocalDateTime end) {
        return repository.findByDateTimeBetween(start, end);
    }

    @Override
    public List<Finance> findByAmountBetween(double init, double end) {
        return repository.findByAmountBetween(init, end);
    }

    @Override
    public Finance findByName(String name) {
        return repository.findByName(name);
    }

}
