package com.project.expensetrackingapp.repository.portfolio;

import com.project.expensetrackingapp.repository.entity.portfolio.Portfolio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Portfolio Mongodb Database Strategy access
 * @author Angel Sic
 */
@Repository
public class PortfolioMongoDatabaseStrategy implements PortfolioDatabaseStrategy {

    @Autowired
    private PortfolioRepositoryMongo repository;

    @Override
    public Portfolio findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Portfolio> findAll() {
        return repository.findAll();
    }

    @Override
    public Portfolio save(Portfolio entity) {
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
    public Optional<Portfolio> findById(Long id) {
        return repository.findById(id);
    }
}
