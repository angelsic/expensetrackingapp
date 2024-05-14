package com.project.expensetrackingapp.controller;

import com.project.expensetrackingapp.repository.entity.portfolio.Portfolio;
import com.project.expensetrackingapp.service.portfolio.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/portfolios")
public class PortfolioController {

    @Autowired
    PortfolioService portfolioService;

    @Transactional
    @PostMapping
    public ResponseEntity<Portfolio> savePortfolio(@RequestBody Portfolio portfolio){
        Portfolio portfolioResponse = portfolioService.savePortfolio(portfolio);
        return ResponseEntity.ok(portfolioResponse);
    }

    @GetMapping
    public ResponseEntity<List<Portfolio>> getAllPortfolio(){
        List<Portfolio> portfolios = portfolioService.getAllPortfolio();
        return ResponseEntity.ok(portfolios);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Portfolio> getPortfolio(@PathVariable String name){
        Portfolio portfolio = portfolioService.getPortfolio(name);
        return ResponseEntity.ok().body(portfolio);
    }

    @PatchMapping
    public ResponseEntity<Portfolio> updatePortfolio(@RequestBody Portfolio portfolio){
        Portfolio portfolioResponse = portfolioService.updatePortfolio(portfolio);
        return ResponseEntity.ok(portfolioResponse);
    }

    @DeleteMapping(value = "/{name}")
    public String deletePortfolio(@PathVariable String name){
        return portfolioService.deletePortfolio(name);
    }
}
