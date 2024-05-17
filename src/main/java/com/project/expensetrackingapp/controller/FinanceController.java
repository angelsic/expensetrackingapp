package com.project.expensetrackingapp.controller;

import com.project.expensetrackingapp.repository.entity.finance.Finance;
import com.project.expensetrackingapp.repository.entity.finance.FinanceReport;
import com.project.expensetrackingapp.repository.entity.finance.FinanceRequest;
import com.project.expensetrackingapp.repository.entity.finance.FinanceResponse;
import com.project.expensetrackingapp.service.finance.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/finances")
public class FinanceController {

    @Autowired
    FinanceService financeService;

    @Transactional
    @PostMapping
    public ResponseEntity<Finance> saveFinance(@RequestBody Finance finance){
        Finance financeResponse = financeService.saveFinance(finance);
        return ResponseEntity.ok(financeResponse);
    }

    @GetMapping
    public ResponseEntity<List<Finance>> getAllFinance(){
        List<Finance> finances = financeService.getAllFinance();
        return ResponseEntity.ok(finances);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Finance> getFinance(@PathVariable long id){
        Finance finance = financeService.getFinance(id).orElse(null);
        return ResponseEntity.ok().body(finance);
    }

    @GetMapping("/report")
    public ResponseEntity<List<FinanceReport>> getReport(@RequestParam String start,
                                                   @RequestParam String end,
                                                   @RequestParam Long id){
        LocalDateTime startDatetime = LocalDateTime.parse(start);
        LocalDateTime endDatetime = LocalDateTime.parse(end);
        List<FinanceReport> financeReports = financeService.getFinanceReport(
                startDatetime, endDatetime, id
        );
        return ResponseEntity.ok().body(financeReports);
    }

    @PostMapping("/search")
    public ResponseEntity<List<FinanceResponse>> searchFinances(@RequestBody FinanceRequest finance){
        List<FinanceResponse> finances = financeService.getFinanceByFilter(
                finance.getId(), finance.getIdPortfolio(), finance.getIdTypeFinance()
                , finance.getIdCategory(), finance.getInitAmount()
                , finance.getEndAmount(), finance.getStart(), finance.getEnd()
        );
        return ResponseEntity.ok(finances);
    }

    @PatchMapping
    public ResponseEntity<Finance> updateFinance(@RequestBody Finance finance){
        Finance financeResponse = financeService.updateFinance(finance);
        return ResponseEntity.ok(financeResponse);
    }

    @DeleteMapping(value = "/{id}")
    public String deleteFinance(@PathVariable long id){
        return financeService.deleteFinance(id);
    }
}
