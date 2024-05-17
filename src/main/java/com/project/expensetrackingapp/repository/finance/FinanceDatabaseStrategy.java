package com.project.expensetrackingapp.repository.finance;

import com.project.expensetrackingapp.repository.entity.finance.Finance;
import com.project.expensetrackingapp.repository.entity.finance.FinanceReport;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.mongodb.core.query.Criteria;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface FinanceDatabaseStrategy {
    List<Finance> findByPortfolioId(Long id);
    List<Finance> findAll();
    Finance save(Finance entity);
    void deleteById(Long id);
    Optional<Finance> findById(Long id);
    List<Finance> findByTypeFinanceId (Long id);
    List<Finance> findByCategoryId(Long id);
    List<Finance> findByDateTimeBetween(LocalDateTime start, LocalDateTime end);
    List<Finance> findByAmountBetween(double init, double end);
    Finance findByName(String name);
    List<FinanceReport> getFinanceReport(LocalDateTime start, LocalDateTime end, Long id);
}
