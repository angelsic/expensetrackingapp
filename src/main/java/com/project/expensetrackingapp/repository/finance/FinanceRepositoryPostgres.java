package com.project.expensetrackingapp.repository.finance;

import com.project.expensetrackingapp.repository.entity.finance.Finance;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface FinanceRepositoryPostgres extends CrudRepository<Finance, Long> {
    List<Finance> findByPortfolioId(Long id);
    List<Finance> findByTypeFinanceId (Long id);
    List<Finance> findByCategoryId(Long id);
    List<Finance> findByDateTimeBetween(LocalDateTime start, LocalDateTime end);
    List<Finance> findByAmountBetween(double init, double end);
    Finance findByName(String name);
}
