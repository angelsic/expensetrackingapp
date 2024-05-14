package com.project.expensetrackingapp.service.finance;

import com.project.expensetrackingapp.exception.IdNotFound;
import com.project.expensetrackingapp.exception.NameNotFound;
import com.project.expensetrackingapp.exception.category.CategoryNotExist;
import com.project.expensetrackingapp.exception.category.CategoryNotFound;
import com.project.expensetrackingapp.exception.finance.AmountNotFound;
import com.project.expensetrackingapp.exception.finance.FinanceNotExist;
import com.project.expensetrackingapp.exception.portfolio.PortfolioNotExist;
import com.project.expensetrackingapp.exception.typefinance.TypeFinanceNotExist;
import com.project.expensetrackingapp.exception.typefinance.TypeFinanceNotFound;
import com.project.expensetrackingapp.repository.category.CategoryDatabaseStrategy;
import com.project.expensetrackingapp.repository.entity.category.Category;
import com.project.expensetrackingapp.repository.entity.finance.Finance;
import com.project.expensetrackingapp.repository.entity.portfolio.Portfolio;
import com.project.expensetrackingapp.repository.entity.typefinance.TypeFinance;
import com.project.expensetrackingapp.repository.finance.FinanceDatabaseStrategy;
import com.project.expensetrackingapp.repository.portfolio.PortfolioDatabaseStrategy;
import com.project.expensetrackingapp.repository.typefinance.TypeFinanceDatabaseStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class FinanceServiceImpl implements  FinanceService{

    private FinanceDatabaseStrategy financeDatabaseStrategy;
    private PortfolioDatabaseStrategy portfolioDatabaseStrategy;
    private TypeFinanceDatabaseStrategy typeFinanceDatabaseStrategy;
    private CategoryDatabaseStrategy categoryDatabaseStrategy;

    @Autowired
    public void setDatabaseStrategy(@Qualifier("financeStrategy") FinanceDatabaseStrategy financeDatabaseStrategy){
        this.financeDatabaseStrategy = financeDatabaseStrategy;
    }

    @Autowired
    public void setPortfolioDatabaseStrategy(@Qualifier("portfolioStrategy") PortfolioDatabaseStrategy portfolioDatabaseStrategy){
        this.portfolioDatabaseStrategy = portfolioDatabaseStrategy;
    }

    @Autowired
    public void setTypeFinanceDatabaseStrategy(@Qualifier("typeFinanceStrategy") TypeFinanceDatabaseStrategy typeFinanceDatabaseStrategy){
        this.typeFinanceDatabaseStrategy = typeFinanceDatabaseStrategy;
    }

    @Autowired
    public void setCategoryDatabaseStrategy(@Qualifier("categoryStrategy") CategoryDatabaseStrategy categoryDatabaseStrategy){
        this.categoryDatabaseStrategy = categoryDatabaseStrategy;
    }

    @Override
    public List<Finance> getFinanceByPortfolioId(Long id) {
        return financeDatabaseStrategy.findByPortfolioId(id);
    }

    @Override
    public List<Finance> getAllFinance() {
        return financeDatabaseStrategy.findAll();
    }

    @Override
    public Finance saveFinance(Finance finance) {
        if(finance.getPortfolio().getName() == null){
            throw new NameNotFound("Portofio is not defined");
        }else if(finance.getTypeFinance().getName() == null){
            throw new TypeFinanceNotFound();
        }else if(finance.getCategory().getName() == null) {
            throw new CategoryNotFound();
        }else if (finance.getName() == null){
            throw new NameNotFound("Name is not defined");
        }else if (finance.getAmount() == 0){
            throw new AmountNotFound();
        }

        Portfolio portfolio = portfolioDatabaseStrategy.findByName(finance.getPortfolio().getName());
        if(portfolio != null){
            finance.setPortfolio(portfolio);
            TypeFinance typeFinance = typeFinanceDatabaseStrategy.findByName(
                    finance.getTypeFinance().getName()
            );
            if(typeFinance != null){
                finance.setTypeFinance(typeFinance);
                Category category = categoryDatabaseStrategy.findByName(
                        finance.getCategory().getName()
                );
                if(category != null){
                    finance.setCategory(category);
                    finance.setDateTime(LocalDateTime.now());
                    return financeDatabaseStrategy.save(finance);
                }
                throw new CategoryNotExist(finance.getCategory().getName());
            }
            throw new TypeFinanceNotExist(finance.getTypeFinance().getName());
        }
        throw new PortfolioNotExist(finance.getPortfolio().getName());
    }

    @Override
    public String deleteFinance(Long id) {
        Finance finance = financeDatabaseStrategy.findById(id).orElse(null);
        if(finance != null){
            financeDatabaseStrategy.deleteById(id);
            return finance.getName() + " was removed";
        }
        return "Finance " + id + " not exist";
    }

    @Override
    public Optional<Finance> getFinance(Long id) {
        return financeDatabaseStrategy.findById(id);
    }

    @Override
    public Finance updateFinance(Finance finance) {
        if(finance.getId() == 0){
            throw new IdNotFound("Id is not defined");
        }else if(finance.getPortfolio().getName() == null){
            throw new NameNotFound("Portofio is not defined");
        }else if(finance.getTypeFinance().getName() == null){
            throw new TypeFinanceNotFound();
        }else if(finance.getCategory().getName() == null) {
            throw new CategoryNotFound();
        }else if (finance.getName() == null){
            throw new NameNotFound("Name is not defined");
        }else if (finance.getAmount() == 0){
            throw new AmountNotFound();
        }

        Finance updFinance = financeDatabaseStrategy.findById(finance.getId()).orElse(null);
        if(updFinance != null) {
            Portfolio portfolio = portfolioDatabaseStrategy.findByName(finance.getPortfolio().getName());
            if (portfolio != null) {
                finance.setPortfolio(portfolio);
                TypeFinance typeFinance = typeFinanceDatabaseStrategy.findByName(
                        finance.getTypeFinance().getName()
                );
                if (typeFinance != null) {
                    finance.setTypeFinance(typeFinance);
                    Category category = categoryDatabaseStrategy.findByName(
                            finance.getCategory().getName()
                    );
                    if (category != null) {
                        finance.setCategory(category);
                        finance.setDateTime(LocalDateTime.now());
                        return financeDatabaseStrategy.save(finance);
                    }
                    throw new CategoryNotExist(finance.getCategory().getName());
                }
                throw new TypeFinanceNotExist(finance.getTypeFinance().getName());
            }
            throw new PortfolioNotExist(finance.getPortfolio().getName());
        }
        throw new FinanceNotExist();
    }

    @Override
    public List<Finance> getFinanceByTypeFinanceId(Long id) {
        return financeDatabaseStrategy.findByTypeFinanceId(id);
    }

    @Override
    public List<Finance> getFinanceByCategoryId(Long id) {
        return financeDatabaseStrategy.findByCategoryId(id);
    }

    @Override
    public List<Finance> getFinanceByDatetimeBetween(LocalDateTime start, LocalDateTime end) {
        return financeDatabaseStrategy.findByDateTimeBetween(start, end);
    }

    @Override
    public List<Finance> getFinanceByAmountBetween(double init, double end) {
        return financeDatabaseStrategy.findByAmountBetween(init, end);
    }

    @Override
    public List<Finance> getFinanceByFilter(Long id, Long porfolioId, Long typeFinanceId
            , Long categoryId, double initAmount, double endAmount
            , LocalDateTime start, LocalDateTime end) {

        List<Finance> finances = financeDatabaseStrategy.findAll();
        Predicate<Finance> predicate = finance -> {
            boolean matches = true;
            if(id != null){
                matches = matches && finance.getId() == id;
            }
            if(porfolioId != null){
                matches = matches && finance.getPortfolio().getId() == porfolioId;
            }
            if(typeFinanceId != null){
                matches = matches && finance.getTypeFinance().getId() == typeFinanceId;
            }
            if(categoryId != null){
                matches = matches && finance.getCategory().getId() == categoryId;
            }
            if(initAmount != 0 && endAmount != 0){
                matches = matches && finance.getAmount() >= initAmount
                        && finance.getAmount() <= endAmount;
            }else if (initAmount != 0){
                matches = matches && finance.getAmount() >= initAmount;
            }else if (endAmount != 0){
                matches = matches && finance.getAmount() <= endAmount;
            }
            if(start != null && end != null){
                matches = matches && finance.getDateTime().isAfter(start)
                        && finance.getDateTime().isBefore(end);
            }else if (start != null){
                matches = matches && finance.getDateTime().isAfter(start);
            }else if (end != null){
                matches = matches && finance.getDateTime().isBefore(end);
            }
            return matches;
        };
        return finances.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}
