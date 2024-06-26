package com.project.expensetrackingapp.service.finance;

import com.project.expensetrackingapp.repository.entity.finance.Finance;
import com.project.expensetrackingapp.repository.entity.finance.FinanceReport;
import com.project.expensetrackingapp.repository.entity.finance.FinanceResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Finance Service Interface
 * @author Angel Sic
 */
public interface FinanceService {
    List<Finance> getFinanceByPortfolioId(Long id);
    List<Finance> getAllFinance();
    Finance saveFinance(Finance finance);
    String deleteFinance(Long id);
    Optional<Finance> getFinance(Long id);
    Finance updateFinance(Finance finance);
    List<Finance> getFinanceByTypeFinanceId(Long id);
    List<Finance> getFinanceByCategoryId (Long id);
    List<Finance> getFinanceByDatetimeBetween (LocalDateTime start, LocalDateTime end);
    List<Finance> getFinanceByAmountBetween (double init, double end);
    List<FinanceResponse> getFinanceByFilter(Long id, Long portfolioId, Long typeFinanceId, Long categoryId, double initAmount, double endAmount, LocalDateTime start, LocalDateTime end);
    List<FinanceReport> getFinanceReport(LocalDateTime start, LocalDateTime end, Long id);
}
