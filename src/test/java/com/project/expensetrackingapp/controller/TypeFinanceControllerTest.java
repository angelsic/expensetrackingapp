package com.project.expensetrackingapp.controller;

import com.project.expensetrackingapp.repository.entity.typefinance.TypeFinance;
import com.project.expensetrackingapp.service.typefinance.TypeFinanceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class TypeFinanceControllerTest {

    @InjectMocks
    private TypeFinanceController typeFinanceController;

    @Mock
    private TypeFinanceService typeFinanceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveTypeFinance() {
        TypeFinance typeFinance = new TypeFinance(1L, "Test Type Finance");
        Mockito.when(typeFinanceService.saveTypeFinance(typeFinance)).thenReturn(typeFinance);

        ResponseEntity<TypeFinance> response = typeFinanceController.saveTypeFinance(typeFinance);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isEqualTo(typeFinance);
    }

    @Test
    void testGetAllTypeFinance() {
        TypeFinance typeFinance1 = new TypeFinance(1L, "Type Finance 1");
        TypeFinance typeFinance2 = new TypeFinance(2L, "Type Finance 2");
        List<TypeFinance> typeFinances = Arrays.asList(typeFinance1, typeFinance2);
        Mockito.when(typeFinanceService.getAllTypeFinance()).thenReturn(typeFinances);

        ResponseEntity<List<TypeFinance>> response = typeFinanceController.getAllTypeFinance();

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isEqualTo(typeFinances);
    }

    @Test
    void testGetTypeFinance() {
        TypeFinance typeFinance = new TypeFinance(1L, "Test Type Finance");
        Mockito.when(typeFinanceService.getTypeFinance("Test Type Finance")).thenReturn(typeFinance);

        ResponseEntity<TypeFinance> response = typeFinanceController.getFinance("Test Type Finance");

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isEqualTo(typeFinance);
    }

    @Test
    void testUpdateTypeFinance() {
        TypeFinance typeFinance = new TypeFinance(1L, "Test Type Finance");
        Mockito.when(typeFinanceService.updateTypeFinance(typeFinance)).thenReturn(typeFinance);

        ResponseEntity<TypeFinance> response = typeFinanceController.updateFinance(typeFinance);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isEqualTo(typeFinance);
    }

    @Test
    void testDeleteTypeFinance() {
        String typeFinanceName = "Test Type Finance";
        Mockito.when(typeFinanceService.deleteTypeFinance(typeFinanceName)).thenReturn("Type Finance deleted successfully");

        String response = typeFinanceController.deleteTypeFinance(typeFinanceName);

        assertThat(response).isEqualTo("Type Finance deleted successfully");
    }
}
