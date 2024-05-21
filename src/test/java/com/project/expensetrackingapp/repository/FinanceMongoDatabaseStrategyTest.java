package com.project.expensetrackingapp.repository;

import com.project.expensetrackingapp.repository.entity.category.Category;
import com.project.expensetrackingapp.repository.entity.finance.Finance;
import com.project.expensetrackingapp.repository.entity.finance.FinanceReport;
import com.project.expensetrackingapp.repository.entity.portfolio.Portfolio;
import com.project.expensetrackingapp.repository.entity.typefinance.TypeFinance;
import com.project.expensetrackingapp.repository.entity.user.User;
import com.project.expensetrackingapp.repository.finance.FinanceMongoDatabaseStrategy;
import com.project.expensetrackingapp.repository.finance.FinanceRepositoryMongo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import org.bson.Document;

@RunWith(MockitoJUnitRunner.class)
class FinanceMongoDatabaseStrategyTest {

    @InjectMocks
    private FinanceMongoDatabaseStrategy financeMongoDatabaseStrategy;

    @Mock
    private FinanceRepositoryMongo financeRepositoryMongo;

    @Mock
    private MongoTemplate mongoTemplate;

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

        when(financeRepositoryMongo.findAll()).thenReturn(finances);

        List<Finance> result = financeMongoDatabaseStrategy.findByPortfolioId(portfolioId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(Optional.of(portfolioId), Optional.of(result.get(0).getPortfolio().getId()));
    }

    @Test
    void testFindAll() {
        List<Finance> finances = new ArrayList<>();
        finances.add(new Finance());
        finances.add(new Finance());

        when(financeRepositoryMongo.findAll()).thenReturn(finances);

        List<Finance> result = financeMongoDatabaseStrategy.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testSave() {
        Finance finance = new Finance();
        finance.setId(0L);
        finance.setName("New Finance");

        when(financeRepositoryMongo.save(finance)).thenReturn(finance);

        Finance savedFinance = financeMongoDatabaseStrategy.save(finance);

        assertNotNull(savedFinance);
        assertNotNull(savedFinance.getId());
    }

    @Test
    void testDeleteById() {
        Long financeId = 1L;

        financeMongoDatabaseStrategy.deleteById(financeId);

        verify(financeRepositoryMongo, times(1)).deleteById(financeId);
    }

    @Test
    void testFindById() {
        Long financeId = 1L;
        Finance finance = new Finance();
        finance.setId(financeId);

        when(financeRepositoryMongo.findById(financeId)).thenReturn(Optional.of(finance));

        Optional<Finance> result = financeMongoDatabaseStrategy.findById(financeId);

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

        when(financeRepositoryMongo.findAll()).thenReturn(finances);

        List<Finance> result = financeMongoDatabaseStrategy.findByTypeFinanceId(typeFinanceId);

        assertNotNull(result);
        assertEquals(1, result.size());
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

        when(financeRepositoryMongo.findAll()).thenReturn(finances);

        List<Finance> result = financeMongoDatabaseStrategy.findByCategoryId(categoryId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(Optional.of(categoryId), Optional.of(result.get(0).getCategory().getId()));
    }

    @Test
    void testFindByDateTimeBetween() {
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusDays(7);

        when(financeRepositoryMongo.findByDateTimeBetween(start, end)).thenReturn(new ArrayList<>());

        List<Finance> result = financeMongoDatabaseStrategy.findByDateTimeBetween(start, end);

        assertNotNull(result);
    }

    @Test
    void testFindByAmountBetween() {
        double init = 100.0;
        double end = 1000.0;

        when(financeRepositoryMongo.findByAmountBetween(init, end)).thenReturn(new ArrayList<>());

        List<Finance> result = financeMongoDatabaseStrategy.findByAmountBetween(init, end);

        assertNotNull(result);
    }

    @Test
    void testFindByName() {
        String name = "Finance 1";
        Finance finance = new Finance();
        finance.setName(name);

        when(financeRepositoryMongo.findByName(name)).thenReturn(finance);

        Finance result = financeMongoDatabaseStrategy.findByName(name);

        assertNotNull(result);
        assertEquals(name, result.getName());
    }


}
