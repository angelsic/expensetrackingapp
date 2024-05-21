package com.project.expensetrackingapp.controller;

import com.project.expensetrackingapp.repository.entity.portfolio.Portfolio;
import com.project.expensetrackingapp.repository.entity.user.User;
import com.project.expensetrackingapp.service.portfolio.PortfolioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class PortfolioControllerTest {

    @InjectMocks
    private PortfolioController portfolioController;

    @Mock
    private PortfolioService portfolioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSavePortfolio() {
        User user = new User(1L, "testUser", "password", new HashSet<>());
        Portfolio portfolio = new Portfolio(1L, "Test Portfolio", user);
        Mockito.when(portfolioService.savePortfolio(portfolio)).thenReturn(portfolio);

        ResponseEntity<Portfolio> response = portfolioController.savePortfolio(portfolio);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isEqualTo(portfolio);
    }

    @Test
    void testGetAllPortfolio() {
        User user = new User(1L, "testUser", "password", new HashSet<>());
        Portfolio portfolio1 = new Portfolio(1L, "Portfolio 1", user);
        Portfolio portfolio2 = new Portfolio(2L, "Portfolio 2", user);
        List<Portfolio> portfolios = Arrays.asList(portfolio1, portfolio2);
        Mockito.when(portfolioService.getAllPortfolio()).thenReturn(portfolios);

        ResponseEntity<List<Portfolio>> response = portfolioController.getAllPortfolio();

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isEqualTo(portfolios);
    }

    @Test
    void testGetPortfolio() {
        User user = new User(1L, "testUser", "password", new HashSet<>());
        Portfolio portfolio = new Portfolio(1L, "Test Portfolio", user);
        Mockito.when(portfolioService.getPortfolio("Test Portfolio")).thenReturn(portfolio);

        ResponseEntity<Portfolio> response = portfolioController.getPortfolio("Test Portfolio");

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isEqualTo(portfolio);
    }

    @Test
    void testUpdatePortfolio() {
        User user = new User(1L, "testUser", "password", new HashSet<>());
        Portfolio portfolio = new Portfolio(1L, "Test Portfolio", user);
        Mockito.when(portfolioService.updatePortfolio(portfolio)).thenReturn(portfolio);

        ResponseEntity<Portfolio> response = portfolioController.updatePortfolio(portfolio);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isEqualTo(portfolio);
    }

    @Test
    void testDeletePortfolio() {
        String portfolioName = "Test Portfolio";
        Mockito.when(portfolioService.deletePortfolio(portfolioName)).thenReturn("Portfolio deleted successfully");

        String response = portfolioController.deletePortfolio(portfolioName);

        assertThat(response).isEqualTo("Portfolio deleted successfully");
    }
}