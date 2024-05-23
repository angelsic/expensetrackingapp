package com.project.expensetrackingapp.service.portfolio;

import com.project.expensetrackingapp.repository.entity.portfolio.Portfolio;

import java.util.List;

/**
 * Portfolio Service Interface
 * @author Angel Sic
 */
public interface PortfolioService {
    Portfolio savePortfolio(Portfolio portfolio);
    Portfolio getPortfolio(String name);
    List<Portfolio> getAllPortfolio();
    Portfolio updatePortfolio(Portfolio portfolio);
    String deletePortfolio(String name);
}
