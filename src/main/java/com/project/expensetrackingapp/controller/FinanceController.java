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

/**
 * Finance Controller
 * Base url: /api/v1/finances
 * Manage Finances data
 * @author Angel Sic
 */
@RestController
@RequestMapping("/api/v1/finances")
@Tag(name = "Finance CRUD")
public class FinanceController {

    @Autowired
    FinanceService financeService;

    /**
     * Allows to Create new Finance
     * @param finance Finance structure
     * @return New Finance created
     */
    @Transactional
    @PostMapping
    @Operation(summary = "Create new income or expense detail information")
    public ResponseEntity<Finance> saveFinance(@RequestBody Finance finance){
        Finance financeResponse = financeService.saveFinance(finance);
        return ResponseEntity.ok(financeResponse);
    }

    /**
     * Allow to Get all Finances
     * @return List of Finances
     */
    @GetMapping
    @Operation(summary = "Get all income or expense detail information")
    public ResponseEntity<List<Finance>> getAllFinance(){
        List<Finance> finances = financeService.getAllFinance();
        return ResponseEntity.ok(finances);
    }

    /**
     * Allows to Get specific Finance
     * Url: /{id}
     * @param id Finance Identification
     * @return Finance information
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get income or expense detail information by Identification")
    public ResponseEntity<Finance> getFinance(@PathVariable long id){
        Finance finance = financeService.getFinance(id).orElse(null);
        return ResponseEntity.ok().body(finance);
    }

    /**
     * Allows to get Finance between Init and End Date and Portfolio
     * Url: /report
     * @param start Init Date
     * @param end End Date
     * @param id Portfolio Identification
     * @return Finance Report
     */
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

    /**
     * Allows to Search finance data
     * Url: /search
     * @param finance Finance Request
     * @return List of Finances
     */
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

    /**
     * Allows to Update Finance Information
     * @param finance Finance Information
     * @return Updated Finance
     */
    @PatchMapping
    @Operation(summary = "Update income or expense detail information")
    public ResponseEntity<Finance> updateFinance(@RequestBody Finance finance){
        Finance financeResponse = financeService.updateFinance(finance);
        return ResponseEntity.ok(financeResponse);
    }

    /**
     * Allows to Delete Finance Information
     * Url: /{id}
     * @param id Finance Identification
     * @return Message indicating the result of the Operation
     */
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete income or expense detail information")
    public String deleteFinance(@PathVariable long id){
        return financeService.deleteFinance(id);
    }
}
