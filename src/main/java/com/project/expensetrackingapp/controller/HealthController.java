package com.project.expensetrackingapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/health")
public class HealthController {
    @GetMapping
    public ResponseEntity<String> ping(){
        return ResponseEntity.ok("Expense Tracking App Version 1.0");
    }
}
