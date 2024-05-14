package com.project.expensetrackingapp.controller;

import com.project.expensetrackingapp.repository.entity.typefinance.TypeFinance;
import com.project.expensetrackingapp.service.typefinance.TypeFinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/typefinances")
public class TypeFinanceController {

    @Autowired
    TypeFinanceService typeFinanceService;

    @Transactional
    @PostMapping
    public ResponseEntity<TypeFinance> saveTypeFinance(@RequestBody TypeFinance typeFinance){
        TypeFinance typeFinanceResponse = typeFinanceService.saveTypeFinance(typeFinance);
        return ResponseEntity.ok(typeFinanceResponse);
    }

    @GetMapping
    public ResponseEntity<List<TypeFinance>> getAllTypeFinance(){
        List<TypeFinance> typeFinanceResult = typeFinanceService.getAllTypeFinance();
        return ResponseEntity.ok(typeFinanceResult);
    }

    @GetMapping("/{name}")
    public ResponseEntity<TypeFinance> getFinance(@PathVariable String name){
        TypeFinance typeFinance = typeFinanceService.getTypeFinance(name);
        return ResponseEntity.ok().body(typeFinance);
    }

    @PatchMapping
    public ResponseEntity<TypeFinance> updateFinance(@RequestBody TypeFinance typeFinance){
        TypeFinance typeFinanceResult = typeFinanceService.updateTypeFinance(typeFinance);
        return ResponseEntity.ok(typeFinanceResult);
    }

    @DeleteMapping(value = "/{name}")
    public String deleteTypeFinance(@PathVariable String name){
        return typeFinanceService.deleteTypeFinance(name);
    }

}
