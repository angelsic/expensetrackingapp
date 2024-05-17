package com.project.expensetrackingapp.repository.portfolio;

import com.project.expensetrackingapp.repository.entity.portfolio.Portfolio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioRepositoryPostgres extends CrudRepository<Portfolio, Long> {
    Portfolio findByName(String name);

}
