package com.project.expensetrackingapp.repository;

import com.project.expensetrackingapp.repository.entity.portfolio.Portfolio;
import com.project.expensetrackingapp.repository.portfolio.PortfolioPostgresDatabaseStrategy;
import com.project.expensetrackingapp.repository.portfolio.PortfolioRepositoryPostgres;
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
class PortfolioPostgresDatabaseStrategyTest {

    @InjectMocks
    private PortfolioPostgresDatabaseStrategy portfolioPostgresDatabaseStrategy;

    @Mock
    private PortfolioRepositoryPostgres portfolioRepositoryPostgres;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindByName() {
        String portfolioName = "Test Portfolio";
        Portfolio portfolio = new Portfolio();
        portfolio.setName(portfolioName);

        when(portfolioRepositoryPostgres.findByName(portfolioName)).thenReturn(portfolio);

        Portfolio result = portfolioPostgresDatabaseStrategy.findByName(portfolioName);

        assertNotNull(result);
        assertEquals(portfolioName, result.getName());
    }

    @Test
    void testFindAll() {
        List<Portfolio> portfolios = new ArrayList<>();
        portfolios.add(new Portfolio());
        portfolios.add(new Portfolio());

        when(portfolioRepositoryPostgres.findAll()).thenReturn(portfolios);

        List<Portfolio> result = portfolioPostgresDatabaseStrategy.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testSave() {
        Portfolio portfolio = new Portfolio();
        portfolio.setName("New Portfolio");

        when(portfolioRepositoryPostgres.save(portfolio)).thenReturn(portfolio);

        Portfolio savedPortfolio = portfolioPostgresDatabaseStrategy.save(portfolio);

        assertNotNull(savedPortfolio);
    }

    @Test
    void testDeleteById() {
        Long portfolioId = 1L;

        portfolioPostgresDatabaseStrategy.deleteById(portfolioId);

        verify(portfolioRepositoryPostgres, times(1)).deleteById(portfolioId);
    }

    @Test
    void testFindById() {
        Long portfolioId = 1L;
        Portfolio portfolio = new Portfolio();
        portfolio.setId(portfolioId);

        when(portfolioRepositoryPostgres.findById(portfolioId)).thenReturn(Optional.of(portfolio));

        Optional<Portfolio> result = portfolioPostgresDatabaseStrategy.findById(portfolioId);

        assertNotNull(result);
        assertEquals(Optional.of(portfolioId), Optional.of(result.get().getId()));
    }
}