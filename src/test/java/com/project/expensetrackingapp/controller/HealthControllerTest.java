package com.project.expensetrackingapp.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;

public class HealthControllerTest {

    @InjectMocks
    private HealthController healthController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPing() {
        ResponseEntity<String> response = healthController.ping();

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isEqualTo("Expense Tracking App Version 1.0");
    }
}
