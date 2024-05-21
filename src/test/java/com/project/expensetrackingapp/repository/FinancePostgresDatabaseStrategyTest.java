package com.project.expensetrackingapp.repository;

import com.project.expensetrackingapp.repository.entity.category.Category;
import com.project.expensetrackingapp.repository.entity.finance.Finance;
import com.project.expensetrackingapp.repository.entity.finance.FinanceReport;
import com.project.expensetrackingapp.repository.entity.portfolio.Portfolio;
import com.project.expensetrackingapp.repository.entity.typefinance.TypeFinance;
import com.project.expensetrackingapp.repository.entity.user.User;
import com.project.expensetrackingapp.repository.finance.FinancePostgresDatabaseStrategy;
import com.project.expensetrackingapp.repository.finance.FinanceRepositoryPostgres;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class FinancePostgresDatabaseStrategyTest {

    @InjectMocks
    private FinancePostgresDatabaseStrategy financePostgresDatabaseStrategy;

    @Mock
    private FinanceRepositoryPostgres financeRepositoryPostgres;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindByPortfolioId() {
        Long portfolioId = 1L;
        List<Finance> finances = new ArrayList<>();
        Finance finance1 = new Finance();
        finance1.setPortfolio(new Portfolio(portfolioId, "Portfolio 1", new User()));
        Finance finance2 = new Finance();
        finance2.setPortfolio(new Portfolio(2L, "Portfolio 2", new User()));
        finances.add(finance1);
        finances.add(finance2);

        when(financeRepositoryPostgres.findByPortfolioId(portfolioId)).thenReturn(finances);

        List<Finance> result = financePostgresDatabaseStrategy.findByPortfolioId(portfolioId);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(Optional.of(portfolioId), Optional.of(result.get(0).getPortfolio().getId()));
    }

    @Test
    void testFindAll() {
        List<Finance> finances = new ArrayList<>();
        finances.add(new Finance());
        finances.add(new Finance());

        when(financeRepositoryPostgres.findAll()).thenReturn(finances);

        List<Finance> result = financePostgresDatabaseStrategy.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testSave() {
        Finance finance = new Finance();
        finance.setName("New Finance");

        when(financeRepositoryPostgres.save(finance)).thenReturn(finance);

        Finance savedFinance = financePostgresDatabaseStrategy.save(finance);

        assertNotNull(savedFinance);
    }

    @Test
    void testDeleteById() {
        Long financeId = 1L;

        financePostgresDatabaseStrategy.deleteById(financeId);

        verify(financeRepositoryPostgres, times(1)).deleteById(financeId);
    }

    @Test
    void testFindById() {
        Long financeId = 1L;
        Finance finance = new Finance();
        finance.setId(financeId);

        when(financeRepositoryPostgres.findById(financeId)).thenReturn(Optional.of(finance));

        Optional<Finance> result = financePostgresDatabaseStrategy.findById(financeId);

        assertNotNull(result);
        assertEquals(Optional.of(financeId), Optional.of(result.get().getId()));
    }

    @Test
    void testFindByTypeFinanceId() {
        Long typeFinanceId = 1L;
        List<Finance> finances = new ArrayList<>();
        Finance finance1 = new Finance();
        finance1.setTypeFinance(new TypeFinance(typeFinanceId, "Type Finance 1"));
        Finance finance2 = new Finance();
        finance2.setTypeFinance(new TypeFinance(2L, "Type Finance 2"));
        finances.add(finance1);
        finances.add(finance2);

        when(financeRepositoryPostgres.findByTypeFinanceId(typeFinanceId)).thenReturn(finances);

        List<Finance> result = financePostgresDatabaseStrategy.findByTypeFinanceId(typeFinanceId);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(Optional.of(typeFinanceId), Optional.of(result.get(0).getTypeFinance().getId()));
    }

    @Test
    void testFindByCategoryId() {
        Long categoryId = 1L;
        List<Finance> finances = new ArrayList<>();
        Finance finance1 = new Finance();
        finance1.setCategory(new Category(categoryId, "Category 1"));
        Finance finance2 = new Finance();
        finance2.setCategory(new Category(2L, "Category 2"));
        finances.add(finance1);
        finances.add(finance2);

        when(financeRepositoryPostgres.findByCategoryId(categoryId)).thenReturn(finances);

        List<Finance> result = financePostgresDatabaseStrategy.findByCategoryId(categoryId);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(Optional.of(categoryId), Optional.of(result.get(0).getCategory().getId()));
    }

    @Test
    void testFindByDateTimeBetween() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusDays(7);

        when(financeRepositoryPostgres.findByDateTimeBetween(start, end)).thenReturn(new ArrayList<>());

        List<Finance> result = financePostgresDatabaseStrategy.findByDateTimeBetween(start, end);

        assertNotNull(result);
    }

    @Test
    void testFindByAmountBetween() {
        double init = 100.0;
        double end = 1000.0;

        when(financeRepositoryPostgres.findByAmountBetween(init, end)).thenReturn(new ArrayList<>());

        List<Finance> result = financePostgresDatabaseStrategy.findByAmountBetween(init, end);

        assertNotNull(result);
    }

    @Test
    void testFindByName() {
        String name = "Finance 1";
        Finance finance = new Finance();
        finance.setName(name);

        when(financeRepositoryPostgres.findByName(name)).thenReturn(finance);

        Finance result = financePostgresDatabaseStrategy.findByName(name);

        assertNotNull(result);
        assertEquals(name, result.getName());
    }

    @Test
    void testGetFinanceReport() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusDays(7);
        Long portfolioId = 1L;

        List<FinanceReport> financeReportList = new ArrayList<>();
        financeReportList.add(new FinanceReport("Type Finance 1", 500.0));
        financeReportList.add(new FinanceReport("Type Finance 2", 300.0));

        when(financeRepositoryPostgres.getFinanceReport(start, end, portfolioId)).thenReturn(financeReportList);

        List<FinanceReport> financeReports = financePostgresDatabaseStrategy.getFinanceReport(start, end, portfolioId);

        assertNotNull(financeReports);
        assertEquals(2, financeReports.size());
    }
}
