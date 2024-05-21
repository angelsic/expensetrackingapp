package com.project.expensetrackingapp.controller;

import com.project.expensetrackingapp.repository.entity.finance.Finance;
import com.project.expensetrackingapp.repository.entity.finance.FinanceReport;
import com.project.expensetrackingapp.repository.entity.finance.FinanceRequest;
import com.project.expensetrackingapp.repository.entity.finance.FinanceResponse;
import com.project.expensetrackingapp.service.finance.FinanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/finances")
@Tag(name = "Finance CRUD")
public class FinanceController {

    @Autowired
    FinanceService financeService;

    @Transactional
    @PostMapping
    @Operation(summary = "Create new income or expense detail information")
    public ResponseEntity<Finance> saveFinance(@RequestBody Finance finance){
        Finance financeResponse = financeService.saveFinance(finance);
        return ResponseEntity.ok(financeResponse);
    }

    @GetMapping
    @Operation(summary = "Get all income or expense detail information")
    public ResponseEntity<List<Finance>> getAllFinance(){
        List<Finance> finances = financeService.getAllFinance();
        return ResponseEntity.ok(finances);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get income or expense detail information by Identification")
    public ResponseEntity<Finance> getFinance(@PathVariable long id){
        Finance finance = financeService.getFinance(id).orElse(null);
        return ResponseEntity.ok().body(finance);
    }

    @GetMapping("/report")
    @Operation(summary = "Generate income and expense report information by range of date")
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
    @Operation(summary = "Custom search income or expense detail information")
    public ResponseEntity<List<FinanceResponse>> searchFinances(@RequestBody FinanceRequest finance){
        List<FinanceResponse> finances = financeService.getFinanceByFilter(
                finance.getId(), finance.getIdPortfolio(), finance.getIdTypeFinance()
                , finance.getIdCategory(), finance.getInitAmount()
                , finance.getEndAmount(), finance.getStart(), finance.getEnd()
        );
        return ResponseEntity.ok(finances);
    }

    @PatchMapping
    @Operation(summary = "Update income or expense detail information")
    public ResponseEntity<Finance> updateFinance(@RequestBody Finance finance){
        Finance financeResponse = financeService.updateFinance(finance);
        return ResponseEntity.ok(financeResponse);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete income or expense detail information")
    public String deleteFinance(@PathVariable long id){
        return financeService.deleteFinance(id);
    }
}
