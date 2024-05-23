package com.project.expensetrackingapp.controller;

import com.project.expensetrackingapp.repository.entity.portfolio.Portfolio;
import com.project.expensetrackingapp.service.portfolio.PortfolioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Portfolio Controller
 * Base url: /api/v1/portfolios
 * Used to make groups of expenses and income in the application
 * @author Angel Sic
 */
@RestController
@RequestMapping("/api/v1/portfolios")
@Tag(name = "Portfolio CRUD")
public class PortfolioController {

    @Autowired
    PortfolioService portfolioService;

    /**
     * Allows to Create new Portfolio information
     * @param portfolio Portfolio information
     * @return Created Portfolio
     */
    @Transactional
    @PostMapping
    @Operation(summary = "Create new group of income and expense information")
    public ResponseEntity<Portfolio> savePortfolio(@RequestBody Portfolio portfolio){
        Portfolio portfolioResponse = portfolioService.savePortfolio(portfolio);
        return ResponseEntity.ok(portfolioResponse);
    }

    /**
     * Allows to get all portfolio information
     * @return List of Portfolios
     */
    @GetMapping
    @Operation(summary = "Get all groups of income and expense information")
    public ResponseEntity<List<Portfolio>> getAllPortfolio(){
        List<Portfolio> portfolios = portfolioService.getAllPortfolio();
        return ResponseEntity.ok(portfolios);
    }

    /**
     * Allows to get Portfolio by name
     * Url: /{name}
     * @param name Portfolio name
     * @return Specific Portfolio information
     */
    @GetMapping("/{name}")
    @Operation(summary = "Get specific group of income and expense information by Groups Name")
    public ResponseEntity<Portfolio> getPortfolio(@PathVariable String name){
        Portfolio portfolio = portfolioService.getPortfolio(name);
        return ResponseEntity.ok().body(portfolio);
    }

    /**
     * Allows to Update Portfolio information
     * @param portfolio Portfolio information
     * @return Updated Portfolio
     */
    @PatchMapping
    @Operation(summary = "Update group of income and expense information")
    public ResponseEntity<Portfolio> updatePortfolio(@RequestBody Portfolio portfolio){
        Portfolio portfolioResponse = portfolioService.updatePortfolio(portfolio);
        return ResponseEntity.ok(portfolioResponse);
    }

    /**
     * Allows to Delete Portfolio by name
     * Url: /{name}
     * @param name Portfolio name
     * @return Message indicating the result of the Operation
     */
    @DeleteMapping(value = "/{name}")
    @Operation(summary = "Delete group of income and expense information by Groups Name")
    public String deletePortfolio(@PathVariable String name){
        return portfolioService.deletePortfolio(name);
    }
}
