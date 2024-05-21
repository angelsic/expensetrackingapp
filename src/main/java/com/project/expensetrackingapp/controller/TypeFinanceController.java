package com.project.expensetrackingapp.controller;

import com.project.expensetrackingapp.repository.entity.typefinance.TypeFinance;
import com.project.expensetrackingapp.service.typefinance.TypeFinanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/typefinances")
@Tag(name = "Type Finance CRUD")
public class TypeFinanceController {

    @Autowired
    TypeFinanceService typeFinanceService;

    @Transactional
    @PostMapping
    @Operation(summary = "Create new types of finance in addition to income and expenses")
    public ResponseEntity<TypeFinance> saveTypeFinance(@RequestBody TypeFinance typeFinance){
        TypeFinance typeFinanceResponse = typeFinanceService.saveTypeFinance(typeFinance);
        return ResponseEntity.ok(typeFinanceResponse);
    }

    @GetMapping
    @Operation(summary = "Get all types of finances within the system")
    public ResponseEntity<List<TypeFinance>> getAllTypeFinance(){
        List<TypeFinance> typeFinanceResult = typeFinanceService.getAllTypeFinance();
        return ResponseEntity.ok(typeFinanceResult);
    }

    @GetMapping("/{name}")
    @Operation(summary = "Get specific type of finances by Name")
    public ResponseEntity<TypeFinance> getFinance(@PathVariable String name){
        TypeFinance typeFinance = typeFinanceService.getTypeFinance(name);
        return ResponseEntity.ok().body(typeFinance);
    }

    @PatchMapping
    @Operation(summary = "Update type of finances")
    public ResponseEntity<TypeFinance> updateFinance(@RequestBody TypeFinance typeFinance){
        TypeFinance typeFinanceResult = typeFinanceService.updateTypeFinance(typeFinance);
        return ResponseEntity.ok(typeFinanceResult);
    }

    @DeleteMapping(value = "/{name}")
    @Operation(summary = "Delete type of finances")
    public String deleteTypeFinance(@PathVariable String name){
        return typeFinanceService.deleteTypeFinance(name);
    }

}
