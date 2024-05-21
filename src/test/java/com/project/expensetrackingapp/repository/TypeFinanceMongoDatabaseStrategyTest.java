package com.project.expensetrackingapp.repository;

import com.project.expensetrackingapp.repository.entity.typefinance.TypeFinance;
import com.project.expensetrackingapp.repository.typefinance.TypeFinanceMongoDatabaseStrategy;
import com.project.expensetrackingapp.repository.typefinance.TypeFinanceRepositoryMongo;
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
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class TypeFinanceMongoDatabaseStrategyTest {

    @InjectMocks
    private TypeFinanceMongoDatabaseStrategy typeFinanceMongoDatabaseStrategy;

    @Mock
    private TypeFinanceRepositoryMongo typeFinanceRepositoryMongo;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindByName() {
        String typeName = "Test Type";
        TypeFinance typeFinance = new TypeFinance();
        typeFinance.setName(typeName);

        when(typeFinanceRepositoryMongo.findByName(typeName)).thenReturn(typeFinance);

        TypeFinance result = typeFinanceMongoDatabaseStrategy.findByName(typeName);

        assertNotNull(result);
        assertEquals(typeName, result.getName());
    }

    @Test
    void testFindAll() {
        List<TypeFinance> typeFinances = new ArrayList<>();
        typeFinances.add(new TypeFinance());
        typeFinances.add(new TypeFinance());

        when(typeFinanceRepositoryMongo.findAll()).thenReturn(typeFinances);

        List<TypeFinance> result = typeFinanceMongoDatabaseStrategy.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void testSave_NewType() {
        TypeFinance newType = new TypeFinance();
        newType.setName("New Type");

        when(typeFinanceRepositoryMongo.save(newType)).thenReturn(newType);

        TypeFinance savedType = typeFinanceMongoDatabaseStrategy.save(newType);

        assertNotNull(savedType);
    }

    @Test
    void testSave_ExistingType() {
        TypeFinance existingType = new TypeFinance();
        existingType.setId(1L);
        existingType.setName("Existing Type");

        when(typeFinanceRepositoryMongo.findByName(existingType.getName())).thenReturn(existingType);

        TypeFinance savedType = typeFinanceMongoDatabaseStrategy.save(existingType);

        assertNull(savedType);
    }

    @Test
    void testDeleteById() {
        Long typeId = 1L;

        typeFinanceMongoDatabaseStrategy.deleteById(typeId);

        verify(typeFinanceRepositoryMongo, times(1)).deleteById(typeId);
    }

    @Test
    void testFindById() {
        Long typeId = 1L;
        TypeFinance typeFinance = new TypeFinance();
        typeFinance.setId(typeId);

        when(typeFinanceRepositoryMongo.findById(typeId)).thenReturn(Optional.of(typeFinance));

        Optional<TypeFinance> result = typeFinanceMongoDatabaseStrategy.findById(typeId);

        assertNotNull(result);
        assertEquals(Optional.of(typeId), Optional.of(result.get().getId()));
    }
}
