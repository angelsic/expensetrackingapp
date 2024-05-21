package com.project.expensetrackingapp.service;

import com.project.expensetrackingapp.exception.IdNotFound;
import com.project.expensetrackingapp.exception.typefinance.TypeFinanceAlreadyExist;
import com.project.expensetrackingapp.exception.typefinance.TypeFinanceNotExist;
import com.project.expensetrackingapp.exception.typefinance.TypeFinanceNotFound;
import com.project.expensetrackingapp.repository.entity.typefinance.TypeFinance;
import com.project.expensetrackingapp.repository.typefinance.TypeFinanceDatabaseStrategy;
import com.project.expensetrackingapp.service.typefinance.TypeFinanceServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class TypeFinanceServiceImplTest {

    @InjectMocks
    private TypeFinanceServiceImpl typeFinanceService;

    @Mock
    private TypeFinanceDatabaseStrategy typeFinanceDatabaseStrategy;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        typeFinanceService.setDatabaseStrategy(typeFinanceDatabaseStrategy);
    }

    @Test
    void testSaveTypeFinance_TypeFinanceNotFound() {
        TypeFinance typeFinance = new TypeFinance();
        typeFinance.setName(null);

        try {
            typeFinanceService.saveTypeFinance(typeFinance);
            fail("TypeFinanceNotFound exception not thrown");
        } catch (TypeFinanceNotFound e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    void testSaveTypeFinance_TypeFinanceAlreadyExist() {
        TypeFinance typeFinance = new TypeFinance();
        typeFinance.setName("Existing Type Finance");

        TypeFinance existingTypeFinance = new TypeFinance();
        existingTypeFinance.setName("Existing Type Finance");

        when(typeFinanceDatabaseStrategy.findByName(typeFinance.getName())).thenReturn(existingTypeFinance);

        try {
            typeFinanceService.saveTypeFinance(typeFinance);
            fail("TypeFinanceAlreadyExist exception not thrown");
        } catch (TypeFinanceAlreadyExist e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    void testSaveTypeFinance_SuccessfulSave() {
        TypeFinance typeFinance = new TypeFinance();
        typeFinance.setName("New Type Finance");

        when(typeFinanceDatabaseStrategy.findByName(typeFinance.getName())).thenReturn(null);
        when(typeFinanceDatabaseStrategy.save(typeFinance)).thenReturn(typeFinance);

        TypeFinance savedTypeFinance = typeFinanceService.saveTypeFinance(typeFinance);

        assertNotNull(savedTypeFinance);
        assertEquals(typeFinance, savedTypeFinance);
    }

    @Test
    void testGetTypeFinance() {
        String name = "Test Type Finance";
        TypeFinance typeFinance = new TypeFinance();
        typeFinance.setName(name);

        when(typeFinanceDatabaseStrategy.findByName(name)).thenReturn(typeFinance);

        TypeFinance result = typeFinanceService.getTypeFinance(name);

        assertEquals(typeFinance, result);
    }

    @Test
    void testGetAllTypeFinance() {
        List<TypeFinance> typeFinances = new ArrayList<>();
        when(typeFinanceDatabaseStrategy.findAll()).thenReturn(typeFinances);

        List<TypeFinance> result = typeFinanceService.getAllTypeFinance();

        assertEquals(typeFinances, result);
    }

    @Test
    void testUpdateTypeFinance_IdNotFound() {
        TypeFinance typeFinance = new TypeFinance();
        typeFinance.setId(0L);

        try {
            typeFinanceService.updateTypeFinance(typeFinance);
            fail("IdNotFound exception not thrown");
        } catch (IdNotFound e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    void testUpdateTypeFinance_TypeFinanceNotFound() {
        TypeFinance typeFinance = new TypeFinance();
        typeFinance.setId(1L);
        typeFinance.setName(null);

        try {
            typeFinanceService.updateTypeFinance(typeFinance);
            fail("TypeFinanceNotFound exception not thrown");
        } catch (TypeFinanceNotFound e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    void testUpdateTypeFinance_TypeFinanceAlreadyExist() {
        TypeFinance typeFinance = new TypeFinance();
        typeFinance.setId(1L);
        typeFinance.setName("Existing Type Finance");

        TypeFinance existingTypeFinance = new TypeFinance();
        existingTypeFinance.setName("Existing Type Finance");

        when(typeFinanceDatabaseStrategy.findById(typeFinance.getId())).thenReturn(Optional.of(typeFinance));
        when(typeFinanceDatabaseStrategy.findByName(typeFinance.getName())).thenReturn(existingTypeFinance);

        try {
            typeFinanceService.updateTypeFinance(typeFinance);
            fail("TypeFinanceAlreadyExist exception not thrown");
        } catch (TypeFinanceAlreadyExist e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    void testUpdateTypeFinance_TypeFinanceNotExist() {
        TypeFinance typeFinance = new TypeFinance();
        typeFinance.setId(1L);
        typeFinance.setName("Non-existent Type Finance");

        when(typeFinanceDatabaseStrategy.findById(typeFinance.getId())).thenReturn(Optional.empty());

        try {
            typeFinanceService.updateTypeFinance(typeFinance);
            fail("TypeFinanceNotExist exception not thrown");
        } catch (TypeFinanceNotExist e) {
            assertNotNull(e.getMessage());
        }
    }

    @Test
    void testDeleteTypeFinance() {
        String name = "Test Type Finance";
        TypeFinance typeFinance = new TypeFinance();
        typeFinance.setName(name);

        when(typeFinanceDatabaseStrategy.findByName(name)).thenReturn(typeFinance);

        String result = typeFinanceService.deleteTypeFinance(name);

        assertEquals(name + " was removed", result);
    }

    @Test
    void testDeleteTypeFinance_TypeFinanceNotExist() {
        String name = "Non-existent Type Finance";

        when(typeFinanceDatabaseStrategy.findByName(name)).thenReturn(null);

        String result = typeFinanceService.deleteTypeFinance(name);

        assertEquals(name + " not exist", result);
    }
}
