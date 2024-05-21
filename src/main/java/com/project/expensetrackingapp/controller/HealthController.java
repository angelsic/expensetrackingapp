package com.project.expensetrackingapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/health")
@Tag(name = "Health App")
public class HealthController {
    @GetMapping
    @Operation(summary = "Validate if app is alive")
    public ResponseEntity<String> ping(){
        return ResponseEntity.ok("Expense Tracking App Version 1.0");
    }
}
