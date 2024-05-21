package com.project.expensetrackingapp.service;

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
import com.project.expensetrackingapp.repository.entity.finance.FinanceReport;
import com.project.expensetrackingapp.repository.entity.finance.FinanceResponse;
import com.project.expensetrackingapp.repository.entity.portfolio.Portfolio;
import com.project.expensetrackingapp.repository.entity.typefinance.TypeFinance;
import com.project.expensetrackingapp.repository.entity.user.User;
import com.project.expensetrackingapp.repository.finance.FinanceDatabaseStrategy;
import com.project.expensetrackingapp.repository.portfolio.PortfolioDatabaseStrategy;
import com.project.expensetrackingapp.repository.typefinance.TypeFinanceDatabaseStrategy;
import com.project.expensetrackingapp.service.finance.FinanceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class FinanceServiceImplTest {

    @InjectMocks
    private FinanceServiceImpl financeService;

    @Mock
    private FinanceDatabaseStrategy financeDatabaseStrategy;

    @Mock
    private PortfolioDatabaseStrategy portfolioDatabaseStrategy;

    @Mock
    private TypeFinanceDatabaseStrategy typeFinanceDatabaseStrategy;

    @Mock
    private CategoryDatabaseStrategy categoryDatabaseStrategy;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetFinanceByPortfolioId() {
        Long portfolioId = 1L;
        List<Finance> finances = new ArrayList<>();
        when(financeDatabaseStrategy.findByPortfolioId(portfolioId)).thenReturn(finances);

        List<Finance> result = financeService.getFinanceByPortfolioId(portfolioId);

        assertEquals(finances, result);
        verify(financeDatabaseStrategy, times(1)).findByPortfolioId(portfolioId);
    }

    @Test
    void testGetAllFinance() {
        List<Finance> finances = new ArrayList<>();
        when(financeDatabaseStrategy.findAll()).thenReturn(finances);

        List<Finance> result = financeService.getAllFinance();

        assertEquals(finances, result);
        verify(financeDatabaseStrategy, times(1)).findAll();
    }

    @Test
    void testSaveFinance() {
        Finance finance = new Finance();
        finance.setName("Test Finance");
        finance.setAmount(100.0);
        finance.setPortfolio(new Portfolio(1L,"Test Portfolio", new User()));
        finance.setTypeFinance(new TypeFinance(1L,"Test Type Finance"));
        finance.setCategory(new Category(1L, "Test Category"));

        when(portfolioDatabaseStrategy.findByName(finance.getPortfolio().getName())).thenReturn(finance.getPortfolio());
        when(typeFinanceDatabaseStrategy.findByName(finance.getTypeFinance().getName())).thenReturn(finance.getTypeFinance());
        when(categoryDatabaseStrategy.findByName(finance.getCategory().getName())).thenReturn(finance.getCategory());
        when(financeDatabaseStrategy.save(finance)).thenReturn(finance);

        Finance savedFinance = financeService.saveFinance(finance);

        assertNotNull(savedFinance);
        assertEquals(finance, savedFinance);
        verify(portfolioDatabaseStrategy, times(1)).findByName(finance.getPortfolio().getName());
        verify(typeFinanceDatabaseStrategy, times(1)).findByName(finance.getTypeFinance().getName());
        verify(categoryDatabaseStrategy, times(1)).findByName(finance.getCategory().getName());
        verify(financeDatabaseStrategy, times(1)).save(finance);
    }

    @Test
    void testDeleteFinance() {
        Long financeId = 1L;
        Finance finance = new Finance();
        finance.setName("Test Finance");

        when(financeDatabaseStrategy.findById(financeId)).thenReturn(Optional.of(finance));

        String result = financeService.deleteFinance(financeId);

        assertEquals(finance.getName() + " was removed", result);
        verify(financeDatabaseStrategy, times(1)).findById(financeId);
        verify(financeDatabaseStrategy, times(1)).deleteById(financeId);
    }

    @Test
    void testGetFinance() {
        Long financeId = 1L;
        Finance finance = new Finance();
        when(financeDatabaseStrategy.findById(financeId)).thenReturn(Optional.of(finance));

        Optional<Finance> result = financeService.getFinance(financeId);

        assertTrue(result.isPresent());
        assertEquals(finance, result.get());
        verify(financeDatabaseStrategy, times(1)).findById(financeId);
    }

    @Test
    void testUpdateFinance() {
        Finance finance = new Finance();
        finance.setId(1L);
        finance.setName("Test Finance");
        finance.setAmount(100.0);
        finance.setPortfolio(new Portfolio(1L,"Test Portfolio", new User()));
        finance.setTypeFinance(new TypeFinance(1L,"Test Type Finance"));
        finance.setCategory(new Category(1L, "Test Category"));

        when(financeDatabaseStrategy.findById(finance.getId())).thenReturn(Optional.of(finance));
        when(portfolioDatabaseStrategy.findByName(finance.getPortfolio().getName())).thenReturn(finance.getPortfolio());
        when(typeFinanceDatabaseStrategy.findByName(finance.getTypeFinance().getName())).thenReturn(finance.getTypeFinance());
        when(categoryDatabaseStrategy.findByName(finance.getCategory().getName())).thenReturn(finance.getCategory());
        when(financeDatabaseStrategy.save(finance)).thenReturn(finance);

        Finance updatedFinance = financeService.updateFinance(finance);

        assertNotNull(updatedFinance);
        assertEquals(finance, updatedFinance);
        verify(financeDatabaseStrategy, times(1)).findById(finance.getId());
        verify(portfolioDatabaseStrategy, times(1)).findByName(finance.getPortfolio().getName());
        verify(typeFinanceDatabaseStrategy, times(1)).findByName(finance.getTypeFinance().getName());
        verify(categoryDatabaseStrategy, times(1)).findByName(finance.getCategory().getName());
        verify(financeDatabaseStrategy, times(1)).save(finance);
    }

    @Test
    void testGetFinanceByTypeFinanceId() {
        Long typeFinanceId = 1L;
        List<Finance> finances = new ArrayList<>();
        when(financeDatabaseStrategy.findByTypeFinanceId(typeFinanceId)).thenReturn(finances);

        List<Finance> result = financeService.getFinanceByTypeFinanceId(typeFinanceId);

        assertEquals(finances, result);
        verify(financeDatabaseStrategy, times(1)).findByTypeFinanceId(typeFinanceId);
    }

    @Test
    void testGetFinanceByCategoryId() {
        Long categoryId = 1L;
        List<Finance> finances = new ArrayList<>();
        when(financeDatabaseStrategy.findByCategoryId(categoryId)).thenReturn(finances);

        List<Finance> result = financeService.getFinanceByCategoryId(categoryId);

        assertEquals(finances, result);
        verify(financeDatabaseStrategy, times(1)).findByCategoryId(categoryId);
    }

    @Test
    void testGetFinanceByDatetimeBetween() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusDays(1);
        List<Finance> finances = new ArrayList<>();
        when(financeDatabaseStrategy.findByDateTimeBetween(start, end)).thenReturn(finances);

        List<Finance> result = financeService.getFinanceByDatetimeBetween(start, end);

        assertEquals(finances, result);
        verify(financeDatabaseStrategy, times(1)).findByDateTimeBetween(start, end);
    }

    @Test
    void testGetFinanceByAmountBetween() {
        double init = 50.0;
        double end = 150.0;
        List<Finance> finances = new ArrayList<>();
        when(financeDatabaseStrategy.findByAmountBetween(init, end)).thenReturn(finances);

        List<Finance> result = financeService.getFinanceByAmountBetween(init, end);

        assertEquals(finances, result);
        verify(financeDatabaseStrategy, times(1)).findByAmountBetween(init, end);
    }

    @Test
    void testGetFinanceReport() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusDays(1);
        Long id = 1L;
        List<FinanceReport> financeReports = new ArrayList<>();
        when(financeDatabaseStrategy.getFinanceReport(start, end, id)).thenReturn(financeReports);

        List<FinanceReport> result = financeService.getFinanceReport(start, end, id);

        assertEquals(financeReports, result);
        verify(financeDatabaseStrategy, times(1)).getFinanceReport(start, end, id);
    }

    @Test
    void testGetFinanceByFilter() {
        Finance finance1 = new Finance();
        finance1.setId(1L);
        finance1.setPortfolio(new Portfolio(1L, "Portfolio 1", new User()));
        finance1.setTypeFinance(new TypeFinance(1L, "Type Finance 1"));
        finance1.setCategory(new Category(1L, "Category 1"));
        finance1.setName("Finance 1");
        finance1.setAmount(100.0);
        finance1.setDateTime(LocalDateTime.now().minusDays(2));

        Finance finance2 = new Finance();
        finance2.setId(2L);
        finance2.setPortfolio(new Portfolio(2L, "Portfolio 2", new User()));
        finance2.setTypeFinance(new TypeFinance(2L, "Type Finance 2"));
        finance2.setCategory(new Category(2L, "Category 2"));
        finance2.setName("Finance 2");
        finance2.setAmount(200.0);
        finance2.setDateTime(LocalDateTime.now().minusDays(1));

        Finance finance3 = new Finance();
        finance3.setId(3L);
        finance3.setPortfolio(new Portfolio(1L, "Portfolio 1", new User()));
        finance3.setTypeFinance(new TypeFinance(1L, "Type Finance 1"));
        finance3.setCategory(new Category(2L, "Category 2"));
        finance3.setName("Finance 3");
        finance3.setAmount(150.0);
        finance3.setDateTime(LocalDateTime.now());

        List<Finance> allFinances = Arrays.asList(finance1, finance2, finance3);

        when(financeDatabaseStrategy.findAll()).thenReturn(allFinances);

        List<FinanceResponse> result = financeService.getFinanceByFilter(
                null, 1L, null, 2L, 0.0, 0.0, null, null
        );

        assertEquals(1, result.size());


        verify(financeDatabaseStrategy, times(1)).findAll();
    }

    @Test
    void testUpdateFinance_IdNotFound() {
        Finance finance = new Finance();
        finance.setPortfolio(new Portfolio(1L,"Test Portfolio", new User()));
        finance.setTypeFinance(new TypeFinance(1L,"Test Type Finance"));
        finance.setCategory(new Category(1L,"Test Category"));
        finance.setName("Test Finance");
        finance.setAmount(100.0);

        try {
            financeService.updateFinance(finance);
            fail("IdNotFound exception not thrown");
        } catch (IdNotFound e) {
            assertEquals("Id is not defined", e.getMessage());
        }
    }

    @Test
    void testUpdateFinance_PortfolioNameNotFound() {
        Finance finance = new Finance();
        finance.setId(1L);
        finance.setPortfolio(new Portfolio());
        finance.setTypeFinance(new TypeFinance(1L,"Test Type Finance"));
        finance.setCategory(new Category(1L,"Test Category"));
        finance.setName("Test Finance");
        finance.setAmount(100.0);

        try {
            financeService.updateFinance(finance);
            fail("NameNotFound exception not thrown");
        } catch (NameNotFound e) {
            assertEquals("Portofio is not defined", e.getMessage());
        }
    }

    @Test
    void testUpdateFinance_TypeFinanceNotFound() {
        Finance finance = new Finance();
        finance.setId(1L);
        finance.setPortfolio(new Portfolio(1L, "Test Portfolio", new User()));
        finance.setCategory(new Category(1L, "Test Category"));
        finance.setName("Test Finance");
        finance.setAmount(100.0);
        finance.setTypeFinance(new TypeFinance(1L, null));

        try {
            financeService.updateFinance(finance);
            fail("TypeFinanceNotFound exception not thrown");
        } catch (TypeFinanceNotFound e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    void testUpdateFinance_CategoryNotFound() {
        Finance finance = new Finance();
        finance.setId(1L);
        finance.setPortfolio(new Portfolio(1L, "Test Portfolio", new User()));
        finance.setTypeFinance(new TypeFinance(1L, "Test Type Finance"));
        finance.setName("Test Finance");
        finance.setAmount(100.0);
        finance.setCategory(new Category(1L, null));

        try {
            financeService.updateFinance(finance);
            fail("CategoryNotFound exception not thrown");
        } catch (CategoryNotFound e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    void testUpdateFinance_NameNotFound() {
        Finance finance = new Finance();
        finance.setId(1L);
        finance.setPortfolio(new Portfolio(1L,"Test Portfolio", new User()));
        finance.setTypeFinance(new TypeFinance(1L,"Test Type Finance"));
        finance.setCategory(new Category(1L,"Test Category"));
        finance.setAmount(100.0);

        try {
            financeService.updateFinance(finance);
            fail("NameNotFound exception not thrown");
        } catch (NameNotFound e) {
            assertEquals("Name is not defined", e.getMessage());
        }
    }

    @Test
    void testUpdateFinance_AmountNotFound() {
        Finance finance = new Finance();
        finance.setId(1L);
        finance.setPortfolio(new Portfolio(1L,"Test Portfolio", new User()));
        finance.setTypeFinance(new TypeFinance(1L,"Test Type Finance"));
        finance.setCategory(new Category(1L,"Test Category"));
        finance.setName("Test Finance");

        try {
            financeService.updateFinance(finance);
            fail("AmountNotFound exception not thrown");
        } catch (AmountNotFound e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    void testUpdateFinance_CategoryNotExist() {
        Finance finance = new Finance();
        finance.setId(1L);
        finance.setPortfolio(new Portfolio(1L, "Test Portfolio", new User()));
        finance.setTypeFinance(new TypeFinance(1L, "Test Type Finance"));
        finance.setCategory(new Category(1L, "Non-existent Category"));
        finance.setName("Test Finance");
        finance.setAmount(100.0);

        Finance existingFinance = new Finance();
        existingFinance.setId(1L);
        existingFinance.setPortfolio(new Portfolio(1L, "Test Portfolio", new User()));
        existingFinance.setTypeFinance(new TypeFinance(1L, "Test Type Finance"));
        existingFinance.setCategory(new Category(1L, "Non-existent Category"));
        existingFinance.setName("Test Finance");
        existingFinance.setAmount(100.0);

        when(financeDatabaseStrategy.findById(finance.getId())).thenReturn(Optional.of(existingFinance));
        when(portfolioDatabaseStrategy.findByName(finance.getPortfolio().getName())).thenReturn(finance.getPortfolio());
        when(typeFinanceDatabaseStrategy.findByName(finance.getTypeFinance().getName())).thenReturn(finance.getTypeFinance());
        when(categoryDatabaseStrategy.findByName(finance.getCategory().getName())).thenReturn(null);

        try {
            financeService.updateFinance(finance);
            fail("CategoryNotExist exception not thrown");
        } catch (CategoryNotExist e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    void testUpdateFinance_TypeFinanceNotExist() {
        Finance finance = new Finance();
        finance.setId(1L);
        finance.setPortfolio(new Portfolio(1L, "Test Portfolio", new User()));
        finance.setTypeFinance(new TypeFinance(1L, "Non-existent Type Finance"));
        finance.setCategory(new Category(1L, "Test Category"));
        finance.setName("Test Finance");
        finance.setAmount(100.0);

        Finance existingFinance = new Finance();
        existingFinance.setId(1L);
        existingFinance.setPortfolio(new Portfolio(1L, "Test Portfolio", new User()));
        existingFinance.setTypeFinance(new TypeFinance(1L, "Non-existent Type Finance"));
        existingFinance.setCategory(new Category(1L, "Test Category"));
        existingFinance.setName("Test Finance");
        existingFinance.setAmount(100.0);

        when(financeDatabaseStrategy.findById(finance.getId())).thenReturn(Optional.of(existingFinance));
        when(portfolioDatabaseStrategy.findByName(finance.getPortfolio().getName())).thenReturn(finance.getPortfolio());
        when(typeFinanceDatabaseStrategy.findByName(finance.getTypeFinance().getName())).thenReturn(null);

        try {
            financeService.updateFinance(finance);
            fail("TypeFinanceNotExist exception not thrown");
        } catch (TypeFinanceNotExist e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    void testUpdateFinance_PortfolioNotExist() {
        Finance finance = new Finance();
        finance.setId(1L);
        finance.setPortfolio(new Portfolio(1L, "Non-existent Portfolio", new User()));
        finance.setTypeFinance(new TypeFinance(1L, "Test Type Finance"));
        finance.setCategory(new Category(1L, "Test Category"));
        finance.setName("Test Finance");
        finance.setAmount(100.0);

        Finance existingFinance = new Finance();
        existingFinance.setId(1L);
        existingFinance.setPortfolio(new Portfolio(1L, "Non-existent Portfolio", new User()));
        existingFinance.setTypeFinance(new TypeFinance(1L, "Test Type Finance"));
        existingFinance.setCategory(new Category(1L, "Test Category"));
        existingFinance.setName("Test Finance");
        existingFinance.setAmount(100.0);

        when(financeDatabaseStrategy.findById(finance.getId())).thenReturn(Optional.of(existingFinance));
        when(portfolioDatabaseStrategy.findByName(finance.getPortfolio().getName())).thenReturn(null);

        try {
            financeService.updateFinance(finance);
            fail("PortfolioNotExist exception not thrown");
        } catch (PortfolioNotExist e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    void testUpdateFinance_FinanceNotExist() {
        Finance finance = new Finance();
        finance.setId(1L);
        finance.setPortfolio(new Portfolio(1L,"Test Portfolio", new User()));
        finance.setTypeFinance(new TypeFinance(1L,"Test Type Finance"));
        finance.setCategory(new Category(1L,"Test Category"));
        finance.setName("Test Finance");
        finance.setAmount(100.0);

        try {
            financeService.updateFinance(finance);
            fail("FinanceNotExist exception not thrown");
        } catch (FinanceNotExist e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    void testUpdateFinance_SuccessfulUpdate() {
        Finance finance = new Finance();
        finance.setId(1L);
        finance.setPortfolio(new Portfolio(1L,"Test Portfolio", new User()));
        finance.setTypeFinance(new TypeFinance(1L,"Test Type Finance"));
        finance.setCategory(new Category(1L,"Test Category"));
        finance.setName("Test Finance");
        finance.setAmount(100.0);

        Finance existingFinance = new Finance();
        existingFinance.setId(1L);
        existingFinance.setPortfolio(new Portfolio(1L,"Test Portfolio", new User()));
        existingFinance.setTypeFinance(new TypeFinance(1L,"Test Type Finance"));
        existingFinance.setCategory(new Category(1L,"Test Category"));
        existingFinance.setName("Test Finance");
        existingFinance.setAmount(100.0);

        when(financeDatabaseStrategy.findById(finance.getId())).thenReturn(Optional.of(existingFinance));
        when(portfolioDatabaseStrategy.findByName(finance.getPortfolio().getName())).thenReturn(finance.getPortfolio());
        when(typeFinanceDatabaseStrategy.findByName(finance.getTypeFinance().getName())).thenReturn(finance.getTypeFinance());
        when(categoryDatabaseStrategy.findByName(finance.getCategory().getName())).thenReturn(finance.getCategory());
        when(financeDatabaseStrategy.save(finance)).thenReturn(finance);

        Finance updatedFinance = financeService.updateFinance(finance);

        assertNotNull(updatedFinance);
        assertEquals(finance, updatedFinance);
    }
}
