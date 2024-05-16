package com.project.expensetrackingapp.repository.finance;

import com.project.expensetrackingapp.repository.entity.finance.Finance;
import com.project.expensetrackingapp.repository.entity.finance.FinanceReport;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
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

    @Query("SELECT new com.project.expensetrackingapp.repository.entity.finance.FinanceReport(tp.name, SUM(f.amount)) " +
            " FROM finance f " +
            " JOIN f.typeFinance tp " +
            " JOIN f.portfolio p " +
            " WHERE f.dateTime >= :startDate " +
            " AND f.dateTime <= :endDate " +
            " AND p.id = :portfolioId "+
            " GROUP BY tp.name ")
    List<FinanceReport> getFinanceReport(@Param("startDate") LocalDateTime start,
                                         @Param("endDate") LocalDateTime end,
                                         @Param("portfolioId") Long id);
}
