package com.project.expensetrackingapp.controller;

import com.project.expensetrackingapp.repository.entity.category.Category;
import com.project.expensetrackingapp.repository.entity.finance.Finance;
import com.project.expensetrackingapp.repository.entity.finance.FinanceReport;
import com.project.expensetrackingapp.repository.entity.finance.FinanceRequest;
import com.project.expensetrackingapp.repository.entity.finance.FinanceResponse;
import com.project.expensetrackingapp.repository.entity.portfolio.Portfolio;
import com.project.expensetrackingapp.repository.entity.typefinance.TypeFinance;
import com.project.expensetrackingapp.service.finance.FinanceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

public class FinanceControllerTest {

    @InjectMocks
    private FinanceController financeController;

    @Mock
    private FinanceService financeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveFinance() {
        Finance finance = new Finance(1L, new Portfolio(), new TypeFinance(), new Category(), "Test Finance", 100.0, LocalDateTime.now());
        Mockito.when(financeService.saveFinance(finance)).thenReturn(finance);

        ResponseEntity<Finance> response = financeController.saveFinance(finance);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isEqualTo(finance);
    }

    @Test
    void testGetAllFinance() {
        Finance finance1 = new Finance(1L, new Portfolio(), new TypeFinance(), new Category(), "Finance 1", 100.0, LocalDateTime.now());
        Finance finance2 = new Finance(2L, new Portfolio(), new TypeFinance(), new Category(), "Finance 2", 200.0, LocalDateTime.now());
        List<Finance> finances = Arrays.asList(finance1, finance2);
        Mockito.when(financeService.getAllFinance()).thenReturn(finances);

        ResponseEntity<List<Finance>> response = financeController.getAllFinance();

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isEqualTo(finances);
    }

    @Test
    void testGetFinance() {
        Finance finance = new Finance(1L, new Portfolio(), new TypeFinance(), new Category(), "Test Finance", 100.0, LocalDateTime.now());
        Mockito.when(financeService.getFinance(1L)).thenReturn(Optional.of(finance));

        ResponseEntity<Finance> response = financeController.getFinance(1L);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isEqualTo(finance);
    }

    @Test
    void testGetReport() {
        LocalDateTime start = LocalDateTime.now().minusDays(7);
        LocalDateTime end = LocalDateTime.now();
        Long id = 1L;

        List<FinanceReport> financeReports = Arrays.asList(new FinanceReport(), new FinanceReport());
        Mockito.when(financeService.getFinanceReport(start, end, id)).thenReturn(financeReports);

        ResponseEntity<List<FinanceReport>> response = financeController.getReport(start.toString(), end.toString(), id);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isEqualTo(financeReports);
    }

    @Test
    void testSearchFinances() {
        FinanceRequest financeRequest = new FinanceRequest(1L, 1L, 1L, 1L, 100.0, 200.0, LocalDateTime.now(), LocalDateTime.now());
        List<FinanceResponse> financeResponses = Arrays.asList(new FinanceResponse(), new FinanceResponse());
        Mockito.when(financeService.getFinanceByFilter(1L, 1L, 1L, 1L, 100.0, 200.0, LocalDateTime.now(), LocalDateTime.now())).thenReturn(financeResponses);

        ResponseEntity<List<FinanceResponse>> response = financeController.searchFinances(financeRequest);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isEqualTo(financeResponses);
    }

    @Test
    void testUpdateFinance() {
        Finance finance = new Finance(1L, new Portfolio(), new TypeFinance(), new Category(), "Test Finance", 100.0, LocalDateTime.now());
        Mockito.when(financeService.updateFinance(finance)).thenReturn(finance);

        ResponseEntity<Finance> response = financeController.updateFinance(finance);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isEqualTo(finance);
    }

    @Test
    void testDeleteFinance() {
        long id = 1L;
        Mockito.when(financeService.deleteFinance(id)).thenReturn("Finance deleted successfully");

        String response = financeController.deleteFinance(id);

        assertThat(response).isEqualTo("Finance deleted successfully");
    }
}
