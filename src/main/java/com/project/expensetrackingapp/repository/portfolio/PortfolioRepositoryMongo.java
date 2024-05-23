package com.project.expensetrackingapp.repository.portfolio;

import com.project.expensetrackingapp.repository.entity.portfolio.Portfolio;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Portfolio Mongodb Connection Interface
 * @author Angel Sic
 */
@Repository
public interface PortfolioRepositoryMongo extends MongoRepository<Portfolio, Long> {
    Portfolio findByName(String name);
}
