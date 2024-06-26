package com.project.expensetrackingapp.repository.finance;

import com.project.expensetrackingapp.repository.entity.finance.Finance;
import com.project.expensetrackingapp.repository.entity.finance.FinanceReport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Finance Mongodb Connection Interface
 * @author Angel Sic
 */
@Repository
public interface FinanceRepositoryMongo extends MongoRepository<Finance, Long> {
    List<Finance> findByPortfolioId(Long id);
    List<Finance> findByTypeFinanceId (Long id);
    List<Finance> findByCategoryId(Long id);
    List<Finance> findByDateTimeBetween(LocalDateTime start, LocalDateTime end);
    List<Finance> findByAmountBetween(double init, double end);
    Finance findByName(String name);
    @Query(value = "[{$match: {dateTime: {$gte: ?0,$lte: ?1}, 'portfolio.id': ?2}}]")
    List<FinanceReport> getFinanceReport(@Param("startDate") LocalDateTime start,
                                         @Param("endDate") LocalDateTime end,
                                         @Param("portfolioId") Long id);
}
