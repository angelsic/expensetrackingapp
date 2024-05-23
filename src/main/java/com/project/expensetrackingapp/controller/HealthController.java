package com.project.expensetrackingapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Health Controller
 * Base url: /api/v1/health
 * Identify if application is online
 * @author Angel Sic
 */
@RestController
@RequestMapping(value = "/api/v1/health")
@Tag(name = "Health App")
public class HealthController {

    /**
     * Validate if app is alive
     * @return String Message
     */
    @GetMapping
    @Operation(summary = "Validate if app is alive")
    public ResponseEntity<String> ping(){
        return ResponseEntity.ok("Expense Tracking App Version 1.0");
    }
}
