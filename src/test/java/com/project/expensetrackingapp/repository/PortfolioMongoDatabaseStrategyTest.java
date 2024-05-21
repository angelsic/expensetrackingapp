package com.project.expensetrackingapp.repository;

import com.project.expensetrackingapp.repository.entity.portfolio.Portfolio;
import com.project.expensetrackingapp.repository.portfolio.PortfolioMongoDatabaseStrategy;
import com.project.expensetrackingapp.repository.portfolio.PortfolioRepositoryMongo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class PortfolioMongoDatabaseStrategyTest {

    @InjectMocks
    private PortfolioMongoDatabaseStrategy portfolioMongoDatabaseStrategy;

    @Mock
    private PortfolioRepositoryMongo portfolioRepositoryMongo;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindByName() {
        String portfolioName = "Test Portfolio";
        Portfolio portfolio = new Portfolio();
        portfolio.setName(portfolioName);

        when(portfolioRepositoryMongo.findByName(portfolioName)).thenReturn(portfolio);

        Portfolio result = portfolioMongoDatabaseStrategy.findByName(portfolioName);

        assertNotNull(result);
        assertEquals(portfolioName, result.getName());
    }

    @Test
    void testFindAll() {
        List<Portfolio> portfolios = new ArrayList<>();
        portfolios.add(new Portfolio());
        portfolios.add(new Portfolio());

        when(portfolioRepositoryMongo.findAll()).thenReturn(portfolios);

        List<Portfolio> result = portfolioMongoDatabaseStrategy.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testSave() {
        Portfolio portfolio = new Portfolio();
        portfolio.setId(0L);
        portfolio.setName("New Portfolio");

        when(portfolioRepositoryMongo.save(portfolio)).thenReturn(portfolio);

        Portfolio savedPortfolio = portfolioMongoDatabaseStrategy.save(portfolio);

        assertNotNull(savedPortfolio);
        assertNotNull(savedPortfolio.getId());
    }

    @Test
    void testDeleteById() {
        Long portfolioId = 1L;

        portfolioMongoDatabaseStrategy.deleteById(portfolioId);

        verify(portfolioRepositoryMongo, times(1)).deleteById(portfolioId);
    }

    @Test
    void testFindById() {
        Long portfolioId = 1L;
        Portfolio portfolio = new Portfolio();
        portfolio.setId(portfolioId);

        when(portfolioRepositoryMongo.findById(portfolioId)).thenReturn(Optional.of(portfolio));

        Optional<Portfolio> result = portfolioMongoDatabaseStrategy.findById(portfolioId);

        assertNotNull(result);
        assertEquals(Optional.of(portfolioId), Optional.of(result.get().getId()));
    }
}
