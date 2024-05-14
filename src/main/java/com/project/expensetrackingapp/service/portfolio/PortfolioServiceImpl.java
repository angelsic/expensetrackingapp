package com.project.expensetrackingapp.service.portfolio;

import com.project.expensetrackingapp.exception.IdNotFound;
import com.project.expensetrackingapp.exception.NameNotFound;
import com.project.expensetrackingapp.exception.portfolio.PortfolioAlreadyExist;
import com.project.expensetrackingapp.exception.portfolio.PortfolioNotExist;
import com.project.expensetrackingapp.exception.user.UserNotExist;
import com.project.expensetrackingapp.exception.user.UsernameNotFound;
import com.project.expensetrackingapp.repository.entity.portfolio.Portfolio;
import com.project.expensetrackingapp.repository.entity.user.User;
import com.project.expensetrackingapp.repository.portfolio.PortfolioDatabaseStrategy;
import com.project.expensetrackingapp.repository.user.UserDatabaseStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.naming.NameNotFoundException;
import java.util.List;

@Service
public class PortfolioServiceImpl implements PortfolioService{

    private PortfolioDatabaseStrategy portfolioDatabaseStrategy;

    private UserDatabaseStrategy userDatabaseStrategy;

    @Autowired
    public void setDatabaseStrategy(@Qualifier("portfolioStrategy") PortfolioDatabaseStrategy portfolioDatabaseStrategy){
        this.portfolioDatabaseStrategy = portfolioDatabaseStrategy;
    }

    @Autowired
    public void setUserDatabaseStrategy(@Qualifier("userStrategy") UserDatabaseStrategy userDatabaseStrategy){
        this.userDatabaseStrategy = userDatabaseStrategy;
    }

    @Override
    public Portfolio savePortfolio(Portfolio portfolio) {
        if(portfolio.getName() == null){
            throw new NameNotFound("Portfolio name is not defined");
        }else if(portfolio.getUser() == null || portfolio.getUser().getUsername() == null || portfolio.getUser().getUsername().isEmpty()){
            throw new UsernameNotFound();
        }
        Portfolio newPortfolio = portfolioDatabaseStrategy.findByName(portfolio.getName());
        if(newPortfolio == null){
            User user = userDatabaseStrategy.findByUsername(portfolio.getUser().getUsername());
            if(user != null) {
                portfolio.setUser(user);
                return portfolioDatabaseStrategy.save(portfolio);
            }
            throw new UserNotExist(portfolio.getUser().getUsername());
        }
        throw new PortfolioAlreadyExist(newPortfolio.getName());
    }

    @Override
    public Portfolio getPortfolio(String name) {
        return portfolioDatabaseStrategy.findByName(name);
    }

    @Override
    public List<Portfolio> getAllPortfolio() {
        return portfolioDatabaseStrategy.findAll();
    }

    @Override
    public Portfolio updatePortfolio(Portfolio portfolio) {
        if (portfolio.getId() == 0) {
            throw new IdNotFound("Portfolio Id is not defined");
        }else if(portfolio.getName() == null){
            throw new NameNotFound("Portfolio name is not defined");
        }else if(portfolio.getUser() == null || portfolio.getUser().getUsername() == null || portfolio.getUser().getUsername().isEmpty()){
            throw new UsernameNotFound();
        }
        Portfolio updPortfolio = portfolioDatabaseStrategy.findById(portfolio.getId()).orElse(null);
        if (updPortfolio != null){
            Portfolio updatePortfolio = portfolioDatabaseStrategy.findByName(portfolio.getName());
            if(updatePortfolio == null) {
                User user = userDatabaseStrategy.findByUsername(portfolio.getUser().getUsername());
                if (user != null) {
                    portfolio.setUser(user);
                    return portfolioDatabaseStrategy.save(portfolio);
                }
                throw new UserNotExist(portfolio.getUser().getUsername());
            }
            throw new PortfolioAlreadyExist(portfolio.getName());
        }
        throw new PortfolioNotExist(portfolio.getName());
    }

    @Override
    public String deletePortfolio(String name) {
        Portfolio portfolio = portfolioDatabaseStrategy.findByName(name);
        if(portfolio != null){
            portfolioDatabaseStrategy.deleteById(portfolio.getId());
            return name + " was removed";
        }else{
            return name + "not exist";
        }
    }
}
