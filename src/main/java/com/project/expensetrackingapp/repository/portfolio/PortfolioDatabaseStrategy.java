package com.project.expensetrackingapp.repository.portfolio;

import com.project.expensetrackingapp.repository.entity.portfolio.Portfolio;

import java.util.List;
import java.util.Optional;

/**
 * Portfolio Database Strategy Interface
 * @author Angel Sic
 */
public interface PortfolioDatabaseStrategy {
    Portfolio findByName(String name);
    List<Portfolio> findAll();
    Portfolio save(Portfolio entity);
    void deleteById(Long id);
    Optional<Portfolio> findById(Long id);
}
