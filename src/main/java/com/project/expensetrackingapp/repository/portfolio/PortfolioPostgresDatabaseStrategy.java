package com.project.expensetrackingapp.repository.portfolio;

import com.project.expensetrackingapp.repository.entity.portfolio.Portfolio;
import com.project.expensetrackingapp.repository.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class PortfolioPostgresDatabaseStrategy implements PortfolioDatabaseStrategy{

    @Autowired
    private PortfolioRepositoryPostgres repository;

    @Override
    public Portfolio findByName(String username) {
        return repository.findByName(username);
    }

    @Override
    public List<Portfolio> findAll() {
        return (List<Portfolio>) repository.findAll();
    }

    @Transactional
    @Override
    public Portfolio save(Portfolio entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(Long id){
        repository.deleteById(id);
    }

    @Override
    public Optional<Portfolio> findById(Long id) {
        return repository.findById(id);
    }
}
