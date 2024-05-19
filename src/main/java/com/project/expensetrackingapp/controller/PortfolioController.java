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

@RestController
@RequestMapping("/api/v1/portfolios")
@Tag(name = "Portfolio CRUD")
public class PortfolioController {

    @Autowired
    PortfolioService portfolioService;

    @Transactional
    @PostMapping
    @Operation(summary = "Create new group of income and expense information")
    public ResponseEntity<Portfolio> savePortfolio(@RequestBody Portfolio portfolio){
        Portfolio portfolioResponse = portfolioService.savePortfolio(portfolio);
        return ResponseEntity.ok(portfolioResponse);
    }

    @GetMapping
    @Operation(summary = "Get all groups of income and expense information")
    public ResponseEntity<List<Portfolio>> getAllPortfolio(){
        List<Portfolio> portfolios = portfolioService.getAllPortfolio();
        return ResponseEntity.ok(portfolios);
    }

    @GetMapping("/{name}")
    @Operation(summary = "Get specific group of income and expense information by Groups Name")
    public ResponseEntity<Portfolio> getPortfolio(@PathVariable String name){
        Portfolio portfolio = portfolioService.getPortfolio(name);
        return ResponseEntity.ok().body(portfolio);
    }

    @PatchMapping
    @Operation(summary = "Update group of income and expense information")
    public ResponseEntity<Portfolio> updatePortfolio(@RequestBody Portfolio portfolio){
        Portfolio portfolioResponse = portfolioService.updatePortfolio(portfolio);
        return ResponseEntity.ok(portfolioResponse);
    }

    @DeleteMapping(value = "/{name}")
    @Operation(summary = "Delete group of income and expense information by Groups Name")
    public String deletePortfolio(@PathVariable String name){
        return portfolioService.deletePortfolio(name);
    }
}
