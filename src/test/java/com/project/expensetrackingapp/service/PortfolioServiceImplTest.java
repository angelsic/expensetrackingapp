package com.project.expensetrackingapp.service;

import com.project.expensetrackingapp.exception.NameNotFound;
import com.project.expensetrackingapp.exception.user.UserNotExist;
import com.project.expensetrackingapp.exception.user.UsernameNotFound;
import com.project.expensetrackingapp.exception.portfolio.PortfolioAlreadyExist;
import com.project.expensetrackingapp.repository.entity.portfolio.Portfolio;
import com.project.expensetrackingapp.repository.entity.role.UserRole;
import com.project.expensetrackingapp.repository.entity.user.User;
import com.project.expensetrackingapp.repository.portfolio.PortfolioDatabaseStrategy;
import com.project.expensetrackingapp.repository.user.UserDatabaseStrategy;
import com.project.expensetrackingapp.service.portfolio.PortfolioServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class PortfolioServiceImplTest {

    @InjectMocks
    private PortfolioServiceImpl portfolioService;

    @Mock
    private PortfolioDatabaseStrategy portfolioDatabaseStrategy;

    @Mock
    private UserDatabaseStrategy userDatabaseStrategy;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        portfolioService.setDatabaseStrategy(portfolioDatabaseStrategy);
        portfolioService.setUserDatabaseStrategy(userDatabaseStrategy);
    }

    @Test
    void testSavePortfolio() {
        Portfolio portfolio = new Portfolio();
        portfolio.setName("Test Portfolio");
        User user = new User();
        user.setUsername("testUser");
        portfolio.setUser(user);

        when(portfolioDatabaseStrategy.findByName(portfolio.getName())).thenReturn(null);
        when(userDatabaseStrategy.findByUsername(user.getUsername())).thenReturn(user);
        when(portfolioDatabaseStrategy.save(portfolio)).thenReturn(portfolio);

        Portfolio savedPortfolio = portfolioService.savePortfolio(portfolio);

        assertNotNull(savedPortfolio);
        assertEquals(portfolio, savedPortfolio);
        verify(portfolioDatabaseStrategy, times(1)).findByName(portfolio.getName());
        verify(userDatabaseStrategy, times(1)).findByUsername(user.getUsername());
        verify(portfolioDatabaseStrategy, times(1)).save(portfolio);
    }

    @Test
    void testGetPortfolio() {
        String portfolioName = "Test Portfolio";
        Portfolio portfolio = new Portfolio();
        portfolio.setName(portfolioName);

        when(portfolioDatabaseStrategy.findByName(portfolioName)).thenReturn(portfolio);

        Portfolio result = portfolioService.getPortfolio(portfolioName);

        assertEquals(portfolio, result);
        verify(portfolioDatabaseStrategy, times(1)).findByName(portfolioName);
    }

    @Test
    void testGetAllPortfolio() {
        List<Portfolio> portfolios = new ArrayList<>();
        when(portfolioDatabaseStrategy.findAll()).thenReturn(portfolios);

        List<Portfolio> result = portfolioService.getAllPortfolio();

        assertEquals(portfolios, result);
        verify(portfolioDatabaseStrategy, times(1)).findAll();
    }

    @Test
    void testUpdatePortfolio() {
        Portfolio portfolio = new Portfolio();
        portfolio.setId(1L);
        portfolio.setName("Test Portfolio");
        User user = new User();
        user.setUsername("testuser");
        portfolio.setUser(user);

        when(portfolioDatabaseStrategy.findById(portfolio.getId())).thenReturn(Optional.of(portfolio));
        when(portfolioDatabaseStrategy.findByName(portfolio.getName())).thenReturn(null);
        when(userDatabaseStrategy.findByUsername(user.getUsername())).thenReturn(user);
        when(portfolioDatabaseStrategy.save(portfolio)).thenReturn(portfolio);

        Portfolio updatedPortfolio = portfolioService.updatePortfolio(portfolio);

        assertNotNull(updatedPortfolio);
        assertEquals(portfolio, updatedPortfolio);
        verify(portfolioDatabaseStrategy, times(1)).findById(portfolio.getId());
        verify(portfolioDatabaseStrategy, times(1)).findByName(portfolio.getName());
        verify(userDatabaseStrategy, times(1)).findByUsername(user.getUsername());
        verify(portfolioDatabaseStrategy, times(1)).save(portfolio);
    }

    @Test
    void testDeletePortfolio() {
        String portfolioName = "Test Portfolio";
        Portfolio portfolio = new Portfolio();
        portfolio.setName(portfolioName);
        when(portfolioDatabaseStrategy.findByName(portfolioName)).thenReturn(portfolio);
        String result = portfolioService.deletePortfolio(portfolioName);
        assertEquals(portfolioName + " was removed", result);
        verify(portfolioDatabaseStrategy, times(1)).findByName(portfolioName);
        verify(portfolioDatabaseStrategy, times(1)).deleteById(portfolio.getId());
    }

    @Test
    void testDeletePortfolioNotExist() {
        String portfolioName = "Test Portfolio";
        Portfolio portfolio = new Portfolio();
        portfolio.setName(portfolioName);
        when(portfolioDatabaseStrategy.findByName(portfolioName)).thenReturn(null);
        String result = portfolioService.deletePortfolio(portfolioName);
        assertEquals(portfolioName + "not exist", result);
    }

    @Test
    public void testSavePortfolio_NameNotFound() {
        Portfolio portfolio = new Portfolio();
        Set<UserRole> roles = new HashSet<>();
        roles.add(new UserRole(1L, "ADMIN"));
        portfolio.setUser(new User(1L, "testUser", "password", roles));

        Exception exception = assertThrows(NameNotFound.class, () -> portfolioService.savePortfolio(portfolio));
        assertEquals("Portfolio name is not defined", exception.getMessage());
    }

    @Test
    public void testSavePortfolio_UsernameNotFound() {
        Portfolio portfolio = new Portfolio();
        portfolio.setName("Test Portfolio");

        Exception exception = assertThrows(UsernameNotFound.class, () -> portfolioService.savePortfolio(portfolio));
        assertEquals("Username is not defined", exception.getMessage());
    }

    @Test
    public void testSavePortfolio_UserNotExist() {
        Portfolio portfolio = new Portfolio();
        portfolio.setName("Test Portfolio");
        Set<UserRole> roles = new HashSet<>();
        roles.add(new UserRole(1L, "ADMIN"));
        portfolio.setUser(new User(1L, "nonexistentuser", "password", roles));

        when(portfolioDatabaseStrategy.findByName(portfolio.getName())).thenReturn(null);
        when(userDatabaseStrategy.findByUsername(portfolio.getUser().getUsername())).thenReturn(null);

        Exception exception = assertThrows(UserNotExist.class, () -> portfolioService.savePortfolio(portfolio));
        assertEquals("User nonexistentuser not exist", exception.getMessage());
    }

    @Test
    public void testSavePortfolio_PortfolioAlreadyExist() {
        Portfolio existingPortfolio = new Portfolio();
        existingPortfolio.setName("Existing Portfolio");

        Portfolio portfolio = new Portfolio();
        portfolio.setName("Existing Portfolio");
        Set<UserRole> roles = new HashSet<>();
        roles.add(new UserRole(1L, "ADMIN"));
        portfolio.setUser(new User(1L, "testuser", "password", roles));

        when(portfolioDatabaseStrategy.findByName(portfolio.getName())).thenReturn(existingPortfolio);

        Exception exception = assertThrows(PortfolioAlreadyExist.class, () -> portfolioService.savePortfolio(portfolio));
        assertEquals("Portfolio Existing Portfolio already exist!", exception.getMessage());
    }

    @Test
    public void testSavePortfolio_SuccessfulSave() {
        Portfolio portfolio = new Portfolio();
        portfolio.setName("Test Portfolio");
        Set<UserRole> roles = new HashSet<>();
        roles.add(new UserRole(1L, "ADMIN"));
        portfolio.setUser(new User(1L, "testuser", "password", roles));

        User existingUser = new User(1L, "testuser", "password", roles);

        when(portfolioDatabaseStrategy.findByName(portfolio.getName())).thenReturn(null);
        when(userDatabaseStrategy.findByUsername(portfolio.getUser().getUsername())).thenReturn(existingUser);
        when(portfolioDatabaseStrategy.save(portfolio)).thenReturn(portfolio);

        Portfolio savedPortfolio = portfolioService.savePortfolio(portfolio);

        assertNotNull(savedPortfolio);
        assertEquals(portfolio, savedPortfolio);
    }
}
